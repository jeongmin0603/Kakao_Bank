package event;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class AmountOfMoneyListener implements FocusListener {
	private JTextField text;
	
	public AmountOfMoneyListener(JTextField text) {
		this.text = text;
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		text.setText(text.getText().replace(",", ""));
	}

	@Override
	public void focusLost(FocusEvent e) {
		try {
			if(text.getText().matches("^[0-9]+$")) {
				text.setText(String.format("%,d", Integer.parseInt(text.getText())));
			}			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

}
