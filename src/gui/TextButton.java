package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class TextButton extends JButton {
	private static Color unenable = new Color(246, 246, 246);
	private static Color able = new Color(251, 226, 1);
	private JTextField[] jTextFields;

	public TextButton(int w, int h, String text, JTextField... jTextFields) {
		setText(text);
		setPreferredSize(new Dimension(w, h));
		setFont(Macro.getFont(0, 15));
		setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		setBackground(unenable);
		setFocusPainted(false);
		setEnabled(false);

		this.jTextFields = jTextFields;

		for (JTextField t : jTextFields) {
			t.addKeyListener(new EmptyTextCheck());
		}
	}

	private class EmptyTextCheck extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent e) {
			for (JTextField t : jTextFields) {
				if (t.getText().isEmpty()) {
					setBackground(unenable);
					setEnabled(false);
					return;
				}
			}

			setBackground(able);
			setEnabled(true);
		}
	}
}
