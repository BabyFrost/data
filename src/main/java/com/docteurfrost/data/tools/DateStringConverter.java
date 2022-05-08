package com.docteurfrost.data.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateStringConverter {
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
	
	public static Date stringToDate(String date) throws ParseException {
		
		if (date == null ) {
			return null;
		}
		
		return simpleDateFormat.parse(date);
	}
	
	public static String dateToString( Date date ) {
		
		if (date == null ) {
			return null;
		}
		
		return simpleDateFormat.format( date );
	}

}
