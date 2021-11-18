package model;

public class Account {
	private String id;
	private String pw;
	private String name;
	private int money;

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getPw() {
		return pw;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public Account(String id, int money) {
		this.id = id;
		this.money = money;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Account() {

	}

	@Override
	public String toString() {
		return "통장아이디 " + getMoney() + " 잔액" + getMoney();
	}
}
