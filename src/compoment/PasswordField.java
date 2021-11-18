package compoment;

import java.awt.Dimension;

import javax.swing.JPasswordField;

public class PasswordField extends JPasswordField {
	
	public PasswordField(int w, int h) {
		setPreferredSize(new Dimension(w, h));
	}

}
