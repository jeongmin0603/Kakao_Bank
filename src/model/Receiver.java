package model;

import org.json.simple.JSONObject;

public class Receiver {
	private String phone;
	private String name;
	private String birth;
	private String id;
	private String accountId;

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountId() {
		return accountId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Receiver(JSONObject json) {
		JSONObject user = (JSONObject) json.get("user");

		accountId = (String) json.get("accountId");
		phone = (String) user.get("phone");
		name = (String) user.get("name");
		birth = (String) user.get("birth");
		id = (String) user.get("id");
	}

}
