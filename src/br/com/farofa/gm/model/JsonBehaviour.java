package br.com.farofa.gm.model;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

@SuppressWarnings("serial")
public class JsonBehaviour implements Serializable {
	private Class<?> entityClass;

	public String getJson() {
		JSONObject jObj = new JSONObject();

		for (Field field : getEntityClass().getDeclaredFields()) {
			String name = field.getName();
			field.setAccessible(true);
			try {
				if (field.getName().equals("serialVersionUID")) {
					continue;
				} else if (field.get(this) instanceof Date) {
					Date date = (Date) field.get(this);
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					Object value = df.format(date);
					jObj.put(name, value);
				} else if (field.get(this) instanceof JsonBehaviour) {
					JsonBehaviour jbo = (JsonBehaviour) field.get(this);
					Object value = getId(jbo);
					jObj.put(name, value);
				} else {
					Object value = field.get(this);
					jObj.put(name, value);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//Check if super extends JsonBehaviour
		if (getEntityClass().getSuperclass().getSuperclass().equals(JsonBehaviour.class)) {
			for (Field field : getEntityClass().getSuperclass().getDeclaredFields()) {
				String name = field.getName();
				field.setAccessible(true);
				try {
					if (field.getName().equals("serialVersionUID")) {
						continue;
					} else if (field.get(this) instanceof Date) {
						Date date = (Date) field.get(this);
						DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
						Object value = df.format(date);
						jObj.put(name, value);
					} else if (field.get(this) instanceof JsonBehaviour) {
						JsonBehaviour jbo = (JsonBehaviour) field.get(this);
						Object value = getId(jbo);
						jObj.put(name, value);
					} else {
						Object value = field.get(this);
						jObj.put(name, value);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return jObj.toString();
	}

	protected void setJson(String json) {
		JSONObject jObj = new JSONObject(json);
		
		for (Field field : getEntityClass().getDeclaredFields()) {
			String name = field.getName();
			field.setAccessible(true);
			if (jObj.has(name)) {
				try {
					if (field.getName().equals("serialVersionUID")) {
						continue;
					} else if (field.getType().equals(Date.class)) {
						String value = jObj.getString(name);
						DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
						Date date = df.parse(value);
						field.set(this, date);
					} else if (JsonBehaviour.class.equals(field.getType().getSuperclass()) ||
							JsonBehaviour.class.equals(field.getType().getSuperclass().getSuperclass())) {
						JsonBehaviour jbo = (JsonBehaviour)field.getType().newInstance();
						Object value = jObj.get(name);
						setId(jbo, value);
						field.set(this, jbo);
					} else {
						if (field.getType().equals(Integer.class)) {
							field.set(this, jObj.getInt(name));
						} else if (field.getType().equals(String.class)) {
							field.set(this, jObj.getString(name));
						} else if (field.getType().equals(Character.class)) {
							field.set(this, jObj.getString(name).charAt(0));
						}else if (field.getType().equals(Boolean.class)) {
							field.set(this, jObj.getBoolean(name));
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		//Check if super extends JsonBehaviour
		if (JsonBehaviour.class.equals(getEntityClass().getSuperclass().getSuperclass())) {
			for (Field field : getEntityClass().getSuperclass().getDeclaredFields()) {
				String name = field.getName();
				field.setAccessible(true);
				if (jObj.has(name)) {
					try {
						if (field.getName().equals("serialVersionUID")) {
							continue;
						} else if (field.getType().equals(Date.class)) {
							String value = jObj.getString(name);
							DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
							Date date = df.parse(value);
							field.set(this, date);
						} else if (JsonBehaviour.class.equals(field.getType().getSuperclass()) ||
								JsonBehaviour.class.equals(field.getType().getSuperclass().getSuperclass())) {
							JsonBehaviour jbo = (JsonBehaviour)field.getType().newInstance();
							Object value = jObj.get(name);
							setId(jbo, value);
							field.set(this, jbo);
						} else {
							if (field.getType().equals(Integer.class)) {
								field.set(this, jObj.getInt(name));
							} else if (field.getType().equals(String.class)) {
								field.set(this, jObj.getString(name));
							} else if (field.getType().equals(Character.class)) {
								field.set(this, jObj.getString(name).charAt(0));
							}else if (field.getType().equals(Boolean.class)) {
								field.set(this, jObj.getBoolean(name));
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	// Detecta se a referencia possuí a annotation @Id e retorna seu valor
	private Object getId(JsonBehaviour jbo) {
		Object result = null;
		//Try to find id
		for (Field field : jbo.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			for (Annotation annotation : field.getDeclaredAnnotations()) {
				if (annotation.toString().equals("@javax.persistence.Id()")) {
					try {
						result = field.get(jbo);
						return result;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		//Try get id in super
		for (Field field : jbo.getClass().getSuperclass().getDeclaredFields()) {
			field.setAccessible(true);
			for (Annotation annotation : field.getDeclaredAnnotations()) {
				if (annotation.toString().equals("@javax.persistence.Id()")) {
					try {
						result = field.get(jbo);
						return result;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return result;
	}

	// Detecta o id do objeto e preenche com o valor indicado
	private void setId(JsonBehaviour jbo, Object id) {
		//Try to find id
		for (Field field : jbo.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			for (Annotation annotation : field.getDeclaredAnnotations()) {
				if (annotation.toString().equals("@javax.persistence.Id()")) {
					try {
						if (field.getType().equals(Integer.class)) {
							field.set(jbo, Integer.parseInt(id.toString()));
							return;
						} else if (field.getType().equals(String.class)) {
							field.set(jbo, (String) id);
							return;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		//Try get id in super
		for (Field field : jbo.getClass().getSuperclass().getDeclaredFields()) {
			field.setAccessible(true);
			for (Annotation annotation : field.getDeclaredAnnotations()) {
				if (annotation.toString().equals("@javax.persistence.Id()")) {
					try {
						if (field.getType().equals(Integer.class)) {
							field.set(jbo, Integer.parseInt(id.toString()));
							return;
						} else if (field.getType().equals(String.class)) {
							field.set(jbo, (String) id);
							return;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public static <T extends JsonBehaviour> String getJsonFromList (List<T> modelList) {
		String result = null;
		if (modelList != null && modelList.size() > 0) {
			JSONObject jsonList = new JSONObject();
			
			@SuppressWarnings("unchecked")
			Class<T> clazz = (Class<T>) modelList.get(0).getClass();
			 
			for (int i = 0; i < modelList.size(); i++) {
				if (modelList.get(i) == null)
					continue;
				
				String key = clazz.getSimpleName() + "-" + i;
				String json = modelList.get(i).getJson();
				JSONObject jsonObj = new JSONObject(json);
				jsonList.put(key, jsonObj);
			}
			
			result = jsonList.toString();
		}
		return result;
	}
	
	private Class<?> getEntityClass () {
		if (entityClass == null) {
			entityClass = (Class<?>) getClass();
		}
		return entityClass;
	}
}
