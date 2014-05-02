package com.example.push.table;

public class Notice {

	private Person person;
	private String date;
	private String title;
	private String content;
	
	public Notice(Person person, String date, String title, String content){
		this.person = person;
		this.date = date;
		this.title = title;
		this.content = content;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
