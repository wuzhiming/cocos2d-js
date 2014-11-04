package com.cocos.EngineTest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class OtherProcessCrashActivity extends Activity {
	private Button crashButton = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.button_layout);
		((Button)this.findViewById(R.id.btn_crash)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				crashButton.setText("");
			}
		});
		
	}
}
