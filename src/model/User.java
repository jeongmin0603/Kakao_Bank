package model;

import org.json.simple.JSONObject;

public class User {
	
	private static String token;
	private static String id;
	private static String pw;
	private static String phone;
	private static String name;
	private static String birth; // 주민등록번호 앞자리

	
	public static String getToken() {
		return token;
	}

	public static void setToken(String token) {
		User.token = token;
	}

	public static String getId() {
		return id;
	}

	public static void setId(String id) {
		User.id = id;
	}

	public static String getPw() {
		return pw;
	}

	public static void setPw(String pw) {
		User.pw = pw;
	}

	public static String getPhone() {
		return phone;
	}

	public static void setPhone(String phone) {
		User.phone = phone;
	}

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		User.name = name;
	}

	public static String getBirth() {
		return birth;
	}

	public static void setBirth(String birth) {
		User.birth = birth;
	}

	public static JSONObject getJSON() {
		JSONObject json = new JSONObject();

		json.put("id", id);
		json.put("pw", pw);
		json.put("phone", phone);
		json.put("name", name);
		json.put("birth", birth);

		return json;
	}
}
