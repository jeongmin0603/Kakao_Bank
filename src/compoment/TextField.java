package compoment;

import java.awt.Dimension;

import javax.swing.JTextField;

public class TextField extends JTextField {
	
	public TextField(int w, int h) {
		setPreferredSize(new Dimension(w, h));
	}

}
