package com.example.push.table;

import java.util.Map;

public class Person {
	
	private String id;
	private String pw;

	private Map<String,Student> sList;
	private Map<String,Professor> pList;
	
	public Person(){
		this.id = null;
		this.pw = null;
	}
	public Person(String id, String pw){
		this.id = id;
		this.pw = pw;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}
	public Map<String, Student> getsList() {
		return sList;
	}

	public void setsList(Map<String, Student> sList) {
		this.sList = sList;
	}

	public Map<String, Professor> getpList() {
		return pList;
	}

	public void setpList(Map<String, Professor> pList) {
		this.pList = pList;
	}

}
