package compoment;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.simple.JSONObject;

import gui.AccountBuild;
import gui.Home;
import gui.Macro;
import model.Server;
import model.User;

public class AccountPassword extends JPanel {
	JTextField[] number = new JTextField[4];

	public AccountPassword() {
		setLayout(new FlowLayout());

		JPanel panel = new JPanel(new BorderLayout(90, 90));
		panel.add(Macro.coverFlowlayout(FlowLayout.LEFT, Macro.getLabel("계좌 비밀번호를 입력하세요.", 30)), BorderLayout.NORTH);
		panel.add(getCenter(), BorderLayout.CENTER);
		panel.add(Macro.coverFlowlayout(
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
				Macro.coverFlowlayout(Macro.coverFlowlayout(
						Macro.coverVertical(Macro.coverFlowlayout(FlowLayout.LEFT, Macro.getLabel("통장명")), numbers))),
				BorderLayout.NORTH);
		panel.add(Macro.coverFlowlayout(new RandomKeyPad(140, 50, number)), BorderLayout.CENTER);

		return Macro.coverFlowlayout(panel);
	}

	private class ClickBuildButton implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			AccountBuild.getInstance().setAccountName(
					number[0].getText() + number[1].getText() + number[2].getText() + number[3].getText());

			JSONObject json = new JSONObject();
			json.put("password", AccountBuild.getInstance().getAccountPW());
			json.put("accountName", AccountBuild.getInstance().getAccountName());

			try (Server server = new Server("POST", "/account", json)) {
				System.out.println(server.getResponsesCode());
				System.out.println(server.getResponsesBody());
				
				AccountBuild.getInstance().dispose();
				
				Home.getInstance().setVisible(true);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}

}
