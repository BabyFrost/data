package com.docteurfrost.data.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateStringConverter {
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
	
	public static Date stringToDate(String date) throws ParseException {
		System.out.println( "Date Converted : "+simpleDateFormat.parse(date) );
		return simpleDateFormat.parse(date);
	}
	
	public static String dateToString( Date date ) {
		return simpleDateFormat.format( date );
	}

}
