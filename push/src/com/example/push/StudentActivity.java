package com.example.push;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;



public class StudentActivity extends SuperActivity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_student);
		
		SuperActivity.context = this;	
		
		setSlideHolder();
		setLoginText();
			
		Context mContext = getApplicationContext();//view가 alert 이면 팝업실행 즉 버튼을 누르면 팝업창이 뜨는 조건
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
		
        View layout = inflater.inflate(R.layout.st_notify_dialog,(ViewGroup) findViewById(R.id.popup));
        AlertDialog.Builder aDialog = new AlertDialog.Builder(StudentActivity.this);
    	
        aDialog.setTitle("공지사항 목록"); //타이틀바 제목
        aDialog.setView(layout); //dialog.xml 파일을 뷰로 셋팅
        
        aDialog.setNegativeButton("닫기", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        //팝업창 생성
        AlertDialog ad = aDialog.create();
        ad.show();//보여줌!
        
        // 식단 확인
        Button button =(Button)findViewById(R.id.meal);
        button.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){
        		
        		startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://m.mju.ac.kr/mbs/mjumob/jsp/subview.jsp?id=mjumob_050000000000")));
        	}
        });
        
	}
}	
	

