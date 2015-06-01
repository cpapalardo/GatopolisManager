package br.com.farofa.gm.model;

import java.io.Serializable;
import java.lang.reflect.Field;

import org.json.JSONObject;

public class JsonBehaviour implements Serializable{
	private static final long serialVersionUID = 1L;

	public JsonBehaviour (){}
	
	public void setJson (String json){
		JSONObject jsonObj = new JSONObject(json);
		Field[] decFields = this.getClass().getDeclaredFields();
		for (Field decField : decFields) {
			try {
				if(!decField.getName().equals("serialVersionUID")){
					decField.setAccessible(true);
					Object value = jsonObj.get(decField.getName());
					decField.set(this, value);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getJson(){
		JSONObject jsonObj = new JSONObject();
		Field[] decFields = this.getClass().getDeclaredFields();
		for (Field decField : decFields) {
			try {
				if(!decField.getName().equals("serialVersionUID")){
					decField.setAccessible(true);
					Object value = decField.get(this);
					jsonObj.put(decField.getName(), value);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return jsonObj.toString();
	}

}
