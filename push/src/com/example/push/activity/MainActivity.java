package com.example.push.activity;

import java.security.KeyStore.Entry;
import java.util.Iterator;
import java.util.Set;
import java.util.Map;

import com.example.push.R;


import com.example.push.table.Globals;
import com.example.push.table.Person;
import com.example.push.table.Professor;
import com.example.push.table.Student;





import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends PreferenceActivity {

	private Globals globals;
	
	private EditText login_id ;
	private EditText login_pwd ;
	private Button login_btn ;
	private Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		intent = new Intent(this, SplashActivity.class);
		startActivity(intent);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);

		globals = Globals.getInstance();
		
		login_id = (EditText) findViewById(R.id.login_id);
		login_pwd = (EditText) findViewById(R.id.login_pwd);
		login_btn = (Button) findViewById(R.id.login_btn);
		
	
		
		login_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				authCheck();
			
			}
		});
	}
	   
    private void authCheck(){
    	//Toast.makeText(this, "test", Toast.LENGTH_LONG).show();
    	
    	Person person = globals.getPerson();
    	String id = login_id.getText().toString();
    	String pw = login_pwd.getText().toString();
    	  	
    	if(person.getsList().containsKey(id) || person.getpList().containsKey(id)){
    	
    		Map<String,Student> sList = person.getsList();
    		Map<String,Professor> pList = person.getpList();
    		
    		if(sList.get(id) != null){
    			if (sList.get(id).getId().equals(id) && sList.get(id).getPw().equals(pw)) {
    			 
    				intent = new Intent(getApplicationContext(),StudentActivity.class);
    				startActivity(intent);
    		
    			}
    		}
    		if(pList.get(id) != null){
    			if (pList.get(id).getId().equals(id) && pList.get(id).getPw().equals(pw)) { 
    				intent = new Intent(getApplicationContext(),ProfessorActivity.class);
    				startActivity(intent);
    			}
    		}
    	}else{
    		Toast.makeText(this, "ID를 확인해 주세요.", Toast.LENGTH_LONG).show();
    	}
    }
}
