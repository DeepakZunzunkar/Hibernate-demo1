package com.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;
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
	List<Employee> list = new ArrayList<Employee>();

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

		
		session=sf.openSession();
		tx=session.beginTransaction();
		try {
			
			//Hibernate provide option to execute native SQL queries through the use of SQLQuery object.
			//Hibernate SQL Query is very handy when we have to execute database vendor specific queries 
			//that are not supported by Hibernate API. For example query hints or the CONNECT keyword in Oracle Database.
			//For normal scenarios, Hibernate SQL query is not the recommended approach because we loose benefits related 
			//to hibernate association and hibernate first level cache.
			
			//METHOD 1 :  Using LIMIT clause in descending order
			SQLQuery query =session.createSQLQuery("SELECT eid,firstname,lastname,createdon,status,birthDate,salary FROM adpemployee a Order by EID DESC LIMIT 5");
			
			//METHOD 2 :  Using Relational Operator and COUNT function.
//			SQLQuery query =session.createSQLQuery("SELECT eid,firstname,lastname,createdon,status,birthDate FROM adpemployee where EID > (select count(*) from adpemployee)-5");
			
			List<Object[]> rows = query.list();
			//list() method returns the List of Object array, we need to explicitly parse them to double, long etc. 
			for(Object[] row : rows){
				Employee emp = new Employee();
				
				emp.setEid(Long.parseLong(row[0].toString()));
				emp.setFirstName(row[1].toString());
				emp.setLastName(row[2].toString());
				emp.setCreatedOn(DateUtils.convertStringToJUtilDateTime(row[3].toString()));
				emp.setStatus(row[4]!=null?row[4].toString():"");
				emp.setBirthDate(row[5]!=null?DateUtils.convertStringToJUtilDateTime(row[5].toString()):null);
				emp.setSalary(row[6]!=null?Double.parseDouble(row[6].toString()):0.00);
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

	@Override
	public List<Employee> getAllEmployees() {
		
		//HQL or Hibernate Query Language is the object-oriented query language of Hibernate Framework.
		//HQL is very similar to SQL except that we use Objects instead of table names, that makes it more close to object oriented programming.
		
		try {
			
			session=sf.openSession();
			tx=session.beginTransaction();
			
			// HQL is case-insensitive except for java class and variable names. 
			// So SeLeCT is the same as sELEct is the same as SELECT, but com.model.Employee is not same as com.model.EMPLOYEE. 
			Query query=session.createQuery("from Employee");
			list = query.list();
			
			tx.commit();
			
		} catch (Exception e) {
			if(tx!=null) tx.rollback();
			throw e;
		}finally {
			session.close();
		}
		return list;
	}
	
	@Override
	public Employee findById(long eid) {
		
		try {
			session= sf.openSession();
			tx = session.beginTransaction();
			employee= session.get(Employee.class,eid);
			
		} catch (Exception e) {
			if(tx!=null) tx.rollback();
			throw e;
		}finally {
			session.close();
		}
		return employee;
	}

	@Override
	public void updateEmployee(Employee empTrn) {
		try {
			session= sf.openSession();
			tx = session.beginTransaction();
			session.update(empTrn);
			tx.commit();
		} catch (Exception e) {
			if(tx!=null) tx.rollback();
			throw e;
		}finally {
			session.close();
		}
	}

	@Override
	public void deleteEmployee(Employee empTrn) {
		try {
			session= sf.openSession();
			tx = session.beginTransaction();
			session.delete(empTrn);
			tx.commit();
		} catch (Exception e) {
			if(tx!=null) tx.rollback();
			throw e;
		}finally {
			session.close();
		}
		
	}



}
