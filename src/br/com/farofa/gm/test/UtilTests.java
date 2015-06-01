package br.com.farofa.gm.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.farofa.gm.util.DateConverterUtil;

public class UtilTests {
	public static void main(String[] args) {
		dateConverterUtilTest();
	}
	
	public static void dateConverterUtilTest(){
		List<String> strings = new ArrayList<String>();
		
		strings.add("1/1/10");
		strings.add("1/10/10");
		strings.add("10/1/10");
		strings.add("1/1/2010");
		strings.add("10/10/10");
		strings.add("1/10/2010");
		strings.add("10/1/2010");
		strings.add("10/10/2010");
		
		strings.add("1012010");
		strings.add("01012010");
		
		for(String s : strings){
			Date date = DateConverterUtil.stringToDate(s);
			System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(date));
		}
	}
}
