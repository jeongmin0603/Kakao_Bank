package layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.json.simple.JSONObject;

import compoment.Label;
import compoment.Layout;
import compoment.RandomKeyPad;
import compoment.TextButton;
import compoment.TextField;
import gui.Frame;
import gui.Remittance;
import model.Account;
import model.Receiver;
import model.Server;

public class RemittancePassword extends JPanel {
	private TextField[] numbers = new TextField[4];
	private Label error = new Label("", 15, Color.red);

	public static void main(String[] args) {
		Frame jf = new Frame(400, 750, "1234");
		jf.add(Layout.coverFlowlayout(new RemittancePassword()));
		jf.setVisible(true);;
	}
	public RemittancePassword() {
		setLayout(new FlowLayout());

		JPanel panel = new JPanel(new BorderLayout(80, 80));
		panel.add(Layout.coverFlowlayout(0, new Label("비밀번호 입력", 1, 30)), BorderLayout.NORTH);
		panel.add(getCenter(), BorderLayout.CENTER);
		panel.add(
				Layout.coverFlowlayout(
						Layout.coverVertical(Layout.coverFlowlayout(error), Layout.coverFlowlayout(new TextButton(300,
								50, "완료", new ClickComplete(), numbers[0], numbers[1], numbers[2], numbers[3])))),
				BorderLayout.SOUTH);
		panel.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));

		add(panel);
	}

	private JPanel getCenter() {
		JPanel panel = new JPanel(new BorderLayout());

		for (int i = 0; i < 4; i++) {
			numbers[i] = new TextField(50, 40);
			numbers[i].setHorizontalAlignment(JLabel.CENTER);
		}

		panel.add(Layout.coverFlowlayout(numbers[0], numbers[1], numbers[2], numbers[3]), BorderLayout.NORTH);
		panel.add(new RandomKeyPad(110, 70, numbers), BorderLayout.CENTER);

		return Layout.coverFlowlayout(panel);
	}

	private class ClickComplete implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JSONObject json = new JSONObject();

			Account account = Remittance.getInstance().getAccount();
			Receiver receiver = Remittance.getInstance().getReceiver();

			json.put("sendAccountId", account.getId());
			json.put("sendAccountPw",
					numbers[0].getText() + numbers[1].getText() + numbers[2].getText() + numbers[3].getText());
			json.put("receiveAccountId", receiver.getAccountId());
			json.put("money", Remittance.getInstance().getMoney());

			try (Server server = new Server("POST", "/remittance/send", json)) {
				int code = server.getResponsesCode();

				if (code == 200) {
					Remittance jf = Remittance.getInstance();
					
					jf.getPanel().removeAll();
					jf.getPanel().add(new RemittanceComplete());
					
					jf.revalidate();
					jf.repaint();
				} else if(code == 403) {
					error.setText("잘못된 비밀번호 입니다.");
				} else if(code == 409) {
					error.setText("자기 자신에게 보낼 수 없습니다.");
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
