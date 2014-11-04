package com.cocos.EngineTest;

import com.tencent.smtt.export.external.interfaces.IGameEngine;

import android.app.Activity;
import android.os.Bundle;

public class OtherProcessActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		IGameEngine gameEngine = DexLoaderHelper.getGameEngine(this);
		setContentView(gameEngine.game_engine_get_view());
	}
}
