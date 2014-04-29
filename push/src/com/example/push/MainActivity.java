package com.example.push;

import org.w3c.dom.Text;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText login_id ;
	private EditText login_pwd ;
	private Button login_btn ;
	String student[][] = { { "60090001", "0001" }, { "60090002", "0002" } };
	String professor[][] = { { "admin", "admin" }, { "1234", "1234"} };
	private Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		intent = new Intent(this, SplashActivity.class);
		startActivity(intent);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);

		login_id = (EditText) findViewById(R.id.login_id);
		login_pwd = (EditText) findViewById(R.id.login_pwd);
		login_btn = (Button) findViewById(R.id.login_btn);
		
	
		
		login_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < 2; j++) {
						if (student[i][j].equals(login_id.getText().toString())) { // student
																					// id

							if (student[i][j + 1].equals(login_pwd.getText()
									.toString())) {
								Toast.makeText(
										getApplicationContext(),
										"test id : " + login_id.getText()
												+ "\n" + "test pwd : "
												+ login_pwd.getText(),
										Toast.LENGTH_SHORT).show();
								break;
							} else {
								Toast.makeText(getApplicationContext(),
										"fail pwd" + login_pwd.getText(),
										Toast.LENGTH_SHORT).show();
								break;
							}
						} else if (professor[i][j].equals(login_id.getText().toString())) { // professor
																							// id
							if (professor[i][j + 1].equals(login_pwd.getText()
									.toString())) {
								Toast.makeText(
										getApplicationContext(),
										"test id : " + login_id.getText()
												+ "\n" + "test pwd : "
												+ login_pwd.getText(),
										Toast.LENGTH_SHORT).show();
								intent = new Intent(getApplicationContext(), ProfessorActivity.class);
								startActivity(intent);
								break;
							} else {
								Toast.makeText(getApplicationContext(),
										"fail pwd" + login_pwd.getText(),
										Toast.LENGTH_SHORT).show();
								break;
							}
						} else {
							Toast.makeText(getApplicationContext(),
									"fail id" + login_pwd.getText(),
									Toast.LENGTH_SHORT).show();
							break;
						}
					}
				}
			}
		});
		
	
	}
}
