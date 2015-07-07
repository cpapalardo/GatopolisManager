package br.com.farofa.gm.webservice;

public class WebServiceExeptionManager {
	public static String GetExceptionMessage (Exception e) {
		String error = e.toString() + "\n";
		for (StackTraceElement element : e.getStackTrace()) {
			error += element.toString() + "\n";
		}
		return error;
	}
}
