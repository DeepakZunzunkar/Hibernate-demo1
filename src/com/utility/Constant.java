package com.utility;

public interface Constant {

	public enum Gender {

		MALE("M"),
		FEMALE("F");
		
		private Gender() {
			System.out.println("called for each constant");
		}
		
		private String genderValue; 
		
		private Gender(String gender) {
			this.genderValue=gender;
		}
		public String getGenderValue() {
			return this.genderValue;
		}
	}
	
	public enum EmployeeStatus{
		
		ACTIVE("A"),
		ONLEAVE("L"),
		TERMINATED("T"),
		DECEASED("D");
		
		private String employeeStatusCode;
		
		private EmployeeStatus(String employeeStatus) {
			this.employeeStatusCode = employeeStatus;
		}

		public String getEmployeeStatusCode() {
			return employeeStatusCode;
		}
		
		public int indexValue() {
			return ordinal();
		}
	}
	
}
