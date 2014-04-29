package com.example.push;

import java.security.PublicKey;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_splash);

		initialize();
	}

	protected void initialize() {
		Handler handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				finish();
			}
		};
		
		handler.sendEmptyMessageDelayed(0, 3000); // 5√ ∞£ µÙ∑π¿Ã
	}
}
