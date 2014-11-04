package com.example.gameengine;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Vector;

import org.cocos2dx.lib.Cocos2dxView;

import android.content.Context;
import android.util.Log;

import com.tencent.smtt.export.external.interfaces.IGameEngine;
import com.tencent.smtt.export.external.interfaces.IGameEngineRuntimeProxy;

public class GameEngine implements IGameEngine {
	private String Tag						=	"GameEngine";
	
	private Cocos2dxView glView					= 	null;
	private Context mCtx					= 	null;
	private IGameEngineRuntimeProxy	mProxy	= 	null;
	
	public GameEngine(Context ctx)
	{
		mCtx = ctx;
	}

	public void game_engine_init()
	{
		Log.d(Tag, "game_engine_init");
	}
	
	public void game_engine_set_runtime_proxy(IGameEngineRuntimeProxy proxy)
	{
		mProxy = proxy;
	}
	
	public Cocos2dxView game_engine_get_view()
	{
		Log.d(Tag, "game_engine_get_view");
		if(glView == null)
			glView = new Cocos2dxView(mCtx);
		return glView;
	}
	
	public void game_engine_config()
	{
		Log.d(Tag, "game_engine_config");
	}
	
	public void game_engine_onPause()
	{
		glView.viewOnPause();
		Log.d(Tag, "game_engine_onPause");
	}
	
	public void game_engine_onResume()
	{
		glView.vewOnResume();
		Log.d(Tag, "game_engine_onResume");
	}
	
	public void game_engine_onStop()
	{
		Log.d(Tag, "game_engine_onStop");
		if(mProxy != null)
			mProxy.x5_destory_game_engine();
	}
	
	public void game_engine_destory()
	{
		unloadNativeLibs("libcocos2djs.so");
		Log.d(Tag, "game_engine_destory");
	}
	
	public void game_engine_delete_cache()
	{
		Log.d(Tag, "game_engine_delete_cache");
	}
	
	public static synchronized void unloadNativeLibs(String libName) {
      try {
       ClassLoader classLoader = GameEngine.class.getClassLoader();
       Field field = ClassLoader.class.getDeclaredField("nativeLibraries");
       field.setAccessible(true);
       Vector<Object> libs = (Vector<Object>) field.get(classLoader);
       Iterator it = libs.iterator();
       while (it.hasNext()) {
             Object object = it.next();
             Field[] fs = object.getClass().getDeclaredFields();
             for (int k = 0; k < fs.length; k++) {
                 if (fs[k].getName().equals("name")) {
                       fs[k].setAccessible(true);
                       String dllPath = fs[k].get(object).toString();
                       if (dllPath.endsWith(libName)) {
               	            Method finalize = object.getClass().getDeclaredMethod("finalize");
               	            finalize.setAccessible(true);
               	            finalize.invoke(object);
               	          }
                     }
               }
       	    }
       } catch (Throwable th) {
         th.printStackTrace();
       }
	}
	
}
