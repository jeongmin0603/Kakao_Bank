package compoment;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.Frame;
import gui.Home;
import gui.Remittance;
import gui.Style;

public class Account extends RoundPanel {
	private static final int WIDTH = 400;
	private static final int HEIGHT = 200;
	private String kind;
	private String accountId;
	private int money;
	
	public Account(String accountId, int money) {
		super(WIDTH, HEIGHT, accountId.length() == 12 ? Style.MAIN_YELLOW : Color.white);

		this.accountId = accountId;
		this.money = money;
		kind = accountId.length() == 12 ? "카카오뱅크" : "?";
		
		setLayout(null);

		JPanel moneyLabel = Layout.coverFlowlayout(new Label("잔액 : " + String.format("%,d", money) + " 원", 0, 25));
		moneyLabel.setBounds(0, 50, WIDTH, 80);

		add(moneyLabel);
		add(getButton(50, "이체", new ClickRemittance()));
		add(getButton(250, "송금", v -> {}));
		add(getBankIcon());
	}
	
	private JLabel getBankIcon() {
		JLabel label = new JLabel();
		if(kind.equals("카카오뱅크")) {
			label.setIcon(Style.getImageIcon(80, 80, "\\icon\\kakao.png"));
		}
		
		label.setPreferredSize(new Dimension(80, 80));
		label.setBounds(25, 35, 80, 80);
		return label;
	}

	private JButton getButton(int x, String text, ActionListener action) {
		JButton btn = new JButton(text);

		int w = 150, h = 40, y = 150;

		btn.addActionListener(action);
		btn.setForeground(Color.DARK_GRAY);
		btn.setContentAreaFilled(false);
		btn.setFont(new Font("맑은 고딕", 0, 20));
		btn.setBackground(kind.equals("카카오뱅크") ? Style.MAIN_YELLOW : Color.white);
		btn.setFocusPainted(false);
		btn.setBorder(null);
		btn.setPreferredSize(new Dimension(w, h));
		btn.setBounds(x, y, w, h);
		
		return btn;
	}
	
	private class ClickRemittance implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Home.getInstance().dispose();
			model.Account account = new model.Account(accountId, money);
			
			Remittance.getInstance(account).setVisible(true);
		}
	}

}
