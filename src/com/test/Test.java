package com.test;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
	
	private static final DecimalFormat df = new DecimalFormat("0.00");
	private static final SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY");
	
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
    	Employee empTrn = null;
    	List<Employee> employeeList =null;
    	long eid =0;
    	do
		{
    		intiateLandingPage(eservice);
    		System.out.println("select your choice \n");
			
    		System.out.println("1]Add  ");
    		System.out.println("2]update");
			System.out.println("3]delete");
			System.out.println("4]Search");
			System.out.println("5]View All Record");
    		System.out.println("6]chaange Employee status ACTIVE / DEACTIVATE / TERMINATE  / ON LEAVE ");
    		System.out.println("7]Exit from App ");
			
			int num =sc.nextInt();
		
			switch (num)
			{
				case 1:	
							empTrn= setEmployeeForm(sc,"ADD",null);
							loader();
							System.out.println("\n\tbefore persist "+empTrn+"\n");
							empTrn = eservice.saveEmployee(empTrn);
							if(empTrn.getEid()==null) {
								System.err.println("not added ,employee already exist");
							}
							System.out.println("\n\tafter persist "+empTrn);
							break;
				case 2:
							System.out.println("****** UPDATE BY ID ***** \n");
							System.out.println("Enter Employee EID : ");
							eid =sc.nextLong();
							loader();
							empTrn = eservice.findById(eid);
							if(empTrn !=null) {
								employeeList = new ArrayList<Employee>();
								employeeList.add(empTrn);
								displayRecord(employeeList);
								empTrn= updateEmployeeForm(sc,empTrn);
								System.out.println("\n\tbefore persist "+empTrn+"\n");
								eservice.updateEmployee(empTrn);
								System.out.println("\n\tafter persist "+empTrn);							
							}else {
								System.err.println("employee not found by EID ");
							}
							break;
				case 3:
							System.out.println("****** DELETE BY ID ***** \n");
							System.out.println("Enter Employee EID : ");
							eid=sc.nextLong();
							loader();
							empTrn = eservice.findById(eid);
							if(empTrn !=null) {
								employeeList = new ArrayList<Employee>();
								employeeList.add(empTrn);
								displayRecord(employeeList);
								loader();
								eservice.deleteEmployee(empTrn);
								System.out.println("deleted successfully !");
							}
							break;
				case 4:
							System.out.println("****** SEARCH BY ID ***** \n");
							System.out.println("Enter Employee EID : ");
							eid =sc.nextLong();
							loader();
							empTrn = eservice.findById(eid);
							if(empTrn !=null) {
								employeeList = new ArrayList<Employee>();
								employeeList.add(empTrn);
								displayRecord(employeeList);
							}else {
								System.err.println("employee not found by EID ");
							}
							break;
				case 5:
							employeeList=eservice.getAllEmployees();
							if(employeeList!=null && !employeeList.isEmpty()) {
								System.out.println("\n\nAll Employee Records ::");
								displayRecord(employeeList);
							}else {
								System.err.println("no data found");
							}
							break;
				case 6:		
							System.out.println("Enter Employee EID : ");
							eid =sc.nextLong();
							loader();
							empTrn = eservice.findById(eid);
							if(empTrn !=null) {
								employeeList = new ArrayList<Employee>();
								employeeList.add(empTrn);
								displayRecord(employeeList);
								System.out.println("\nselect your choise \n");
								
								System.out.println("1] ON LEAVE  ");
								System.out.println("2] TERMINATE ");
								System.out.println("3] DEACTIVATE ");
								System.out.println("4] ACTIVATE \n");
								int choice =sc.nextInt();
								switch (choice)
								{
									case 1:	
											empTrn.setStatus(EmployeeStatus.ONLEAVE.getEmployeeStatusCode());
											
											break;
									case 2: 
											empTrn.setStatus(EmployeeStatus.TERMINATED.getEmployeeStatusCode());
											break;
									case 3:	
											empTrn.setStatus(EmployeeStatus.DECEASED.getEmployeeStatusCode());
											break;
									case 4: 
											empTrn.setStatus(EmployeeStatus.ACTIVE.getEmployeeStatusCode());
											break;
									default:
											System.err.println("Invalid Choice,try again\n");
											break;
								}
								empTrn= setEmployeeForm(sc,"STATUS",empTrn);
								System.out.println("\n\tbefore persist "+empTrn+"\n");
								eservice.updateEmployee(empTrn);
								System.out.println("\n\tafter persist "+empTrn);	
							}else {
								System.err.println("employee not found by EID ");
							}
							break;

				case 7:
							System.exit(0);
							break;
				default:
							System.err.println("Invalid Choice,try again");
							break;
			}
    		
			System.out.println("\n\n\tDo u want to continue other operation (yes[y] / no[n] ) ? : ");
			status=sc.next();	
		
		
		}while(status.equalsIgnoreCase("yes") || status.equalsIgnoreCase("y"));
    	if(!(status.equalsIgnoreCase("yes") || status.equalsIgnoreCase("y"))){
    		System.err.println("invalid choise "+status);
    		System.exit(0);
    	}
    }

	private static Employee updateEmployeeForm(Scanner sc, Employee empTrn) {
		
		System.out.println("select your choise \n");
		
		System.out.println("1]update first and last name  ");
		System.out.println("2]update gender ");
		System.out.println("3]update dob ");
		System.out.println("4]update all data\n");
		
		int choice =sc.nextInt();
		
		switch (choice)
		{
			case 1:	
					empTrn= setEmployeeForm(sc,"NAMES",empTrn);
					break;
			case 2: 
					empTrn= setEmployeeForm(sc,"GENDER",empTrn);
					break;
			case 3:	
					empTrn= setEmployeeForm(sc,"DOB",empTrn);
					break;
			case 4: 
					empTrn= setEmployeeForm(sc,"ALL",empTrn);
					break;
			default:
					System.err.println("Invalid Choice,try again\n");
					break;
		}	
		
		return empTrn;
	}

	private static Employee setEmployeeForm(Scanner sc,String label,Employee trn) {
		
		
		if(trn!=null) {
			trn.setUpdatedBy("admin");
			trn.setUpdatedOn(new Date());
		}else {
			trn =new Employee("A","admin",new Date(),null,null);
		}
		
		if(label.equalsIgnoreCase("ADD") || label.equalsIgnoreCase("ALL")|| label.equalsIgnoreCase("NAMES")) {
			
			System.out.println("First Name________:\n");
			trn.setFirstName(sc.next());
			
			System.out.println("Last Name_________:\n");
			trn.setLastName(sc.next());
			
		}
		if(label.equalsIgnoreCase("ADD") || label.equalsIgnoreCase("ALL") || label.equalsIgnoreCase("GENDER")){
			
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
			
		}
		if(label.equalsIgnoreCase("ADD") || label.equalsIgnoreCase("ALL") || label.equalsIgnoreCase("DOB")){
			
			System.out.println("Date Of Birth [ yyyy-mm-dd ] _________:\n");
			String dateStr = sc.next();
			trn.setBirthDate(DateUtils.convertStringToJUtilDateTime(dateStr));
			
		}
		if(label.equalsIgnoreCase("ADD") || label.equalsIgnoreCase("STATUS")) {
			trn.setStatus(label.equalsIgnoreCase("STATUS")? trn.getStatus():EmployeeStatus.ACTIVE.getEmployeeStatusCode());
		}
		if(label.equalsIgnoreCase("ADD")){
			System.out.println("Salary per month ________:\n");
			trn.setSalary(Double.parseDouble(sc.next()));
		}
				
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
		loader();
		List<Employee> employeeList =null;
		try {
			employeeList=eservice.loadRecentRegistEmployeeByNativeQuery();
		} catch (Exception e) {
			System.err.println("Exception occured in intiateLandingPage "+e.getMessage());
		}
		System.out.println("\n\nRecently Added Record ::");
		displayRecord(employeeList);
	
	}
		
	

	private static void displayRecord(List<Employee> employeeList) {
		
		System.out.println("\n-------------------------------------------------------------------------------------------------------------");
		System.out.println("ID	|	NAME		|	STATUS 	|	AGE	| 	SALARY 		|	CREATED ON	 ");
		System.out.println("---------------------------------------------------------------------------------------------------------------");   
		
		if(employeeList!=null && !employeeList.isEmpty()) {
			
			for(Employee emp:employeeList) {
				System.out.println(emp.getEid()+"\t|"+emp.getFirstName()+" "+emp.getLastName()+"\t\t|\t"+emp.getStatus()+"\t|\t"+DateUtils.getAge(DateUtils.convertJUtilDateTimeToString(emp.getBirthDate()))+"\t|\t"+df.format(emp.getSalary())+"\t|\t"+sdf.format(emp.getCreatedOn()));
			}
			employeeList.clear();
		}
		System.out.println("-----------------------------------------------------------------------------------------------------------------\n");
	}

	private static void displayRecord1(List<Employee> employeeList) {
		
		System.out.println("\n--------------------------------------------------------------------");
		System.out.println("ID	|	NAME	|	STATUS 	|	AGE	| CREATED ON	 ");
		System.out.println("---------------------------------------------------------------------");   
		
		if(employeeList!=null && !employeeList.isEmpty()) {
			
			for(Employee emp:employeeList) {
				System.out.println(emp.getEid()+"\t|"+emp.getFirstName()+" "+emp.getLastName()+"\t|\t"+emp.getStatus()+"\t|\t"+DateUtils.getAge(DateUtils.convertJUtilDateTimeToString(emp.getBirthDate()))+"\t|"+emp.getCreatedOn());
			}
			employeeList.clear();
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
