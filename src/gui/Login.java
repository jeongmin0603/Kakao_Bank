package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Login extends Frame {
	private static Login instance = new Login();
	
	public static void main(String[] args) {
		Login.getInstance().setVisible(true);
	}	
	
	public static Login getInstance() {
		if(instance == null) instance = new Login();
		return instance;
	}
	
	@Override
	public void dispose() {
		instance = null;
		super.dispose();
	}
	
	private Login() {
		super(500, 850, "로그인");
		Macro.changeJPanelColor(Color.white);
		
		JPanel panel = new JPanel(new GridLayout(1, 1));
		JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP);
		tab.addTab("아이디 & 비밀번호", new LoginOfIDAndPW());
		tab.addTab("간편인증번호", new JPanel());
		
		panel.add(tab);
		add(panel);
	}

}
