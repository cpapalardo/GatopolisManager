package br.com.farofa.gm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverterUtil {
	public static Date stringToDate(String date){
		Date result = null;
		String format = null;
		
		if(date != null) {
			if(date.contains("/")){
				if(date.length() == 6){
					format = "d/M/yy";
				}else if(date.length() == 7){
					if(date.charAt(1) == '/')
						format = "d/MM/yy";
					else
						format = "dd/M/yy";
				}else if(date.length() == 8){
					if(date.charAt(1) == '/')
						format = "d/M/yyyy";
					else
						format = "dd/MM/yy";
				}else if(date.length() == 9) {
					if(date.charAt(1) == '/')
						format = "d/MM/yyyy";
					else
						format = "dd/M/yyyy";
				}else if(date.length() == 10){
					format = "dd/MM/yyyy";
				}
			}else{
				if(date.length() == 7){
					format = "dMMyyyy";
				}else if(date.length() == 8){
					format = "ddMMyyyy";
				}
			}
		}
		
		try {
			result = new SimpleDateFormat(format).parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String dateToString(Date date){
		return null;
	}
}
