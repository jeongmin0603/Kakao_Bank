package event;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class NumberKeyListener implements KeyListener {
	JTextField text;
	public NumberKeyListener(JTextField text) {
		this.text = text;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (!String.valueOf(e.getKeyChar()).matches("^[0-9]+$") && e.getKeyCode() != 8) {
			JOptionPane.showMessageDialog(null, "숫자만 입력 가능합니다.", "경고", JOptionPane.ERROR_MESSAGE);
			text.setText("");
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}