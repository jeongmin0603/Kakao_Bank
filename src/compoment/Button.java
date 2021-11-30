package compoment;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Button extends JButton {

	public Button(int w, int h, String text, ActionListener action) {
		setText(text);
		setPreferredSize(new Dimension(w, h));
		addActionListener(action);
		setBackground(Style.MAIN_YELLOW);
		setBorder(null);
		setFocusPainted(false);
	}

	public Button(int w, int h, int style, int size, String text, ActionListener action) {
		setText(text);
		setFont(Style.getFont(style, size));
		setPreferredSize(new Dimension(w, h));
		addActionListener(action);
		setBackground(Style.MAIN_YELLOW);
		setBorder(null);
		setFocusPainted(false);
	}

	public Button(int w, int h, int style, int size, String text, Color foreground, Color background, ActionListener action) {
		setText(text);
		setFont(Style.getFont(style, size));
		setPreferredSize(new Dimension(w, h));
		addActionListener(action);
		setBackground(background);
		setForeground(foreground);
		setBorder(null);
		setFocusPainted(false);
	}
	
	

	public Button(int w, int h, String text, Color color, ActionListener action) {
		setText(text);
		setPreferredSize(new Dimension(w, h));
		addActionListener(action);
		setBackground(color);
		setBorder(null);
		setFocusPainted(false);
	}

}
