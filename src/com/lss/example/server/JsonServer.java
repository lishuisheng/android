package com.lss.example.server;

import java.util.ArrayList;
import java.util.List;

public class JsonServer {

	public JsonServer() {
		// TODO Auto-generated constructor stub
	}
	
	public Person getPerson(){
		Person person = new Person(1001, "��ˮʤ", "����");
		return person;
	}

	public List<Person> getListPerson(){
		
		List<Person> list = new ArrayList<Person>();
		Person person1 = new Person(1001, "��ˮʤ", "����");
		Person person2 = new Person(1002, "��ˮʤ", "����");
		list.add(person1);
		list.add(person2);
		
		return list;
		
	}
}
