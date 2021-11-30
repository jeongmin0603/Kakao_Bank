package compoment;

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
	public static final Color[] ACCOUNT_COLOR = { 
			new Color(251, 226, 1), 
			new Color(75, 71, 55), 
			new Color(157, 145, 92),
			new Color(242, 254, 220), 
			new Color(27, 61, 47), 
			new Color(253, 249, 240), 
			new Color(25, 61, 47),
			new Color(105, 96, 0), 
			new Color(0, 107, 94), 
			new Color(99, 186, 171), 
			new Color(251, 226, 1),
			new Color(140, 210, 71),
			new Color(4, 181, 116),
			new Color(0, 146, 137),
			new Color(0, 108, 126),
			new Color(47, 72, 88),
			new Color(201, 164, 141),
			new Color(166, 204, 186),
			new Color(255, 196, 169),
			new Color(251, 141, 117),
			new Color(190, 89, 68),
			new Color(0, 0, 0) 
	};

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
