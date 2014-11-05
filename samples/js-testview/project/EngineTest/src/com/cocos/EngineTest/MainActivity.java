package com.cocos.EngineTest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	String fileName = "libcocos2djs.so";
	
	private Button btnCurProcessTest;
	private Button btnOtherProcessTest;
	private Button btnCurProcessTestCrash;
	private Button btnOtherProcessTestCrash;
	
	private boolean newCreateFlag = false;
	
	private TextView tvCurStatus;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e("MainActivity", "MainActivity onCreate");
		newCreateFlag = true;
		setContentView(R.layout.activity_main);
		
		btnCurProcessTest = (Button) this.findViewById( R.id.btn_cur_process_test);
		btnOtherProcessTest = (Button) this.findViewById( R.id.btn_other_process_test);
		btnCurProcessTestCrash = (Button) this.findViewById( R.id.btn_cur_process_test_crash);
		btnOtherProcessTestCrash = (Button) this.findViewById( R.id.btn_other_process_test_crash);
		
		tvCurStatus = (TextView) this.findViewById( R.id.tv_curstatus);
		
		btnCurProcessTest.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				setAllButtonEnable(false);
				Intent intent = new Intent( MainActivity.this, CurProcessActivity.class );			
	            startActivity(intent);  
			}
		});
		
		btnOtherProcessTest.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				setAllButtonEnable(false);
				Intent intent = new Intent( MainActivity.this, OtherProcessActivity.class );				
	            startActivity(intent); 
			}
		});
		
		btnCurProcessTestCrash.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				setAllButtonEnable(false);
				Intent intent = new Intent( MainActivity.this, CurProcessCrashActivity.class );		
	            startActivity(intent);  
			}
		});
		btnOtherProcessTestCrash.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				setAllButtonEnable(false);
				Intent intent = new Intent( MainActivity.this, OtherProcessCrashActivity.class );
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
		Log.e("MainActivity", "MainActivity onResume");
		setAllButtonEnable(true);
		if (newCreateFlag)
		{
			String strTemp = "本界面重新启动了";
			this.setTitle(strTemp);
			tvCurStatus.setText(strTemp);
			newCreateFlag = false;
		}
		else
		{
			String strTemp = "本应用处于从后台被唤醒了";
			this.setTitle(strTemp);
			tvCurStatus.setText(strTemp);
		}
		
	}
}
