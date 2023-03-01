package com.model;
import java.util.Date;

import javax.persistence.Column;


public class Employee {

    private Long eid;
    
    private String firstName;
    
    private String lastName;
    
    private String gender;

    private String status;
    
    private Date birthDate;

    private Integer age;

    private String active;
	
	private String createdBy;

	private Date createdOn;

	private Date updatedOn;
	
	private String updatedBy;

	public Long getEid() {
		return eid;
	}

	public void setEid(Long eid) {
		this.eid = eid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Employee(Long eid, String firstName, String lastName, String gender, String status, Date birthDate,
			Integer age, String active, String createdBy, Date createdOn, Date updatedOn, String updatedBy) {
		super();
		this.eid = eid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.status = status;
		this.birthDate = birthDate;
		this.age = age;
		this.active = active;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
	}

	public Employee(String createdBy, Date createdOn, Date updatedOn, String updatedBy) {
		super();
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
	}

	public Employee(String active, String createdBy, Date createdOn, Date updatedOn, String updatedBy) {
		super();
		this.active = active;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
	}

	public Employee() {
		super();
	}

	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", status=" + status + ", birthDate=" + birthDate + ", age=" + age + ", active=" + active
				+ ", createdBy=" + createdBy + ", createdOn=" + createdOn + ", updatedOn=" + updatedOn + ", updatedBy="
				+ updatedBy + "]";
	}
	
	
}