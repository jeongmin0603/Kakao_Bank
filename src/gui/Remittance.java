package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JPanel;
import javax.swing.UIManager;

import layout.RemittanceInfo;
import model.Account;
import model.Receiver;

public class Remittance extends Frame {
	private static final double FEE = 0.03;
	private static Remittance instance = null;
	private Account account = null;
	private Receiver receiver;
	private int money;
	private JPanel panel;

	public static double getFee() {
		return FEE;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public Receiver getReceiver() {
		return receiver;
	}

	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public JPanel getPanel() {
		return panel;
	}

	public Account getAccount() {
		return account;
	}

	public static Remittance getInstance() {
		return instance;
	}

	public static Remittance getInstance(Account account) {
		if(instance == null) 
			instance = new Remittance(account);
		return instance;
	}

	@Override
	public void dispose() {
		instance = null;
		super.dispose();
	}

	private Remittance(Account account) {
		super(400, 750, "송금");

		UIManager.put("Panel.background", Color.WHITE);
		panel = new JPanel(new FlowLayout());

		this.account = account;
		panel.add(new RemittanceInfo(account));

		add(panel);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Home.getInstance().setVisible(true);
			}
		});
	}

}
