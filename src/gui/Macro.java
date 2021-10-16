package gui;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Macro {

	public static ImageIcon getImageIcon(int w, int h, String path) {
		return new ImageIcon(new ImageIcon(System.getProperty("user.dir") + "\\images\\" + path).getImage()
				.getScaledInstance(w, h, Image.SCALE_SMOOTH));
	}
	
	public static Font getFont(int style, int size) {
		return new Font("맑은 고딕", style, size);
	}

}
