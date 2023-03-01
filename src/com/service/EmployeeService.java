package com.service;

import java.util.List;

import com.model.Employee;

public interface EmployeeService {


	Employee saveEmployee(Employee employee);
	List<Employee> loadRecentRegistEmployeeByNativeQuery();
//		Boolean terminateEmployee();
	
	
}
