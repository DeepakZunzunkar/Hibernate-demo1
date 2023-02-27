package com.hibernateUtility;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtility 
{
	
	public static SessionFactory getHibernateConnection()
	{
		Configuration cfg=new Configuration();
		
		//this is by default naming convention means even if not specified this name of file internally configer object will search for this file references
		//if configer file name is different then we need to specify the file name . 
		//cfg.configure("abc.cfg.xml");
		cfg.configure();
		
		SessionFactory sf =cfg.buildSessionFactory();
		
		return sf;
	}
}
