package com.example.push.table;

public class Student extends Person{
	
	private String phone;
	private String regid;
	
	
	
	
	public Student(String id, String pw){
		super(id, pw);
	}
	
	public Student(String id , String pw , String name , String phone , String regid ){
		super(id,pw,name);
		this.phone = phone;
		this.regid = regid;
	}
	
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRegid() {
		return regid;
	}
	public void setRegid(String regid) {
		this.regid = regid;
	}
	
	
	
	
}
