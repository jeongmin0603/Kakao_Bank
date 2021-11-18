package gui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Style {
	public static final Color MAIN_BROWN = new Color(66, 54, 48);
	public static final Color MAIN_YELLOW = new Color(251, 226, 1);
	public static final Color MAIN_DARKBLUE = new Color(52, 59, 88);
	
	public static ImageIcon getImageIcon(int w, int h, String path) {
		return new ImageIcon(new ImageIcon(System.getProperty("user.dir") + "\\images\\" + path).getImage()
				.getScaledInstance(w, h, Image.SCALE_SMOOTH));
	}

	public static ImageIcon getImageIcon(int w, int h, BufferedImage img) {
		return new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
	}

	public static Font getFont(int style, int size) {
		return new Font("맑은 고딕", style, size);
	}

	public static ImageIcon getCircleImageIcon(int w, int h, String path) {
		try {
			BufferedImage img = ImageIO.read(new File(System.getProperty("user.dir") + "\\images\\" + path));

			BufferedImage output = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);

			Graphics2D g2 = output.createGraphics();

			g2.setComposite(AlphaComposite.Src);
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.fill(new RoundRectangle2D.Float(0, 0, img.getWidth(), img.getHeight(), 360, 360));
			g2.setComposite(AlphaComposite.SrcAtop);
			g2.drawImage(img, 0, 0, null);
			g2.dispose();

			return getImageIcon(w, h, output);

		} catch (IOException e) {
			e.printStackTrace();

			return null;
		}
	}
}
