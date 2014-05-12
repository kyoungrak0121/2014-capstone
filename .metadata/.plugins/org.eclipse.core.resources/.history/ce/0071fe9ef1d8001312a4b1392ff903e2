package com.example.push.table;

import java.util.ArrayList;
import java.util.List;


public class Globals {
	private static Globals globals;
	private Person person = null;
	//일괄전송에 필요한 List 변수
	private List<String> registrationIds = new ArrayList<String>();
		
	public List<String> getRegistrationIds() {
		return registrationIds;
	}
	
	public void setRegistrationIds(List<String> registrationIds) {
		this.registrationIds = registrationIds;
	}	
	
	public static synchronized Globals getInstance() {
		if (globals == null) {
			globals = new Globals();
		}
		return globals;
	}
	public Person getPerson(){
			return person;
	}
	public void setPerson(Person person){
		this.person = person;
	}
	

}
