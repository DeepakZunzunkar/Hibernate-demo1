package com.test;

import java.util.List;
import java.util.Scanner;

import com.daoImpl.StudebtDaoImpl;
import com.model.Student;

public class Test 
{
	
	
	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		int option=0;
		int rs=0;
		
		System.out.println("*************wecome***********************\n");
		while(true)
		{
			System.out.println("1]Add");
			System.out.println("2]update");
			System.out.println("3]delete");
			System.out.println("4]Search");
			System.out.println("5]View All Record");
			System.out.println("6]Exit");
			System.out.println("\n");
			
			System.out.println("Select your choice :");
			
			option=sc.nextInt();
			
		switch (option)
		{
			case 1:
					rs=StudebtDaoImpl.add();
					System.out.println("id "+rs);
					if(rs>0)
					{
						System.out.println("Record Added succesfully..");
					}else{
						System.err.println("fail to add..try again");
					}
					break;
	
			case 2:
					StudebtDaoImpl.update();
					
					break;
				
			case 3:
					
					StudebtDaoImpl.delete();
					
					break;
			
			case 4:
					Student student=StudebtDaoImpl.search();
					System.out.println("\n|\t ID \t|\t Name \t|\t Address \t|");
					System.out.println("=====================================================");
					System.out.println("\t"+student.getId()+"\t|\t"+student.getName()+"\t|\t"+student.getAddress());
					System.out.println("---------------------------------------------------------------------");
					break;
					
			case 5:
					List<Student> slist=StudebtDaoImpl.getAllRecord();
					System.out.println("\n|\t ID \t|\t Name \t|\t Address \t|");
					System.out.println("=====================================================================");
					for(Student st : slist)
					{
						System.out.println("\t"+st.getId()+"\t|\t"+st.getName()+"\t|\t"+st.getAddress());
						System.out.println("------------------------------------------------------------------------------");
					}
					break;
					
			case 6 :
					System.exit(0);
					break;
				
			default:
					System.err.println("Invalid Choice ...try again");
					break;
			}	
		}
		
	}
}
