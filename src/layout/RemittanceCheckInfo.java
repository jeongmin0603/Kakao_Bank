package layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import compoment.Button;
import compoment.Label;
import compoment.Layout;
import gui.Home;
import gui.Remittance;
import model.Account;
import model.Receiver;

public class RemittanceCheckInfo extends JPanel {
	private Receiver receiver;
	private Account account;

	public RemittanceCheckInfo(Account account, Receiver receiver) {
		setLayout(new FlowLayout());

		this.account = account;
		this.receiver = receiver;

		JPanel panel = new JPanel(new BorderLayout(120, 120));
		panel.add(getNorth(), BorderLayout.NORTH);
		panel.add(getCenter(), BorderLayout.CENTER);
		panel.add(getSouth(), BorderLayout.SOUTH);

		add(panel);
	}

	private JPanel getNorth() {
		JPanel panel = new JPanel(new FlowLayout());

		panel.add(new Label(receiver.getName() + "에게 이체하시겠습니까?", 0, 25));
		panel.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));

		JPanel flow = Layout.coverFlowlayout(panel);
		flow.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));

		return flow;
	}

	private JPanel getCenter() {
		JPanel panel = new JPanel(new GridLayout(0, 2, 60, 40));

		Map<String, String> data = new HashMap<String, String>();
		data.put("출금계좌", account.getId());
		data.put("받는계좌", receiver.getAccountId());
		data.put("보낼금액", String.format("%,d원", Remittance.getInstance().getMoney()));
		data.put("수수료", String.format("%,d원", (int) (Remittance.getInstance().getMoney() * Remittance.getFee())));

		for (String title : data.keySet()) {
			panel.add(Layout.coverFlowlayout(0, new Label(title, 18, Color.LIGHT_GRAY)));
			panel.add(Layout.coverFlowlayout(2, new Label(data.get(title), 18)));
		}

		return Layout.coverFlowlayout(panel);
	}

	private JPanel getSouth() {
		JPanel panel = new JPanel(new GridLayout(1, 0, 5, 5));
		panel.add(new Button(170, 40, "취소", new ClickCancel()));
		panel.add(new Button(170, 40, "확인", new ClickConfirm()));
		return panel;
	}

	private class ClickConfirm implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Remittance jf = Remittance.getInstance();

			jf.getPanel().removeAll();
			
			jf.getPanel().add(new RemittancePassword());
			
			jf.revalidate();
			jf.repaint();
		}
	}

	private class ClickCancel implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Remittance.getInstance().dispose();
			Home.getInstance().setVisible(true);
		}
	}

}
