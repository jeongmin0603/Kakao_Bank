package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import compoment.Button;
import compoment.Layout;
import compoment.Style;

public class Start extends Frame {
	private static Start instance = null;

	public static void main(String[] args) {
		Start.getInstance().setVisible(true);
	}

	public static Start getInstance() {
		if (instance == null)
			instance = new Start();

		return instance;
	}

	@Override
	public void dispose() {
		instance = null;
		super.dispose();
	}

	private Start() {
		super(400, 750, "시작");
		UIManager.put("Button.background", Style.MAIN_BROWN);
		UIManager.put("Button.foreground", Color.white);

		UIManager.put("Panel.background", Style.MAIN_YELLOW);

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(getIcon(), BorderLayout.NORTH);
		panel.add(getSouth(), BorderLayout.SOUTH);

		add(panel);
	}

	private JPanel getSouth() {
		JPanel panel = new JPanel(new GridLayout(0, 1));

		panel.add(Layout.coverFlowlayout(new Button(300, 50, "로그인", Style.MAIN_BROWN, new ClickLogin())));
		panel.add(Layout.coverFlowlayout(new Button(300, 50, "회원가입", Style.MAIN_BROWN, new ClickRegister())));

		panel.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
		return Layout.coverFlowlayout(panel);
	}

	private JPanel getIcon() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.add(new JLabel(Style.getImageIcon(120, 120, "\\icon\\kakao.png")));
		panel.setBorder(BorderFactory.createEmptyBorder(100, 0, 100, 0));
		return panel;
	}

	private class ClickLogin implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Login.getInstance().setVisible(true);
			dispose();
		}
	}

	private class ClickRegister implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Register.getInstance().setVisible(true);
			dispose();
		}
	}
}
