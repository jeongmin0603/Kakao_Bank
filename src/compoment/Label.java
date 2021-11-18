package compoment;

import java.awt.Color;

import javax.swing.JLabel;

import gui.Style;

public class Label extends JLabel {

	public Label(String text) {
		setText(text);
		setFont(Style.getFont(0, 15));
	}

	public Label(String text, int size) {
		setText(text);
		setFont(Style.getFont(0, size));
	}

	public Label(String text, int size, Color color) {
		setText(text);
		setForeground(color);
		setFont(Style.getFont(0, size));
	}

	public Label(String text, int style, int size, Color color) {
		setText(text);
		setForeground(color);
		setFont(Style.getFont(style, size));
	}

	public Label(String text, int style, int size) {
		setText(text);
		setFont(Style.getFont(style, size));
	}

}
