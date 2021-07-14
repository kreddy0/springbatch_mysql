package com.shi.springbatch.model;

public class Customer {
	
	private String phone;
	
	
	private String last_name;
	
	
	private String first_name;
	
	
	private String title;
	
	
	private String email;
	
	
	private String designation;
	
	public Customer() {
		
	}

	
	public Customer(String phone, String last_name, String first_name, String title, String email, String designation) {
		super();
		this.phone = phone;
		this.last_name = last_name;
		this.first_name = first_name;
		this.title = title;
		this.email = email;
		this.designation = designation;
	}


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Override
	public String toString() {
		return "Customer [phone=" + phone + ", last_name=" + last_name + ", first_name=" + first_name + ", title="
				+ title + ", email=" + email + ", designation=" + designation + "]";
	}
	
	
	
	
}
