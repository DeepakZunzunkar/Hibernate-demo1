package com.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author dz Feb 4, 2023
 *
 */
public class DateUtils {

	private final static String timezone = "Asia/Kolkata";

	public static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

	// current date time in ZonedDateTime 
	public static ZonedDateTime getCurrentZoneDateTime(String timezone) {
		return ZonedDateTime.now(ZoneId.of(timezone));
	}

	
	public static ZonedDateTime convertStringToZoneDateTime(String dateStr, String timezone) {
		// required string to be in following format : yyyy-MM-dd HH:mm:ss
		if (dateStr != null && !dateStr.trim().isEmpty()) {
			try {
				if (dateStr.length() == 10) {
					dateStr = dateStr.trim() + " 00:00:00";
				} else if (dateStr.length() > 10) {
					dateStr = dateStr.substring(0, 10);
					dateStr = dateStr.trim() + getCurrentZoneDateTime(timezone).toLocalTime().toString();
				}
				ZonedDateTime zonedDateTime = LocalDateTime.parse(df.parse(dateStr).toInstant().toString(), dtf)
						.atZone(ZoneId.of(timezone));
				return zonedDateTime;

			} catch (Exception e) {
				System.err.println("Exeception occured " + e.getMessage());
				e.printStackTrace();
			}

		}
		return null;
	}

	//covert string to java.util.Date 
	public static Date convertStringToJUtilDateTime(String dateStr) {

		Date jUtilDate = null;
		if (dateStr != null && !dateStr.trim().isEmpty()) {
			try {
				if(dateStr.contains("/")) {
					dateStr = dateStr.replace("/", "-");
				}
				if (dateStr.length() > 10) {
					jUtilDate = df.parse(dateStr);
				} else {
					jUtilDate = sdf.parse(dateStr);
				}
			} catch (ParseException e) {
				System.err.println("Exeception occured " + e.getMessage());
				e.printStackTrace();
			}
		}
		return jUtilDate;
	}

	//covert java.util.Date  to string  
	public static String convertJUtilDateTimeToString(Date dateObj){

		String dateStr = null;
		if (dateObj != null) {
			dateStr = df.format(dateObj);
		}
		return dateStr;
	}

	public static long convertStringToLongTimeTime(String dateStr) {

		Date jUtilDate = null;
		if (dateStr != null && !dateStr.trim().isEmpty()) {
			try {
				if (dateStr.length() > 10) {
					jUtilDate = df.parse(dateStr);
				} else {
					jUtilDate = sdf.parse(dateStr);
				}
			} catch (ParseException e) {
				System.err.println("Exeception occured " + e.getMessage());
				e.printStackTrace();
			}
		}
		return jUtilDate.getTime();
	}
	
	
	public static Integer getAge(String dob) {

		//set dob in calender 
		Calendar calendar = Calendar.getInstance();
//		System.out.println(calendar);
		try {
			calendar.setTime(sdf.parse(dob));
		} catch(ParseException e) {
			System.err.println("error at getAge "+e.getMessage());
		}catch(Exception e) {
			System.err.println("error at getAge "+e.getMessage());
		}
//		System.out.println(calendar);
		LocalDate birthDate = LocalDate.of(calendar.get(Calendar.YEAR),(calendar.get(Calendar.MONTH)+1), calendar.get(Calendar.DAY_OF_MONTH));
		
		// current date 
		LocalDate currentDate =LocalDate.now();
		
		Period p = Period.between(birthDate, currentDate);
		return p.getYears();
	}
	
	
	
	
	public static void main(String[] args) throws ParseException {

//		System.out.println(new Date());
//		System.out.println(new java.sql.Date(0));
//		System.out.println(getCurrentZoneDateTime(timezone).toLocalTime().toString());

//		System.out.println(sdf.parse("1993/04/15 00:00:00"));
//		System.out.println(sdf.parse("1993-04-15 00:00:00"));
		
//		System.out.println(convertStringToJUtilDateTime("1993-04-15"));
		
		System.out.println(getAge("1993-04-15"));
		
		
	}

}