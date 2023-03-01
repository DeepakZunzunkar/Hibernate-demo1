package com.test;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.daoImpl.StudebtDaoImpl;
import com.model.Employee;
import com.model.Student;
import com.service.EmployeeService;
import com.serviceImpl.EmployeeServiceImpl;
import com.utility.Constant.EmployeeStatus;
import com.utility.Constant.Gender;
import com.utility.DateUtils;

public class Test 
{
	
	
	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		int option=0;
		int rs=0;

		
		System.out.println("	1]Student CURD ");
		System.out.println("	2]Employee CURD ");
		
		option=sc.nextInt();
		if(option==1) {
			System.out.println("*************welcome to student curd operation***********************\n");
			studentCurdOperation();
		}else if(option==2) {
			System.out.println("*************welcome to employee curd operation***********************\n");
			employeeCurdOperation();
		}else {
			System.out.println("invalid choice,please restart the app");
		}
		
		
		
	}

	private static void employeeCurdOperation() {
		
		Scanner sc =new Scanner(System.in);
    	String status;
    	
    	// display active , inactive and terminated count and recent register list of employee with pagination
    	// button to register employee
    	// button to exit and terminate the employee
    	
    	EmployeeService eservice = new EmployeeServiceImpl();

		
    	do
		{
    		intiateLandingPage(eservice);
    		System.out.println("select your choice \n");
			
    		System.out.println("1.Regeister Employee ");
    		System.out.println("2.view all record");
    		System.out.println("3.Terminate Employee ");
    		System.out.println("4.Deactivate / Exist Employee from the system ");
			
			int num =sc.nextInt();
		
			switch (num)
			{
				case 1:	
						Employee empTrn= registerEmployeeForm(sc);
						loader();
						System.out.println("\n\tbefore persist "+empTrn+"\n");
						empTrn = eservice.saveEmployee(empTrn);
						if(empTrn.getEid()==null) {
							System.err.println("not added ,employee already exist");
						}
						System.out.println("\n\tafter persist "+empTrn);
						break;
				case 2:
						
						break;
				case 3:

						break;
			
				default:
						System.out.println("INVALID Choice");
						break;
			}
    		
			System.out.println("\n\n\tDo u want to continue other operation (yes[y] / no[n] ) ? : ");
			status=sc.next();	
		
		
		}while(status.equalsIgnoreCase("yes") || status.equalsIgnoreCase("y"));
    }

	private static Employee registerEmployeeForm(Scanner sc) {
		
		Employee trn =new Employee("A","admin",new Date(),null,null);
		
		System.out.println("First Name________:\n");
		trn.setFirstName(sc.next());
		
		System.out.println("Last Name_________:\n");
		trn.setLastName(sc.next());
		
		System.out.println("Gender \n 1.Male\n 2.Female : ");
		int gChoice =sc.nextInt();
		switch (gChoice)
		{
			case 1:	
					trn.setGender(Gender.MALE.getGenderValue());
					break;
			case 2: 
					trn.setGender(Gender.FEMALE.getGenderValue());
					break;
			default:
					trn.setGender("NA");
					break;
		}	
		
		trn.setStatus(EmployeeStatus.ACTIVE.getEmployeeStatusCode());

		System.out.println("Date Of Birth [ yyyy-mm-dd ] _________:\n");
		String dateStr = sc.next();
		trn.setBirthDate(DateUtils.convertStringToJUtilDateTime(dateStr));
		
		return trn;
	}
	
	public static void loader(){
		System.out.print("Loading ");
		for(int i=0;i<=20;i++) {
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.print("#");
		}
		System.out.println("\n");
	}
	private static void intiateLandingPage(EmployeeService eservice) {
		
		List<Employee> employeeList =null;
		try {
			employeeList=eservice.loadRecentRegistEmployeeByNativeQuery();
		} catch (Exception e) {
			System.out.println("Exception occured in intiateLandingPage"+e.getMessage());
		}
		
		System.out.println("\n\nRecently Added Record ::");
		System.out.println("\n--------------------------------------------------------------------");
		System.out.println("ID	|	NAME		|	STATUS 	|	AGE		| CREATED ON	 ");
		System.out.println("---------------------------------------------------------------------");   
		
		if(employeeList!=null && !employeeList.isEmpty()) {
			
			for(Employee emp:employeeList) {
				System.out.println(emp.getEid()+"\t|"+emp.getFirstName()+" "+emp.getLastName()+"\t|\t"+emp.getStatus()+"\t|\t"+DateUtils.getAge(DateUtils.convertJUtilDateTimeToString(emp.getBirthDate()))+"|"+emp.getCreatedOn());
			}
		}
		System.out.println("-----------------------------------------------------------------------\n");
	
	}
		
	

	private static void studentCurdOperation() {
		
		Scanner sc=new Scanner(System.in);
		int option=0;
		int rs=0;
		
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
