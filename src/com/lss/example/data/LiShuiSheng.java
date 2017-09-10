package com.lss.example.data;

public class LiShuiSheng {

	public LiShuiSheng() {
		// TODO Auto-generated constructor stub
	}
	
	public LiShuiSheng(String name, int age, int weigh){
		this.name = name;
		this.age = age;
		this.weigh = weigh;
	}
	
	String name = null;
	int age;
	int weigh;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getWeigh() {
		return weigh;
	}
	public void setWeigh(int weigh) {
		this.weigh = weigh;
	}

}
