package compoment;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import gui.Style;

public class Button extends JButton {
	
	public Button(int w, int h, String text, ActionListener action) {
		setText(text);
		setPreferredSize(new Dimension(w, h));
		addActionListener(action);
		setBackground(Style.MAIN_YELLOW);
		setBorder(null);
		setFocusPainted(false);
	}

}
