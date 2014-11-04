package com.cocos.EngineTest;

import java.io.File;
import java.lang.reflect.Constructor;

import android.content.Context;

import com.tencent.smtt.export.external.interfaces.IGameEngine;

import dalvik.system.DexClassLoader;

public class DexLoaderHelper {
	public static IGameEngine getGameEngine(Context context)
	{
		/**使用DexClassLoader方式加载类*/  
        //dex压缩文件的路径(可以是apk,jar,zip格式)  
        //String dexPath = Environment.getExternalStorageDirectory().toString() + File.separator + "GameEngine.jar";
		String dexPath = "/mnt/sdcard/GameEngine.jar";
        //dex解压释放后的目录  
        final File optimizedDexOutputPath = context.getDir("outdex", Context.MODE_WORLD_WRITEABLE);
		
		//printClass(dexPath);
        
        //定义DexClassLoader  
        //第一个参数：是dex压缩文件的路径  
        //第二个参数：是dex解压缩后存放的目录  
        //第三个参数：是C/C++依赖的本地库文件目录,可以为null  
        //第四个参数：是上一级的类加载器  
        DexClassLoader cl = new DexClassLoader(dexPath,optimizedDexOutputPath.getAbsolutePath(),null, context.getClassLoader());  
        //DexClassLoader cl = new DexClassLoader(dexPath,optimizedDexOutputPath.getAbsolutePath(),null, ClassLoader.getSystemClassLoader());
        
        System.out.println(System.getenv());
        //加载类  
        try {
            Class libProviderClazz = cl.loadClass("com.example.gameengine.GameEngine");
            Constructor gameEngineConstructor = libProviderClazz.getDeclaredConstructor(new Class[]{Context.class});   
            gameEngineConstructor.setAccessible(true);   
            IGameEngine gameEngine = (IGameEngine)gameEngineConstructor.newInstance(new Object[]{context});
            return gameEngine;
        } catch (Exception exception) {  
            exception.printStackTrace();  
        }  
        return null;
	}
}
