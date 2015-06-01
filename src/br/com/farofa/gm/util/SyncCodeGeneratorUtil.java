package br.com.farofa.gm.util;

import java.util.Calendar;
import java.util.Random;

public class SyncCodeGeneratorUtil {
	public static String generate(){
		String result = null;
		
		Calendar c = Calendar.getInstance();
		String year = String.valueOf(c.get(Calendar.YEAR));
		
		String month = String.valueOf(c.get(Calendar.MONTH) + 1);
		if(month.length() == 1)
			month = "0" + month;
		
		String random = String.valueOf (new Random ().nextInt(9999));
		if (random.length() < 4) {
			random = "0" + random;
		}
		
		result = year + month + random;
		
		return result;
	}
}
