package com.example.push.table;

import java.util.Map;

public class Professor extends Person{
	
	private Map<String, Subject_Info> subjectList;
	
	public Professor(String id, String pw , String name ){
		super(id, pw,name);
	}

	public Map<String, Subject_Info> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(Map<String, Subject_Info> subjectList) {
		this.subjectList = subjectList;
	}
	
}
