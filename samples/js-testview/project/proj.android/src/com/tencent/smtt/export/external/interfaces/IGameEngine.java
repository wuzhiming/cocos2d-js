package com.tencent.smtt.export.external.interfaces;

import android.opengl.GLSurfaceView;

public interface IGameEngine {
	void game_engine_init();
	GLSurfaceView game_engine_get_view();
	void game_engine_config();
	void game_engine_onPause();
	void game_engine_onResume();
	void game_engine_onStop();
	void game_engine_destory();
	void game_engine_delete_cache();
	void game_engine_set_runtime_proxy(IGameEngineRuntimeProxy proxy);
}
