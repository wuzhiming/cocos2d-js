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
				setAllButtonEnable(false);
				Intent intent = new Intent( MainActivity.this, CurProcessActivity.class );			
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK );	
	            startActivity(intent);  
			}
		});
		
		btnOtherProcessTest.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				setAllButtonEnable(false);
				Intent intent = new Intent( MainActivity.this, OtherProcessActivity.class );			
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK );	
	            startActivity(intent); 
			}
		});
		
		btnCurProcessTestCrash.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				setAllButtonEnable(false);
				Intent intent = new Intent( MainActivity.this, CurProcessCrashActivity.class );			
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK );	
	            startActivity(intent);  
			}
		});
		btnOtherProcessTestCrash.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				setAllButtonEnable(false);
				Intent intent = new Intent( MainActivity.this, OtherProcessCrashActivity.class );			
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK );	
	            startActivity(intent); 
	            
			}
		});
		
	}
	
	private void setAllButtonEnable(boolean enable)
	{
		btnCurProcessTest.setEnabled(enable);
		btnOtherProcessTest.setEnabled(enable);
		btnCurProcessTestCrash.setEnabled(enable);
		btnOtherProcessTestCrash.setEnabled(enable);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		setAllButtonEnable(true);
	}
}
