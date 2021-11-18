package compoment;

import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;

public class ImageComboBox extends JComboBox<String> {
	Map<String, Icon> map = new HashMap<>();
	
	public ImageComboBox() {
		setRenderer(new IconListRenderer());
	}
	
	public void put(String name, ImageIcon icon) {
		addItem(name);
		map.put(name, icon);
	}
	
	private class IconListRenderer extends DefaultListCellRenderer {
		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
	        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus); 

	        label.setIcon(map.get((String) value)); 
	        return label; 
		}
	}

}
