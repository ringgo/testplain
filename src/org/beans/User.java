package org.beans;

public class User {
	private int id;
	private String name;
	private String sex;
	private Integer age;
	private String career;

	public User() {
		super();
	}

	public User(Integer id, String name, String sex, Integer age, String career) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.career = career;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[" + id + ", " + name + ", " + sex + ", " + age + ", "
				+ career + "]");
		return sb.toString();
	}

}
