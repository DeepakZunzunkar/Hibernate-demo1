package com.daoImpl;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Criteria;
import org.hibernate.Query;
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
		
		session.close();
		//sf.close();
		return rs;
	}
	public static int update()
	{
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
		
		session.close();
		//sf.close();
		
		return 0;
	}
	
	public static int  delete()
	{
		student=new Student();
		
		System.out.println("You should Know ID :");
		
		System.out.println("Enter ID :");
		student.setId(sc.nextInt());
		
		session=sf.openSession();
		tx=session.beginTransaction();
		
		session.delete(student);
		
		tx.commit();
		System.out.println("Record Deleted Successfully..");
		
		session.close();
		//sf.close();
		
		return 0;
	}
	
	public static Student search()
	{
		System.out.println("Enter ID :");
		int id=sc.nextInt();
		
		session=sf.openSession();
		student=session.get(Student.class,id);
		
		session.close();
		//sf.close();	
		
		return student;
	}
	
	public static List<Student> getAllRecord()
	{
		session=sf.openSession();
		Criteria criteria=session.createCriteria(Student.class);
		List<Student> slist=criteria.list();
		
		session.close();
		//sf.close();
		
		return slist;
		
	}
	
	
	
}
