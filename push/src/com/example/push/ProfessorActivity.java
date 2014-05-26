package com.example.push;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.push.R;
import com.example.push.db.DBManager;
import com.example.push.professor.fragment.CancleClassFragment;
import com.example.push.professor.fragment.GeneralFragment;
import com.example.push.professor.fragment.SupplementFragment;
import com.example.push.professor.fragment.TaskFragment;
import com.example.push.table.Globals;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.storage.OnObbStateChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class ProfessorActivity extends PreferenceActivity implements OnClickListener {

	final String TAG = "ProfessorActivity";
	 
	private Globals globals;
	
	private TextView notify ;
	private Button general_btn;
	private Button cancel_a_class_btn;
	private Button supplement_btn;
	private Button task_btn;
	
	int mCurrentFragmentIndex;
	public final static int FRAGMENT_GENERAL = 0;
	public final static int FRAGMENT_CANCLE_A_CLASS = 1;
	public final static int FRAGMENT_SUPPLEMENT = 2;
	public final static int FRAGMENT_TASK = 3;	
	
	private String prof_id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_professor);

		globals = Globals.getInstance();
		
		prof_id = getIntent().getStringExtra("id");
		
		
		setSlideHolder();
		setLayout();
		
		setSublist();
		
		mCurrentFragmentIndex = FRAGMENT_GENERAL;

		fragmentReplace(mCurrentFragmentIndex);
	}
	
	public void setLayout(){
		
		general_btn = (Button)findViewById(R.id.general_btn);
		cancel_a_class_btn = (Button)findViewById(R.id.cancel_a_class_btn);
		supplement_btn = (Button)findViewById(R.id.supplement_btn);
		task_btn = (Button)findViewById(R.id.task_btn);
		notify = (TextView)findViewById(R.id.notify);
		
		general_btn.setOnClickListener(this);
		cancel_a_class_btn.setOnClickListener(this);
		supplement_btn.setOnClickListener(this);
		task_btn.setOnClickListener(this);		
	}
	
	private void setSublist(){
		DBManager db = new DBManager(getApplication());
		globals.getPerson().getpList().get(prof_id).setSubjectList(db.setProfSubList(prof_id));
		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		 switch (v.getId()) {
	        case R.id.general_btn:
	            mCurrentFragmentIndex = FRAGMENT_GENERAL;
	            fragmentReplace(mCurrentFragmentIndex);
	            break;
	        case R.id.cancel_a_class_btn:
	        	mCurrentFragmentIndex = FRAGMENT_CANCLE_A_CLASS;
	            fragmentReplace(mCurrentFragmentIndex);
	            break;
	        case R.id.supplement_btn:
	            mCurrentFragmentIndex = FRAGMENT_SUPPLEMENT;
	            fragmentReplace(mCurrentFragmentIndex);
	            break;
	        case R.id.task_btn: 
	            mCurrentFragmentIndex = FRAGMENT_TASK;
	            fragmentReplace(mCurrentFragmentIndex);
	            break;
	        }
	}
	
	   public void fragmentReplace(int reqNewFragmentIndex) {
		   
	        Fragment newFragment = null;
	 
	        Log.d(TAG, "fragmentReplace " + reqNewFragmentIndex);
	 
	        newFragment = getFragment(reqNewFragmentIndex);
	 
	        // replace fragment
	        final FragmentTransaction transaction = getFragmentManager().beginTransaction();
	        transaction.replace(R.id.ll_fragment, newFragment);
	 
	        // Commit the transaction
	        transaction.commit();
	 
	    }
	 
	    private Fragment getFragment(int idx) {
	        Fragment newFragment = null;
	 
	        switch (idx) {
	        case FRAGMENT_GENERAL:
	        	notify.setText("일반 공지");
	            newFragment = new GeneralFragment();
	            break;
	        case FRAGMENT_CANCLE_A_CLASS:
	        	notify.setText("휴강 공지");
	            newFragment = new CancleClassFragment();
	            break;
	        case FRAGMENT_SUPPLEMENT:
	        	notify.setText("보강 공지");
	           newFragment = new SupplementFragment();
	            break;
	        case FRAGMENT_TASK:
	        	notify.setText("과제 공지");
	            newFragment = new TaskFragment();
	            break;
	        default:
	            Log.d(TAG, "Unhandle case");
	            break;
	        }
	      
	        Bundle bundle = new Bundle();
	        bundle.putString("id",prof_id);
	        newFragment.setArguments(bundle);
	        return newFragment;
	    }
	
		
}
