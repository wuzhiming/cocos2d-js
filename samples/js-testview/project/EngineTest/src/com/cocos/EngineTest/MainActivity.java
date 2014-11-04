package com.cocos.EngineTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Constructor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.tencent.smtt.export.external.interfaces.IGameEngine;

import dalvik.system.DexClassLoader;
import dalvik.system.DexFile;

public class MainActivity extends Activity {

	String fileName = "libcocos2djs.so";
	
	private Button btnCurProcessTest;
	private Button btnOtherProcessTest;
	private Button btnCurProcessTestCrash;
	private Button btnOtherProcessTestCrash;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnCurProcessTest = (Button) this.findViewById( R.id.btn_cur_process_test);
		btnOtherProcessTest = (Button) this.findViewById( R.id.btn_other_process_test);
		btnCurProcessTestCrash = (Button) this.findViewById( R.id.btn_cur_process_test_crash);
		btnOtherProcessTestCrash = (Button) this.findViewById( R.id.btn_other_process_test_crash);
		
		btnCurProcessTest.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent( MainActivity.this, CurProcessActivity.class );			
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK );	
	            startActivity(intent);  
			}
		});
		
		btnOtherProcessTest.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						Intent intent = new Intent( MainActivity.this, OtherProcessActivity.class );			
						intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK );	
			            startActivity(intent); 
					}
				});
		
		btnCurProcessTestCrash.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent( MainActivity.this, CurProcessCrashActivity.class );			
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK );	
	            startActivity(intent);  
			}
		});
		btnOtherProcessTestCrash.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//loadLibrary();
				Intent intent = new Intent( MainActivity.this, OtherProcessCrashActivity.class );			
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK );	
	            startActivity(intent); 
			}
		});
		
	}
	private void printClass(String dexPath)
	{
		// print all class from dex
        DexFile dx;
		try {
			dx = DexFile.loadDex(dexPath, File.createTempFile("opt", "dex",
			        getCacheDir()).getPath(), 0);
			ClassLoader cl = getClassLoader(); 
	        // Print all classes in the DexFile
//	        for(Enumeration<String> classNames = dx.entries(); classNames.hasMoreElements();) {
//	            String className = classNames.nextElement();
//
//	            Class clazz = dx.loadClass(className, cl); 
//	            Log.i("class &&&&&&", "" + clazz);
//	        }
			Class clazz = dx.loadClass("com.example.gameengine.GameEngine", cl);
			Constructor c1=clazz.getDeclaredConstructor(new Class[]{Context.class});   
            c1.setAccessible(true);   
            IGameEngine a1=(IGameEngine)c1.newInstance(new Object[]{this});
            setContentView(a1.game_engine_get_view());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String loadLibrary()
	{
		try{
			
	        FileInputStream fis = new FileInputStream("/sdcard/gameEngine/libcocos2djs.so");
	        File dir = this.getDir("gameEngine", Activity.MODE_PRIVATE);
	        File nf = new File(dir.getAbsolutePath() + File.separator + fileName);
	        if (nf.exists())
	        {
	        	nf.delete();
	        }
	        FileOutputStream fos = new FileOutputStream(nf);
	        byte[] buf = new byte[8 * 1014];
	        int n;
	        while ((n = fis.read(buf)) > 0)
	            fos.write(buf, 0, n);
	        fis.close();
	        fos.close();
	        
	        String lib = "cocos2djs";
	        System.load(dir.getAbsolutePath() + File.separator + fileName); 
	        //System.loadLibrary( lib );
	        return dir.getAbsolutePath();
		} catch (Exception e) {
	        e.printStackTrace();
	    }
		return "";
	}
}
