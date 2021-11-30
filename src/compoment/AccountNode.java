package compoment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.Bring;
import layout.BringMoney;
import model.Account;

public class AccountNode extends RoundPanel {
	private static final int WIDTH = 340;
	private static final int HEIGHT = 100;

	private String accountId;
	private int money;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public AccountNode(String accountId, int money) {
		super(WIDTH, HEIGHT);

		this.accountId = accountId;
		this.money = money;

		int index = new Random().nextInt(Style.ACCOUNT_COLOR.length);

		setLayout(new FlowLayout());
		setBackground(Style.ACCOUNT_COLOR[index]);

		if (getBackground().getRed() <= 100 && getBackground().getGreen() <= 100 && getBackground().getBlue() <= 100) {
			setForeground(Color.white);
		} else {
			setForeground(Color.black);
		}

		JPanel panel = new JPanel(new BorderLayout(20, 20));
		panel.add(getCenter(), BorderLayout.CENTER);
		panel.setBackground(getBackground());

		add(panel);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Bring bring = Bring.getInstance();
				JPanel panel = bring.getPanel();

				bring.setSend(getAccountNode());
				panel.removeAll();

				panel.add(new BringMoney());

				bring.revalidate();
				bring.repaint();
			}
		});
	}

	private AccountNode getAccountNode() {
		return this;
	}

	private JPanel getCenter() {
		JPanel money = Layout.coverFlowlayout(0,
				new Label("잔액 : " + String.format("%,d", this.money) + " 원", 18, getForeground()));
		JPanel id = Layout.coverFlowlayout(0, new Label(
				accountId.replaceFirst("(^02|[0-9]{4})([0-9]{2})([0-9]{6})$", "$1-$2-$3"), 18, getForeground()));

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(Layout.coverFlowlayout(getBankIcon()), BorderLayout.WEST);
		panel.add(Layout.coverFlowlayout(0, Layout.coverVertical(money, id)));

		panel.setBackground(getBackground());
		return Layout.coverFlowlayout(0, panel);
	}

	private JLabel getBankIcon() {
		JLabel label = new JLabel();
		label.setIcon(Style.getImageIcon(40, 40, "\\icon\\kakao_bank_logo.png"));
		label.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 0));
		return label;
	}
}
