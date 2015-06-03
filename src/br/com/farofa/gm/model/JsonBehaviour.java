package br.com.farofa.gm.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.text.SimpleDateFormat;

import org.json.JSONObject;

public class JsonBehaviour implements Serializable{
	private static final long serialVersionUID = 1L;

	public JsonBehaviour (){}
	
	public void setJson (String json){
		JSONObject jsonObj = new JSONObject(json);
		
		//Get all the field from the instance
		Field[] decFields = this.getClass().getDeclaredFields();
		for (Field decField : decFields) {
			try {
				//Check serialized field
				if (!decField.getName().equals("serialVersionUID")){
					//Field
					decField.setAccessible(true);
					String fieldName = decField.getName();
					Class<?> type = decField.getType();
					
					//null check
					if (jsonObj.isNull(fieldName)) {
						continue;
					}
					//if String
					else if (type.equals(String.class)) {
						String s = jsonObj.getString(fieldName);
						decField.set(this, s);
					} 
					//if int
					else if (type.equals(Integer.class)) {
						Integer i = jsonObj.getInt(fieldName);
						decField.set(this, i);
					} 
					//if char
					else if (type.equals(Character.class)) {
						String s = jsonObj.getString(fieldName);
						Character c = s.charAt(0);
						decField.set(this, c);
					}
					//if date
					else if (type.equals(Date.class)) {
						String s = jsonObj.getString(fieldName);
						Date date = new SimpleDateFormat("dd/MM/yyyy").parse(s);
						decField.set(this, date);
					} else {
						//if it is an object where extends jsonBehaviour
						Object obj = Class.forName(type.getName()).newInstance();
						if (obj instanceof JsonBehaviour) {
							JsonBehaviour jsonB = (JsonBehaviour) obj;
							jsonB.setJson(jsonObj.get(fieldName).toString());
							decField.set(this, jsonB);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getJson(){
		JSONObject jsonObj = new JSONObject();
		
		//Get all the field from the instance
		Field[] decFields = this.getClass().getDeclaredFields();
		for (Field decField : decFields) {
			try {
				//Check serialized field
				if(!decField.getName().equals("serialVersionUID")){
					//Field
					decField.setAccessible(true);
					String fieldName = decField.getName();
					Class<?> type = decField.getType();
					Object value = decField.get(this);
					
					//if primitive
					if (type.equals(String.class) || type.equals(Integer.class) || type.equals(Character.class)) {
						jsonObj.put(fieldName, value);
					} 
					//if date
					else if (type.equals(Date.class)) {
						String date = new SimpleDateFormat("dd/MM/yyyy").format(value);
						jsonObj.put(fieldName, date);
					} else {
						//if it is an object where extends jsonBehaviour
						if (value instanceof JsonBehaviour) {
							JsonBehaviour jsonB = (JsonBehaviour) value;
							jsonObj.put(fieldName, jsonB.getJson());
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return jsonObj.toString();
	}
}
