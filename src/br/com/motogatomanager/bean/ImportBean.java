package br.com.motogatomanager.bean;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class ImportBean {
	
	public String toImport () {
		return "import";
	}
	
	public String back () {
		return "/home";
	}
}
