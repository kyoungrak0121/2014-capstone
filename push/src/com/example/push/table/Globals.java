package com.example.push.table;

import java.util.Map;

public class Globals {
	private static Globals globals;
	private Person person;

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
