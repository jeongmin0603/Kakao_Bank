package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class Start extends Frame {
	
	public static void main(String[] args) {
		new Start();
	}
	
	public Start() {
		super(500, 850, "로그인");
		Macro.changeJPanelColor(MAIN_YELLOW);
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(getNorth(), BorderLayout.NORTH);
		
		add(panel);
		setVisible(true);
	}
	
	private JPanel getNorth() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.add(new JLabel(Macro.getImageIcon(150, 150, "logo.png")));
		panel.setBorder(BorderFactory.createEmptyBorder(100, 0, 100, 0));
		return panel;
	}

}
