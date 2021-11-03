package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class InitialScreen extends Frame {
	private static InitialScreen instance = new InitialScreen();

	public static void main(String[] args) {
		InitialScreen.getInstance().setVisible(true);
	}

	public static InitialScreen getInstance() {
		if (instance == null)
			instance = new InitialScreen();

		return instance;
	}

	private InitialScreen() {
		super(500, 850, "시작");
		UIManager.put("Button.background", MAIN_BROWN);
		UIManager.put("Button.foreground", Color.white);
		UIManager.put("Panel.background", MAIN_YELLOW);

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(getIcon(), BorderLayout.NORTH);
		panel.add(getButtons(), BorderLayout.SOUTH);

		add(panel);
	}

	private JPanel getButtons() {
		JPanel panel = Macro.coverFlowlayout(
				Macro.combine(new GridLayout(0, 1), Macro.coverFlowlayout(Macro.getButton(350, 50, "로그인", v -> {
					Login.getInstance().setVisible(true);
					dispose();
				})), Macro.coverFlowlayout(Macro.getButton(350, 50, "회원가입", v -> {
					Register.getInstance().setVisible(true);
					dispose();
				}))));

		panel.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
		return panel;
	}

	private JPanel getIcon() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.add(new JLabel(Macro.getImageIcon(150, 150, "logo.png")));
		panel.setBorder(BorderFactory.createEmptyBorder(100, 0, 100, 0));
		return panel;
	}
}
