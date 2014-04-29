package com.example.push;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
    	startActivity(new Intent(this,SplashActivity.class));
    	
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        
       
    }
}
