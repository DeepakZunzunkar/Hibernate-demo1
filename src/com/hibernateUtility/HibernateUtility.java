package com.hibernateUtility;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtility 
{
	
	public static SessionFactory getHibernateConnection()
	{
		Configuration cfg=new Configuration();
		cfg.configure();
		
		SessionFactory sf =cfg.buildSessionFactory();
		
		return sf;
	}
}
