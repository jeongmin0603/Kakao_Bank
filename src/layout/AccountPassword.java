package layout;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.simple.JSONObject;

import compoment.Label;
import compoment.Layout;
import compoment.RandomKeyPad;
import compoment.TextButton;
import gui.AccountBuild;
import gui.Home;
import model.Account;
import model.Server;

public class AccountPassword extends JPanel {
	JTextField[] number = new JTextField[4];

	public AccountPassword() {
		setLayout(new FlowLayout());

		JPanel panel = new JPanel(new BorderLayout(90, 90));
		panel.add(Layout.coverFlowlayout(FlowLayout.LEFT, new Label("계좌 비밀번호를 입력하세요.", 30)), BorderLayout.NORTH);
		panel.add(getCenter(), BorderLayout.CENTER);
		panel.add(Layout.coverFlowlayout(
				new TextButton(400, 40, "계설", new ClickBuildButton(), number[0], number[1], number[2], number[3])),
				BorderLayout.SOUTH);
		panel.setBorder(BorderFactory.createEmptyBorder(40, 0, 20, 0));

		add(panel);
	}

	private JPanel getCenter() {
		JPanel panel = new JPanel(new BorderLayout(40, 40));

		JPanel numbers = new JPanel(new FlowLayout());
		for (int i = 0; i < number.length; i++) {
			number[i] = new JTextField();
			number[i].setPreferredSize(new Dimension(80, 50));
			number[i].setFocusable(false);
			numbers.add(number[i]);
		}

		panel.add(
				Layout.coverFlowlayout(Layout.coverFlowlayout(
						Layout.coverVertical(Layout.coverFlowlayout(FlowLayout.LEFT, new Label("통장명")), numbers))),
				BorderLayout.NORTH);
		panel.add(Layout.coverFlowlayout(new RandomKeyPad(140, 50, number)), BorderLayout.CENTER);

		return Layout.coverFlowlayout(panel);
	}

	private class ClickBuildButton implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Account account = AccountBuild.getInstance().getAccount();

			account.setPw(number[0].getText() + number[1].getText() + number[2].getText() + number[3].getText());

			JSONObject json = new JSONObject();
			json.put("password", account.getPw());
			json.put("accountName", account.getName());

			try (Server server = new Server("POST", "/account", json)) {
				
				if(server.getResponsesCode() == 201 || server.getResponsesCode() == 200) {					
					JPanel panel =  AccountBuild.getInstance().getPanel();
					
					panel.removeAll();
					panel.add(new AccountComplete());
					
					AccountBuild.getInstance().revalidate();
					AccountBuild.getInstance().repaint();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
