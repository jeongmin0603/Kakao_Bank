package compoment;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import gui.Frame;
import gui.Macro;

public class Account extends RoundPanel {
	private static final int WIDTH = 400;
	private static final int HEIGHT = 200;
	private String accountId;
	private int money;
	
	public Account(String accountId, int money) {
		super(WIDTH, HEIGHT, accountId.length() == 12 ? Frame.MAIN_YELLOW : Color.white);

		this.accountId = accountId;
		this.money = money;
		
		setLayout(null);

		JPanel moneyLabel = Macro.coverFlowlayout(Macro.getLabel("잔액 : " + String.format("%,d", money) + " 원", 0, 25));
		moneyLabel.setBounds(0, 50, WIDTH, 80);

		add(moneyLabel);
		add(getButton(50, "이체", v -> {}));
		add(getButton(250, "이체", v -> {}));
	}

	private JButton getButton(int x, String text, ActionListener action) {
		JButton btn = new JButton("이체");

		int w = 150, h = 40, y = 150;

		btn.setForeground(Color.LIGHT_GRAY);
		btn.setContentAreaFilled(false);
		btn.setFont(new Font("맑은 고딕", 0, 20));
		btn.setBackground(accountId.length() == 12 ? Frame.MAIN_YELLOW :
		 Color.white);
		btn.setFocusPainted(false);
		btn.setBorder(null);
		btn.setPreferredSize(new Dimension(w, h));
		btn.setBounds(x, y, w, h);
		
		return btn;
	}

}
