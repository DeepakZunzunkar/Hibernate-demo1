package com.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hibernateUtility.HibernateUtility;
import com.model.Employee;
import com.service.EmployeeService;
import com.utility.DateUtils;

public class EmployeeServiceImpl implements EmployeeService{
	
	static Employee employee=null;
	static Scanner sc=new Scanner(System.in);
	static SessionFactory sf=HibernateUtility.getHibernateConnection();
	static Session session=null;
	static Transaction tx=null;
	static Integer rs=0;
	

	@Override
	public Employee saveEmployee(Employee employee) {
		try {
			
			session = sf.openSession();
			tx = session.beginTransaction();
			
			SQLQuery query =session.createSQLQuery("SELECT * FROM adpemployee a where a.firstname='"+employee.getFirstName()+"' and a.lastname='"+employee.getLastName()+"'");
			Object obj = query.uniqueResult();
			if(obj ==null) {
				session.save(employee);
			}
			tx.commit();
			
		}catch (Exception e) {
			if(tx!=null) tx.rollback();
			throw e;
		}finally {
			session.close();
		}
		return employee;
	}

	@Override
	public List<Employee> loadRecentRegistEmployeeByNativeQuery() {

		List<Employee> list = new ArrayList<Employee>();
		session=sf.openSession();
		tx=session.beginTransaction();
		try {
			
			//Hibernate provide option to execute native SQL queries through the use of SQLQuery object.
			//Hibernate SQL Query is very handy when we have to execute database vendor specific queries 
			//that are not supported by Hibernate API. For example query hints or the CONNECT keyword in Oracle Database.
			//For normal scenarios, Hibernate SQL query is not the recommended approach because we loose benefits related 
			//to hibernate association and hibernate first level cache.
			
			//METHOD 1 :  Using LIMIT clause in descending order
			SQLQuery query =session.createSQLQuery("SELECT eid,firstname,lastname,createdon,status,birthDate FROM adpemployee a Order by EID DESC LIMIT 5");
			
			//METHOD 2 :  Using Relational Operator and COUNT function.
//			SQLQuery query =session.createSQLQuery("SELECT eid,firstname,lastname,createdon,status,birthDate FROM adpemployee where EID > (select count(*) from adpemployee)-5");
			
			List<Object[]> rows = query.list();
			for(Object[] row : rows){
				Employee emp = new Employee();
				
				emp.setEid(Long.parseLong(row[0].toString()));
				emp.setFirstName(row[1].toString());
				emp.setLastName(row[2].toString());
				emp.setCreatedOn(DateUtils.convertStringToJUtilDateTime(row[3].toString()));
				emp.setStatus(row[4].toString());
				emp.setBirthDate(row[5]!=null?DateUtils.convertStringToJUtilDateTime(row[5].toString()):null);
				
				list.add(emp);
				
			}	
			tx.commit();
			
		}catch (Exception e) {
		     if (tx!=null) tx.rollback();
		     throw e;
		}finally {
			 session.close();
		}
		return list;
	}

}