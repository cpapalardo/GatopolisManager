package br.com.farofa.gm.ws;

public class WebServiceExeptionManager {
	public static String getExceptionMessage (Exception e) {
		String error = "Exception\n";
		error += e.toString() + "\n";
		for (StackTraceElement element : e.getStackTrace()) {
			error += element.toString() + "\n";
		}
		return error;
	}
}
