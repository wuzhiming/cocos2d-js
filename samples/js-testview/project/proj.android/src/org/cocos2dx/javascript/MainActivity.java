package org.cocos2dx.javascript;

import org.cocos2dx.helloWorld.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button btnCurProcessTest;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnCurProcessTest = (Button) this.findViewById( R.id.btn_cur_process_test);
		
		
		btnCurProcessTest.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Intent intent = new Intent( MainActivity.this, CocosPlayJSActivity.class );
				intent.putExtra("COCOSPLAY_JS_GAME_PATH", "/mnt/sdcard/gameEnginePlay");
				intent.putExtra("COCOSPLAY_JS_LIB_NAME", "cocos2djs");
	            startActivity(intent);  
			}
		});
	}

}
