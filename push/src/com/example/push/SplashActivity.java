package com.example.push;

<<<<<<< HEAD
=======
import java.security.PublicKey;
import java.util.Date;

>>>>>>> cc06111baf0182d378861e4ae713a77b674b6d99
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
<<<<<<< HEAD
		Handler handler = new Handler(){
=======
		Handler handler = new Handler() {
>>>>>>> cc06111baf0182d378861e4ae713a77b674b6d99

			@Override
			public void handleMessage(Message msg) {
				finish();
			}
		};
		
		handler.sendEmptyMessageDelayed(0, 3000); // 5√ ∞£ µÙ∑π¿Ã
	}
}
