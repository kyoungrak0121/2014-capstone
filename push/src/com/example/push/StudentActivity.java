package com.example.push;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class StudentActivity extends PreferenceActivity{
	@Override
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_student);
		setSlideHolder();
		
		Context mContext = getApplicationContext();//view�� alert �̸� �˾����� �� ��ư�� ������ �˾�â�� �ߴ� ����
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
		
        View layout = inflater.inflate(R.layout.dialog,(ViewGroup) findViewById(R.id.popup));
        AlertDialog.Builder aDialog = new AlertDialog.Builder(StudentActivity.this);
    	
        aDialog.setTitle("�������� ���"); //Ÿ��Ʋ�� ����
        aDialog.setView(layout); //dialog.xml ������ ��� ����
        
        aDialog.setNegativeButton("�ݱ�", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        //�˾�â ����
        AlertDialog ad = aDialog.create();
        ad.show();//������!
	}
}	
	

