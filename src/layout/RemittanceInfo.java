package layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.simple.JSONObject;

import compoment.ImageComboBox;
import compoment.Label;
import compoment.Layout;
import compoment.Style;
import compoment.TextButton;
import event.AmountOfMoneyListener;
import event.NumberKeyListener;
import gui.Remittance;
import model.Account;
import model.Receiver;
import model.Server;

public class RemittanceInfo extends JPanel {
	private JTextField amount = new JTextField();
	private JTextField number = new JTextField();
	private ImageComboBox bank = new ImageComboBox();
	private JLabel error = new Label("", 15, Color.red);
	private Account account;

	public RemittanceInfo(Account account) {
		setLayout(new BorderLayout(80, 80));
		setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));

		this.account = account;

		add(getNorth(), BorderLayout.NORTH);
		add(getCenter(), BorderLayout.CENTER);
		add(Layout.coverFlowlayout(Layout.coverVertical(Layout.coverFlowlayout(error),
				Layout.coverFlowlayout(new TextButton(400, 40, "다음", new ClickNextButton(), amount, number)))),
				BorderLayout.SOUTH);
	}

	private JPanel getNorth() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		panel.add(Layout.coverFlowlayout(0, new Label("계좌번호로 보내기", 0, 30)));
		panel.add(
				Layout.coverFlowlayout(0, new Label("잔액 " + String.format("%,d", account.getMoney()), 25, Color.gray)));

		return Layout.coverFlowlayout(0, panel);
	}

	private JPanel getCenter() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		amount.setPreferredSize(new Dimension(380, 30));
		number.setPreferredSize(new Dimension(250, 30));
		bank.setPreferredSize(new Dimension(130, 30));
		// amount.addKeyListener(new NumberKeyListener(amount));
		// number.addKeyListener(new NumberKeyListener(number));
		amount.addFocusListener(new AmountOfMoneyListener(amount));

		bank.put("카카오뱅크", Style.getImageIcon(20, 20, "\\icon\\kakao.png"));
		bank.put("토스", Style.getImageIcon(20, 20, "\\icon\\toss.png"));
		bank.put("신한 은행", Style.getImageIcon(20, 20, "\\icon\\shinhan.png"));

		panel.add(Layout.coverFlowlayout(Layout.combine(new GridLayout(0, 1),
				Layout.coverFlowlayout(FlowLayout.LEFT, new Label("금액")), Layout.coverFlowlayout(amount))));
		panel.add(Layout.coverFlowlayout(bank, Layout.coverFlowlayout(Layout.combine(new GridLayout(0, 1),
				Layout.coverFlowlayout(FlowLayout.LEFT, new Label("계좌번호")), Layout.coverFlowlayout(number)))));

		return Layout.coverFlowlayout(panel);
	}

	private class ClickNextButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try (Server server = new Server("GET", "/account/find/id/" + number.getText(), null)) {
				int code = server.getResponsesCode();

				if (code == 403) {
					error.setText("등록되지 않은 계좌번호 입니다.");
					return;
				} else if (code == 200) {
					JSONObject json = server.getResponsesBody();

					Receiver receiver = new Receiver((JSONObject) json.get("data"));

					if (JOptionPane.showConfirmDialog(null, receiver.getName() + "이(가) 맞나요?", "확인",
							JOptionPane.YES_NO_OPTION) == 0) {
						Remittance remittance = Remittance.getInstance();

						remittance.setMoney(Integer.parseInt(amount.getText().replace(",", "")));
						remittance.setReceiver(receiver);

						JPanel panel = remittance.getPanel();
						panel.removeAll();
						panel.add(new RemittanceCheckInfo(account, receiver));

						remittance.revalidate();
						remittance.repaint();
					}
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
	}

}
