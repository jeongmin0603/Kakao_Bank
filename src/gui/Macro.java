package gui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class Macro {

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

	public static JPanel coverFlowlayout(Component... components) {
		JPanel panel = new JPanel(new FlowLayout());

		for (Component obj : components)
			panel.add(obj);

		panel.setOpaque(false);
		return panel;
	}

	public static JPanel coverFlowlayout(int style, Component... components) {
		JPanel panel = new JPanel(new FlowLayout(style));

		for (Component obj : components)
			panel.add(obj);

		panel.setOpaque(false);
		return panel;
	}

	public static JLabel getLabel(String text) {
		JLabel label = new JLabel(text);
		label.setFont(getFont(0, 15));
		return label;
	}

	public static JLabel getLabel(String text, int size) {
		JLabel label = new JLabel(text);
		label.setFont(getFont(0, size));
		return label;
	}

	public static JLabel getLabel(String text, int size, Color color) {
		JLabel label = new JLabel(text);
		label.setForeground(color);
		label.setFont(getFont(0, size));
		return label;
	}

	public static JLabel getLabel(String text, int style, int size) {
		JLabel label = new JLabel(text);
		label.setFont(getFont(style, size));
		return label;
	}

	public static void changeJPanelColor(Color color) {
		UIManager.put("Panel.background", color);
	}

	public static JButton getButton(int w, int h, String text, ActionListener action) {
		JButton btn = new JButton(text);
		btn.setPreferredSize(new Dimension(w, h));
		btn.addActionListener(action);
		return btn;
	}

}
