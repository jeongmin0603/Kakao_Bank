package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import compoment.TextButton;
import layout.AccountUserInfo;
import model.Account;
import model.User;

public class AccountBuild extends Frame {
	private static AccountBuild instance = new AccountBuild();
	JPanel panel = new JPanel(new FlowLayout());
	private Account account = new Account();
	
	public Account getAccount() {
		return account;
	}

	public static void main(String[] args) {
		instance.setVisible(true);
	}
	
	public JPanel getPanel() {
		return panel;
	}

	public static AccountBuild getInstance() {
		if (instance == null)
			instance = new AccountBuild();
		return instance;
	}

	@Override
	public void dispose() {
		instance = null;
		super.dispose();
	}

	private AccountBuild() {
		super(400, 750, "계좌 개설");
		UIManager.put("Panel.background", Color.white);

		panel.add(new AccountUserInfo());
		panel.setBackground(Color.white);

		add(panel);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Home.getInstance().setVisible(true);
			}
		});
	}
	
}
