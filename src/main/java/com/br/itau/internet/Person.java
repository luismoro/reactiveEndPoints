package com.br.itau.internet;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by luismoro on 12/12/16.
 */
@Document
class Person {

	@Id
	private String id;

	private String name;

	private int age;

	public Person() {
	}

    public Person(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

	public Person(String id, String name, Integer age, Person p) {
		this.id = id;

		if (name == null) {
			this.name = p.getName();
		}
		else {
			this.name = name;
		}

		if (age == null) {
			this.age = p.getAge();
		}
		else {
			this.age = age;
		}
	}

	@Override
	public String toString() {
		return "Person{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", age=" + age +
				'}';
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

//    public static class Builder  extends Person{
//
//        public Person build(){
//            return
//        }
//    }

}
