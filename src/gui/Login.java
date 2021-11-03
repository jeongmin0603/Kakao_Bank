package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import org.json.simple.JSONObject;

import compoment.LoginOfEasyKey;
import compoment.LoginOfIDAndPW;
import model.User;

public class Login extends Frame {
	private static Login instance = new Login();

	public static void main(String[] args) {
		Login.getInstance().setVisible(true);
	}

	public static Login getInstance() {
		if (instance == null)
			instance = new Login();
		return instance;
	}

	@Override
	public void dispose() {
		super.dispose();
		instance = null;
	}

	private Login() {
		super(500, 850, "로그인");
		UIManager.put("Panel.background", Color.white);

		JPanel panel = new JPanel(new GridLayout(1, 1));
		JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP);
		tab.addTab("아이디 & 비밀번호", new LoginOfIDAndPW());
		tab.addTab("간편인증번호", new LoginOfEasyKey());

		panel.add(tab);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				InitialScreen.getInstance().setVisible(true);
			}
		});
		add(panel);
	}

	public void parseJSon(JSONObject json) {
		JSONObject user = (JSONObject) ((JSONObject) json.get("data")).get("user");
		
		User.setToken((String) ((JSONObject) json.get("data")).get("token"));
		User.setPhone((String) user.get("phone"));
		User.setName((String) user.get("name"));
		User.setBirth(((String) user.get("birth")));
		User.setId((String) user.get("id"));
	}
}
