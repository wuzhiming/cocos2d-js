
var __failCount = 0;

var LoaderScene = cc.Scene.extend({
    _am : null,
    _manifestPath : "",
    _storagePath : "",
    _interval : null,
    _label : null,
    _className:"LoaderScene",

    ctor: function(manifestPath, storagePath, cb) {
        this._super();
        this._manifestPath = manifestPath;
        this._storagePath = storagePath;
        this.cb = cb;
    },

    init : function(){
        var self = this;

        //logo
        var logoWidth = 160;
        var logoHeight = 200;

        // bg
        var bgLayer = self._bgLayer = new cc.LayerColor(cc.color(32, 32, 32, 255));
        bgLayer.setPosition(cc.visibleRect.bottomLeft);
        self.addChild(bgLayer, 0);

        //image move to CCSceneFile.js
        var fontSize = 24, lblHeight =  -logoHeight / 2 + 100;
        if(cc._loaderImage){
            //loading logo
            cc.loader.loadImg(cc._loaderImage, {isCrossOrigin : false }, function(err, img){
                logoWidth = img.width;
                logoHeight = img.height;
                self._initStage(img, cc.visibleRect.center);
            });
            fontSize = 14;
            lblHeight = -logoHeight / 2 - 10;
        }
        //loading percent
        var label = self._label = new cc.LabelTTF("Loading... 0%", "Arial", fontSize);
        label.setPosition(cc.pAdd(cc.visibleRect.center, cc.p(0, lblHeight)));
        label.setColor(cc.color(180, 180, 180));
        bgLayer.addChild(this._label, 10);
        return true;
    },

    _initStage: function (img, centerPos) {
        var self = this;
        var texture2d = self._texture2d = new cc.Texture2D();
        texture2d.initWithElement(img);
        texture2d.handleLoadedTexture();
        var logo = self._logo = new cc.Sprite(texture2d);
        logo.setScale(cc.contentScaleFactor());
        logo.x = centerPos.x;
        logo.y = centerPos.y;
        self._bgLayer.addChild(logo, 10);
    },

    onEnter: function () {
        var self = this;
        cc.Node.prototype.onEnter.call(self);
        self.schedule(self._startLoading, 0.3);
    },

    onExit: function () {
        var tmpStr = "Loading... 0%";
        this._label.setString(tmpStr);

        this._am.release();
        cc.Node.prototype.onExit.call(this);
    },

    _startLoading: function () {
        var self = this;
        self.unschedule(self._startLoading);

        this._am = new jsb.AssetsManager(this._manifestPath, this._storagePath);
        this._am.retain();

        if (!this._am.getLocalManifest().isLoaded())
        {
            cc.log("Fail to update assets, step skipped.");
            var scene = new AssetsManagerTestScene(backgroundPaths[currentScene]);
            cc.director.runScene(scene);
        }
        else
        {
            var self = this;
            var updateListener = new jsb.EventListenerAssetsManager(this._am, function(event) {
                switch (event.getEventCode())
                {
                    case jsb.EventAssetsManager.ERROR_NO_LOCAL_MANIFEST:
                        cc.log("No local manifest file found, skip assets update.");
                        //self.cb && self.cb();
                        break;
                    case jsb.EventAssetsManager.UPDATE_PROGRESSION:
                        self._percent = event.getPercent();
                        self._percentByFile = event.getPercentByFile();

                        self._label.setString("Loading... " + (self._percent || self._percentByFile) + "%");

                        var msg = event.getMessage();
                        if (msg) {
                            cc.log(msg);
                        }
                        cc.log((self._percent || self._percentByFile) + "%");
                        break;
                    case jsb.EventAssetsManager.ERROR_DOWNLOAD_MANIFEST:
                    case jsb.EventAssetsManager.ERROR_PARSE_MANIFEST:
                        cc.log("Fail to download manifest file");
                        //self.cb && self.cb();
                        break;
                    case jsb.EventAssetsManager.ALREADY_UP_TO_DATE:
                    case jsb.EventAssetsManager.UPDATE_FINISHED:
                        cc.log("Update finished. " + event.getMessage());

                        var assets = self._am.getLocalManifest().getAssets();
                        cc.log(assets);
                        for (var i in assets) {
                            var asset = assets[i].path;
                            if (asset.length > 0) {
                                if (asset.substr(-3) == ".js") {
                                    cc.log(asset);
                                    require(asset);
                                }
                            }
                        }

                        self.cb && self.cb();
                        break;
                    case jsb.EventAssetsManager.UPDATE_FAILED:
                        cc.log("Update failed. " + event.getMessage());

                        __failCount ++;
                        if (__failCount < 5)
                        {
                            self._am.downloadFailedAssets();
                        }
                        else
                        {
                            cc.log("Reach maximum fail count, exit update process");
                            __failCount = 0;
                            //self.cb && self.cb();
                        }
                        break;
                    case jsb.EventAssetsManager.ERROR_UPDATING:
                        cc.log("Asset update error: " + event.getAssetId() + ", " + event.getMessage());
                        break;
                    case jsb.EventAssetsManager.ERROR_DECOMPRESS:
                        cc.log(event.getMessage());
                        break;
                    default:
                        break;
                }
            });

            cc.eventManager.addListener(updateListener, 1);
            this._am.update();
        }
    }
});

LoaderScene.preload = function(manifestPath, storagePath, cb){
    var loaderScene = new LoaderScene(manifestPath, storagePath, cb);
    loaderScene.init();

    cc.director.runScene(loaderScene);
    return loaderScene;
};