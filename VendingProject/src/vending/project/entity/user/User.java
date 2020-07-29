package vending.project.entity.user;

import java.io.Serializable;

public class User implements Serializable {
	
	private int id;
	private String name;
	private int age;
	private String phone;
	private String email;
	private String userName = "null";
	private String passWord;
	private int money;
	private int role_id;
	
	public User(int id, String name, int age, String phone, String email, String userName, String passWord, int money,
			int role_id) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.phone = phone;
		this.email = email;
		this.userName = userName;
		this.passWord = passWord;
		this.money = money;
		this.role_id = role_id;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public User(int id, String name, int age, String phone, String email, String userName, String passWord, int money) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.phone = phone;
		this.email = email;
		this.userName = userName;
		this.passWord = passWord;
		this.money = money;
	}
	public User() {
		super();
		
	}
	
	public User(String name, int age, String email, String phone, String street, String city){
        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.money = money;
    }
	
	public User(String userName, String passWord){
        this.userName = userName;
        this.passWord = passWord;
    }
	
	public int getId() {
		return id;
	}
	public int setId(int id) {
		return this.id = id;
	}
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	
	@Override
	public String toString() {
		return "Name: " + name + "\n"
				+ "Age: " + age + "\n"
				+ "Phone: " + phone + "\n"
				+ "Account: " + userName + "\n"
				+ "Money: " + money;
	}
	
	public void display() {
		System.out.println(this);
	}
	
	
	

}
