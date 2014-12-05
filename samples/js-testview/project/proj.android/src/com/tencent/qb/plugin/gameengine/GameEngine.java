package com.tencent.qb.plugin.gameengine;


import org.cocos2dx.lib.Cocos2dxView;

import android.content.Context;
import android.util.Log;

import com.tencent.smtt.export.internal.gameengine.IGameEngine;
import com.tencent.smtt.export.internal.gameengine.IGameEngineRuntimeProxy;

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
		{
			Log.d(Tag, "game_engine_creat_view");
			glView = new Cocos2dxView(mCtx);
		}
		else
		{
			Log.d(Tag, "game_engine_use_exist_view");
		}
		return glView;
	}
	
	public void game_engine_onPause()
	{
		glView.viewOnPause();
		Log.d(Tag, "game_engine_onPause");
	}
	
	public void game_engine_onResume()
	{
		glView.viewOnResume();
		Log.d(Tag, "game_engine_onResume");
	}
	
	public void game_engine_onStop()
	{
		Log.d(Tag, "game_engine_onStop");
		
		//glView.viewOnDestory();
		//if(mProxy != null)
			//mProxy.x5_destory_game_engine();
	}
	
	public void game_engine_destory()
	{
		Log.d(Tag, "game_engine_destory");
//		glView.viewOnDestory();
	}
	
	public void game_engine_delete_cache()
	{
		Log.d(Tag, "game_engine_delete_cache");
	}

	@Override
	public void game_engine_set_option(String key, String value) {
		// TODO Auto-generated method stub
		
	}
	
}
