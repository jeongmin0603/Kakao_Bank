package compoment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.Frame;
import gui.Home;
import gui.Remittance;

public class Account extends RoundPanel {
	private static final int WIDTH = 340;
	private static final int HEIGHT = 190;

	private String accountId;
	private int money;

	public Account(String accountId, int money) {
		super(WIDTH, HEIGHT);

		this.accountId = accountId;
		this.money = money;

		int index = new Random().nextInt(Style.ACCOUNT_COLOR.length);
		
		setLayout(new FlowLayout());
		setBackground(Style.ACCOUNT_COLOR[index]);

		if(getBackground().getRed() <= 100 && getBackground().getGreen() <= 100 && getBackground().getBlue() <= 100) {
			setForeground(Color.white);
		} else {
			setForeground(Color.black);
		}
		
		System.out.println(index);
		
		JPanel panel = new JPanel(new BorderLayout(20, 20));
		panel.add(getCenter(), BorderLayout.CENTER);
		panel.add(getSouth(), BorderLayout.SOUTH);
		panel.setBackground(getBackground());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

		add(panel);
	}

	private JPanel getSouth() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.add(new Button(150, 30, 0, 20, "이체", getForeground(), getBackground(), new ClickRemittance()));
		panel.add(new Button(150, 30, 0, 20, "송금", getForeground(), getBackground(), new ClickRemittance()));

		panel.setBackground(getBackground());
		return panel;
	}

	private JPanel getCenter() {
		JPanel money = Layout.coverFlowlayout(0, new Label("잔액 : " + String.format("%,d", this.money) + " 원", 18, getForeground()));
		JPanel id = Layout.coverFlowlayout(0,
				new Label(accountId.replaceFirst("(^02|[0-9]{4})([0-9]{2})([0-9]{6})$", "$1-$2-$3"), 18, getForeground()));

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(Layout.coverFlowlayout(getBankIcon()), BorderLayout.WEST);
		panel.add(Layout.coverFlowlayout(0, Layout.coverVertical(money, id)));

		panel.setBackground(getBackground());
		return Layout.coverFlowlayout(0, panel);
	}

	private JLabel getBankIcon() {
		JLabel label = new JLabel();

		if (accountId.length() == 12) {
			label.setIcon(Style.getImageIcon(40, 40, "\\icon\\kakao_bank_logo.png"));
		}
		
		label.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 0));
		return label;
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