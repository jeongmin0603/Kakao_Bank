package layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.simple.JSONObject;

import compoment.Label;
import compoment.Layout;
import compoment.PasswordField;
import compoment.TextButton;
import compoment.TextField;
import gui.Home;
import gui.Login;
import model.Server;

public class LoginIDPW extends JPanel {
	
	JTextField id = new TextField(350, 30);
	JTextField pw = new PasswordField(350, 30);
	JLabel error = new Label("", 18, Color.red);

	public LoginIDPW() {
		setLayout(new FlowLayout());

		JPanel panel = new JPanel(new BorderLayout(130, 130));
		panel.add(Layout.coverFlowlayout(new Label("로그인", 1, 30)), BorderLayout.NORTH);
		panel.add(getCenter(), BorderLayout.CENTER);
		panel.add(Layout.coverFlowlayout(new TextButton(350, 50, "로그인", new ClickLogin(), id, pw)), BorderLayout.SOUTH);

		setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
		add(Layout.coverFlowlayout(panel));
	}

	private JPanel getCenter() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		panel.add(getInput("아이디", id));
		panel.add(getInput("비밀번호", pw));
		
		panel.add(Layout.coverFlowlayout(FlowLayout.LEFT, error));

		panel.setPreferredSize(new Dimension(350, 210));
		return Layout.coverFlowlayout(panel);
	}

	private JPanel getInput(String text, JTextField input) {
		JPanel panel = new JPanel(new BorderLayout());

		panel.add(Layout.coverFlowlayout(FlowLayout.LEFT, new Label(text)), BorderLayout.NORTH);
		panel.add(Layout.coverFlowlayout(input), BorderLayout.CENTER);

		return Layout.coverFlowlayout(panel);
	}

	private class ClickLogin implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			JSONObject json = new JSONObject();
			json.put("id", id.getText());
			json.put("pw", pw.getText());

			try (Server post = new Server("POST", "/auth/login", json)) {

				if (post.getResponsesCode() == 200) {
					Login.parseUserJSon(post.getResponsesBody());
					
					Home.getInstance().setVisible(true);
					Login.getInstance().dispose();
				} else {
					error.setText("아이디 혹은 비밀번호를 확인해주세요.");
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
