package com.cocos.EngineTest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import com.tencent.smtt.export.external.interfaces.IGameEngine;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CurProcessCrashActivity extends Activity {
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
