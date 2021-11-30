package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JPanel;
import javax.swing.UIManager;

import compoment.AccountNode;
import layout.BringAccountList;
import model.Account;

public class Bring extends Frame {
	private static Bring instance = null;
	private JPanel panel;
	private int money;
	private Account receive;
	private AccountNode send;

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public AccountNode getSend() {
		return send;
	}

	public void setSend(AccountNode send) {
		this.send = send;
	}

	@Override
	public void dispose() {
		instance = null;
		super.dispose();
	}

	public Account getReceive() {
		return receive;
	}

	public void setReceive(Account receive) {
		this.receive = receive;
	}

	public static Bring getInstance(Account receive) {
		if (instance == null)
			instance = new Bring(receive);
		return instance;
	}

	public static Bring getInstance() {
		return instance;
	}

	public JPanel getPanel() {
		return panel;
	}

	public Bring(Account receive) {
		super(400, 750, "가져오기");
		UIManager.put("Panel.background", Color.white);

		this.receive = receive;

		panel = new JPanel(new FlowLayout());
		panel.add(new BringAccountList(receive));

		add(panel);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Home.getInstance().setVisible(true);
			}
		});
	}

}
