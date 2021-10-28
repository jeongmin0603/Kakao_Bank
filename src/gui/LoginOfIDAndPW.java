package gui;

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

import model.Server;

public class LoginOfIDAndPW extends JPanel {
	JTextField id = Macro.getTextField(350, 30);
	JTextField pw = Macro.getPWField(350, 30);
	JLabel error = Macro.getLabel("", 18, Color.red);

	public LoginOfIDAndPW() {
		setLayout(new FlowLayout());

		JPanel panel = new JPanel(new BorderLayout(130, 130));
		panel.add(Macro.coverFlowlayout(FlowLayout.LEFT, Macro.getLabel("로그인", 1, 30)), BorderLayout.NORTH);
		panel.add(getCenter(), BorderLayout.CENTER);
		panel.add(Macro.coverFlowlayout(new TextButton(350, 50, "로그인", new ClickLogin(), id, pw)), BorderLayout.SOUTH);

		setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
		add(panel);
	}

	private JPanel getCenter() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		panel.add(getInput("아이디", id));
		panel.add(getInput("비밀번호", pw));
		panel.add(Macro.coverFlowlayout(FlowLayout.LEFT, error));

		panel.setPreferredSize(new Dimension(350, 210));
		return Macro.coverFlowlayout(panel);
	}

	private JPanel getInput(String text, JTextField input) {
		JPanel panel = new JPanel(new BorderLayout());

		panel.add(Macro.coverFlowlayout(FlowLayout.LEFT, Macro.getLabel(text)), BorderLayout.NORTH);
		panel.add(Macro.coverFlowlayout(input), BorderLayout.CENTER);

		return Macro.coverFlowlayout(panel);
	}

	private class ClickLogin implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			JSONObject json = new JSONObject();
			json.put("id", id.getText());
			json.put("pw", pw.getText());

			try (Server post = new Server("POST", "/auth/login", json)) {

				if (post.getResponsesCode() == 200) {
					Login.getInstance().parseJSon(post.getResponsesBody());
					
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
