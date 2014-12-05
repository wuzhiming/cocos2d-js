package com.tencent.qb.plugin.gameengine;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class GLView extends GLSurfaceView{
	
	public GLView(Context ctx)
	{
		super(ctx);
		
		setGLRenderer(new SimpleRender());
	}
	
	public void setGLRenderer(Renderer render)
	{
		setRenderer(render);
	}
	
	static class SimpleRender implements Renderer {
    	
    	public void onSurfaceCreated(GL10 gl, EGLConfig config)
    	{}
    	
    	public void onSurfaceChanged(GL10 gl, int width, int height)
    	{}
    	public void onDrawFrame(GL10 gl)
    	{
    		gl.glClearColor(1, 0, 0, 1);
    		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
    	}
	}
}
