package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class Start extends Frame {
	private static Start instance = new Start();
	
	public static void main(String[] args) {
		instance.getInstance().setVisible(true);;
	}
	
	public static Start getInstance() {
		return instance;
	}

	private Start() {
		super(500, 850, "시작");
		UIManager.put("Button.background", MAIN_BROWN);
		UIManager.put("Button.foreground", Color.white);
		Macro.changeJPanelColor(MAIN_YELLOW);

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(getNorth(), BorderLayout.NORTH);
		panel.add(getSouth(), BorderLayout.SOUTH);

		add(panel);
	}

	private JPanel getSouth() {
		JPanel panel = Macro.coverFlowlayout(
				Macro.combine(new GridLayout(0, 1), Macro.coverFlowlayout(Macro.getButton(350, 50, "로그인", v -> {
					Login.getInstance().setVisible(true);
					dispose();
				})), Macro.coverFlowlayout(Macro.getButton(350, 50, "회원가입", v -> {
					Register_Info.getInstance().setVisible(true);
					dispose();
				}))));

		return panel;
	}

	private JPanel getNorth() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.add(new JLabel(Macro.getImageIcon(150, 150, "logo.png")));
		panel.setBorder(BorderFactory.createEmptyBorder(100, 0, 100, 0));
		return panel;
	}

}
