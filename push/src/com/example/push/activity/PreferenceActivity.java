package com.example.push.activity;

import java.util.HashMap;
import java.util.Map;

import com.example.push.table.Person;
import com.example.push.table.Professor;
import com.example.push.table.Student;

import android.app.Activity;
import android.content.SharedPreferences;
import android.widget.Toast;

public class PreferenceActivity extends Activity{

	private final String size_student = "s_student";
	private final String size_professor = "s_professor";
	//private final String size_notice = "s_notice";
	
	public boolean insert_student(String id, String pw){
		int size = GetPrefInt(size_student);
		
		try{
			SharedPreferences prefs = getSharedPreferences("student", Activity.MODE_PRIVATE);
			SharedPreferences.Editor editor = prefs.edit();
			editor.putString("id_" + size, id);
			editor.putString("pw_" + size, pw);
			editor.commit();
		} catch(Exception e){
			return false;
		}
		
		SetPref(size_student, size+1);
		return true;
	}
	
	public boolean insert_professor(String id, String pw){
		int size = GetPrefInt(size_professor);
		
		try{
			SharedPreferences prefs = getSharedPreferences("professor", Activity.MODE_PRIVATE);
			SharedPreferences.Editor editor = prefs.edit();
			editor.putString("id_" + size, id);
			editor.putString("pw_" + size, pw);

			editor.commit();
		} catch(Exception e){
			return false;
		}
		
		SetPref(size_professor, size+1);
		return true;
	}
	
	public boolean delete_student(String id){
		
		final int size = GetPrefInt(size_student);
		try{
			SharedPreferences prefs = getSharedPreferences("student",
					Activity.MODE_PRIVATE);
			SharedPreferences.Editor editor = prefs.edit();
			
			for( int i = 0; i < size; i++ ){
				String src = prefs.getString("id_" + i, null);
				if( src.equals(id) == true ){
					editor.remove("id_" + i);
					editor.remove("pw_" + i);
					
					for( int j = i+1; j <= size; j++ ){
						editor.putString("id_" + (j-1), prefs.getString("id_"+j, null));
						editor.putString("pw_" + (j-1), prefs.getString("pw_"+j, null));
					}
				}
			}
			editor.commit();
		} catch(Exception e){
			return false;
		}
		
		SetPref(size_student, size-1);
		return true;
	}
	
	public boolean delete_professor(String id){
		final int size = GetPrefInt(size_professor);
		
		try{
			SharedPreferences prefs = getSharedPreferences("professor", Activity.MODE_PRIVATE);
			SharedPreferences.Editor editor = prefs.edit();
			
			for( int i = 0; i < size; i++ ){
				String src = prefs.getString("id_" + i, null);
				if( src.equals(id) == true ){
					editor.remove("id_" + i);
					editor.remove("pw_" + i);
					
					for( int j = i+1; j <= size; j++ ){
						editor.putString("id_" + (j-1), prefs.getString("id_" + j, null));
						editor.putString("pw_" + (j-1), prefs.getString("pw_" + j, null));
					}
				}
			}
			editor.commit();
		} catch(Exception e){
			return false;
		}
		

		SetPref(size_student, size-1);
		return true;
	}
//	public boolean update_student(String id, String pw){
//		
//	}
//	
//	public boolean update_professor(String id, String pw){
//		
//	}
	
	
	public Person read_person(){
		Person person = new Person();
		person.setsList(read_student());
		person.setpList(read_professor());
		return person;
	}
	
	public Map<String,Student> read_student(){
		Map<String,Student> sList = new HashMap<String,Student>();
		
		int size = GetPrefInt(size_student);
		
		SharedPreferences prefs = getSharedPreferences("student", Activity.MODE_PRIVATE);
		
		for( int i = 0; i < size; i++ ){
			
			String id = prefs.getString("id_" + i, null);		
			String pw = prefs.getString("pw_" + i, null);
	
			sList.put(id,new Student(id,pw));
		}
		
		return sList;
	}
	
	public  Map<String,Professor> read_professor(){
		 Map<String,Professor> pList = new HashMap<String,Professor>();
		
		int size = GetPrefInt(size_professor);
		
		SharedPreferences prefs = getSharedPreferences("professor", Activity.MODE_PRIVATE);
		
		for( int i = 0; i < size; i++ ){
			String id = prefs.getString("id_" + i, null);
			String pw = prefs.getString("pw_" + i, null);

			pList.put(id,new Professor(id, pw));
		}
		return pList;
	}
	
	public void SetPref(String key, int value) {
		SharedPreferences prefs = getSharedPreferences("noname",
				Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	public int GetPrefInt(String key) {
		SharedPreferences prefs = getSharedPreferences("noname",
				Activity.MODE_PRIVATE);
		return prefs.getInt(key, 0);
	}

	public void DeletePref(String key) {
		SharedPreferences prefs = getSharedPreferences("noname",
				Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.remove(key);
		editor.commit();
	}
	
}
