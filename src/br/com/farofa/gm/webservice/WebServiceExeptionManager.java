package br.com.farofa.gm.webservice;

import com.google.common.base.Throwables;

public class WebServiceExeptionManager {
	public static String getExceptionMessage (Exception e) {
		String error = "Exception\n";
		error += Throwables.getStackTraceAsString(e);
		return error;
	}
}
