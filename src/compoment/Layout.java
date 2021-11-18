package compoment;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class Layout {
	
	public static JPanel coverFlowlayout(Component... components) {
		JPanel panel = new JPanel(new FlowLayout());

		for (Component obj : components)
			panel.add(obj);

		panel.setOpaque(false);
		return panel;
	}

	public static JPanel coverVertical(Component... components) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

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

	public static JPanel combine(LayoutManager layout, Component... components) {
		JPanel panel = new JPanel(layout);

		for (Component obj : components)
			panel.add(obj);

		panel.setOpaque(false);
		return panel;
	}
	
}
