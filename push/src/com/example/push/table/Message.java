package com.example.push.table;

public class Message {
	
		private String message_num;
		private String subject_num;
		private String context;
		
	public Message(String message_num , String subject_num , String context){
		this.message_num = message_num;
		this.subject_num = subject_num;
		this.context     = context;
	}

	public String getMessage_num() {
		return message_num;
	}

	public void setMessage_num(String message_num) {
		this.message_num = message_num;
	}

	public String getSubject_num() {
		return subject_num;
	}

	public void setSubject_num(String subject_num) {
		this.subject_num = subject_num;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
	
	
}
