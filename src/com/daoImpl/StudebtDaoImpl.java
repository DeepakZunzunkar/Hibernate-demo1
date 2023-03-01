package com.daoImpl;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hibernateUtility.HibernateUtility;
import com.model.Student;

public class StudebtDaoImpl
{
	static Student student=null;
	static Scanner sc=new Scanner(System.in);
	static SessionFactory sf=HibernateUtility.getHibernateConnection();
	static Session session=null;
	static Transaction tx=null;
	
	
	static Integer rs=0;
	
	public static int add()
	{
		//A typical transaction should use the following idiom:
		try {

			student=new Student();
			
			System.out.println("Enter Name :");
			student.setName(sc.next());
			
			System.out.println("Enter Address :");
			student.setAddress(sc.next());
			
			session=sf.openSession();
			tx=session.beginTransaction();
			
			//return last inserted record id
			rs=(Integer)session.save(student);
			
			tx.commit();
		
		}catch (Exception e) {
		     if (tx!=null) tx.rollback();
		     throw e;
		}finally {
			 session.close();
			//sf.close();
		 }
		 
		
		return rs;
	}
	public static int update()
	{
		try {
			student=new Student();
			
			System.out.println("You should Know ID :");
			
			System.out.println("Enter ID :");
			student.setId(sc.nextInt());
			
			System.out.println("Enter Name :");
			student.setName(sc.next());
			
			System.out.println("Enter Address :");
			student.setAddress(sc.next());
			
			session=sf.openSession();
			tx=session.beginTransaction();
			
			session.update(student);
			
			tx.commit();
			
			System.out.println("Record Updated Successfully..");
			
		}catch (Exception e) {
		     if (tx!=null) tx.rollback();
		     throw e;
		}finally {
			 session.close();
			//sf.close();
		 }
		
		return 0;
	}
	
	public static int  delete()
	{
		try {
			student=new Student();
			
			System.out.println("You should Know ID :");
			
			System.out.println("Enter ID :");
			student.setId(sc.nextInt());
			
			session=sf.openSession();
			tx=session.beginTransaction();
			
			session.delete(student);
			
			tx.commit();
			System.out.println("Record Deleted Successfully..");
			
		}catch (Exception e) {
		     if (tx!=null) tx.rollback();
		     throw e;
		}finally {
			 session.close();
			//sf.close();
		 }
		return 0;
	}
	
	public static Student search()
	{
		try {
			System.out.println("Enter ID :");
			int id=sc.nextInt();
			
			session=sf.openSession();
			student=session.get(Student.class,id);
			
		}catch (Exception e) {
		     if (tx!=null) tx.rollback();
		     throw e;
		}finally {
			 session.close();
			//sf.close();
		 }
		return student;
	}
	
	public static List<Student> getAllRecord()
	{
		List<Student> slist = null;
		try {
			
			session=sf.openSession();
			Criteria criteria=session.createCriteria(Student.class);
			slist=criteria.list();
		
		}catch (Exception e) {
		     if (tx!=null) tx.rollback();
		     throw e;
		}finally {
			 session.close();
			//sf.close();
		}
		return slist;
		
	}
	
	
	
}
