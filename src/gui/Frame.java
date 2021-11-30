package gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.plaf.FontUIResource;

import compoment.Style;

public class Frame extends JFrame {

	static {
		UIManager.put("OptionPane.background", Color.white);
		UIManager.put("TextField.font", new FontUIResource(Style.getFont(0, 17)));
		UIManager.put("TextField.border", BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
		UIManager.put("PasswordField.font", new FontUIResource(Style.getFont(0, 20)));
		UIManager.put("PasswordField.border", BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
		UIManager.put("Button.border", new LineBorder(Color.LIGHT_GRAY));
		UIManager.put("Button.font", new FontUIResource(Style.getFont(0, 15)));
		UIManager.put("RadioButton.font", new FontUIResource(Style.getFont(0, 15)));
		UIManager.put("RadioButton.background", Color.white);
		UIManager.put("TabbedPane.font", new FontUIResource(Style.getFont(0, 15)));
		UIManager.put("ComboBox.background", Color.white);
		UIManager.put("ComboBox.font", new FontUIResource(Style.getFont(0, 15)));
		UIManager.put("ComboBox.border", new LineBorder(Color.LIGHT_GRAY));
		UIManager.put("Panel.font", new FontUIResource(Style.getFont(0, 15)));
	}

	public Frame(int w, int h, String title) {
		setSize(w, h);
		setTitle(title);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setIconImage(Style.getImageIcon(100, 100, "\\icon\\kakao.png").getImage());
		setFont(Style.getFont(0, 15));
	}

}
