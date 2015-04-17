package br.com.motogatomanager.util;

import java.io.UnsupportedEncodingException;

public class EncodingUtil {
	public static String ConvertToUTF8 (String ISO) {
		String result = "";
		try {
			result = new String (ISO.getBytes("ISO-8859-15"), "UTF-8");
		} catch (UnsupportedEncodingException uee) {
			System.out.println(uee);
			return ISO;
		}
		return result;
	}
	
	public static String ConvertToISO (String UTF) {
		String result = "";
		try {
			result = new String (UTF.getBytes("UTF-8"), "ISO-8859-15");
		} catch (UnsupportedEncodingException uee) {
			System.out.println(uee);
			return UTF;
		}
		return result;
	}
}
