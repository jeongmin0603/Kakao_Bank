package gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.plaf.FontUIResource;

public class Frame extends JFrame {
	public static final Color MAIN_YELLOW = new Color(251, 226, 1);
	
	static {
		UIManager.put("TextField.font", new FontUIResource(Macro.getFont(0, 17)));
		UIManager.put("TextField.border", BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
		UIManager.put("PasswordField.font", new FontUIResource(Macro.getFont(0, 20)));
		UIManager.put("PasswordField.border", BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
		UIManager.put("Button.border", new LineBorder(Color.LIGHT_GRAY));
		UIManager.put("Button.background", MAIN_YELLOW);
		UIManager.put("Button.font", new FontUIResource(Macro.getFont(0, 15)));
		UIManager.put("RadioButton.font", new FontUIResource(Macro.getFont(0, 15)));
		UIManager.put("RadioButton.background", Color.white);
	}
	
	public Frame(int w, int h, String title) {
		setSize(w, h);
		setTitle(title);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setIconImage(Macro.getImageIcon(100, 100, "logo.png").getImage());
		setFont(Macro.getFont(0, 15));
	}

	
}