package com.example.push.table;

public class Subject_Info {

	private String name ;
	private String day ;
	private String period ;
	private String time;
	private String location;
	
	public Subject_Info(String name,String day , String period , String time ,String location){
		this.name = name;
		this.day = day;
		this.period = period;
		this.time = time;
		this.location = location;
	}

	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
}
