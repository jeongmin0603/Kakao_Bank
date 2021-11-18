package layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.simple.JSONObject;

import compoment.Label;
import compoment.Layout;
import compoment.RandomKeyPad;
import compoment.TextButton;
import compoment.TextField;
import gui.Home;
import gui.Login;
import gui.Style;
import model.Server;

public class LoginEasyKey extends JPanel {
	private JLabel error = new Label("", 18, Color.red);
	private JTextField id = new TextField(350, 30);
	private JTextField[] numbers = new JTextField[6];

	public LoginEasyKey() {
		setLayout(new FlowLayout());

		JPanel panel = new JPanel(new BorderLayout(50, 50));
		panel.add(Layout.coverFlowlayout(new Label("간편 로그인", 1, 30)), BorderLayout.NORTH);
		panel.add(getCenter(), BorderLayout.CENTER);
		panel.add(Layout.coverFlowlayout(new TextButton(350, 50, "로그인", new ClickLoginButton(), numbers[0], numbers[1],
				numbers[2], numbers[3], numbers[4], numbers[5], id)), BorderLayout.SOUTH);

		setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
		add(panel);
	}

	private JPanel getCenter() {
		JPanel panel = new JPanel(new BorderLayout(40, 40));

		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = new TextField(55, 50);
			numbers[i].setHorizontalAlignment(JLabel.CENTER);
			numbers[i].setFont(Style.getFont(0, 25));
			numbers[i].setFocusable(false);
		}

		panel.add(Layout.combine(new GridLayout(0, 1, 20, 20), Layout.coverFlowlayout(id),
				Layout.coverFlowlayout(numbers[0], numbers[1], numbers[2], numbers[3], numbers[4], numbers[5])), BorderLayout.NORTH);
		panel.add(Layout.coverFlowlayout(new RandomKeyPad(160, 45, numbers)), BorderLayout.CENTER);
		panel.add(Layout.coverFlowlayout(error), BorderLayout.SOUTH);

		return Layout.coverFlowlayout(panel);
	}

	private class ClickLoginButton implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JSONObject json = new JSONObject();
			json.put("easyLoginId", id.getText());
			json.put("key", numbers[0].getText() + numbers[1].getText() + numbers[2].getText() + numbers[3].getText()
					+ numbers[4].getText() + numbers[5].getText());

			System.out.println(json);
			
			try (Server post = new Server("POST", "/auth/easy/login", json)) {
				if (post.getResponsesCode() == 200) {
					Login.parseUserJSon(post.getResponsesBody());

					Home.getInstance().setVisible(true);
					Login.getInstance().dispose();

				} else {
					error.setText("<html>간편 비밀번호를 설정하지 않았거나,<br>아이디 또는 비밀번호를 확인해주세요.</html>");
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
