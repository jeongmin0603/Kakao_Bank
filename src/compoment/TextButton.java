package compoment;

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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import gui.Style;

public class TextButton extends JButton {
	private static Color unenable = new Color(246, 246, 246);
	private static Color able = new Color(251, 226, 1);
	private JTextField[] jTextFields;

	public TextButton(int w, int h, String text, ActionListener action, JTextField... jTextFields) {
		setText(text);
		setPreferredSize(new Dimension(w, h));
		setFont(Style.getFont(0, 15));
		setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		setBackground(unenable);
		setFocusPainted(false);
		setEnabled(false);
		setForeground(Color.white);
		addActionListener(action);

		this.jTextFields = jTextFields;

		for (JTextField t : jTextFields) {
			t.getDocument().addDocumentListener(new EmptyTextCheck());
		}
	}

	private class EmptyTextCheck implements DocumentListener {
		@Override
		public void changedUpdate(DocumentEvent e) {
			checkAllTextFieldNotEmpty();
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			checkAllTextFieldNotEmpty();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			checkAllTextFieldNotEmpty();
		}

		private void checkAllTextFieldNotEmpty() {
			for (JTextField t : jTextFields) {
				if (t.getText().isEmpty()) {
					setBackground(unenable);
					setForeground(Color.white);
					setEnabled(false);
					return;
				}
			}

			setForeground(Color.black);
			setBackground(able);
			setEnabled(true);
		}
	}
}
