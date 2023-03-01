package com.service;

import java.util.List;

import com.model.Employee;

public interface EmployeeService {


	Employee saveEmployee(Employee employee);
	void updateEmployee(Employee empTrn);
	void deleteEmployee(Employee empTrn);
	List<Employee> loadRecentRegistEmployeeByNativeQuery();
//		Boolean terminateEmployee();
	Employee findById(long eid);
	List<Employee> getAllEmployees();
	
}
