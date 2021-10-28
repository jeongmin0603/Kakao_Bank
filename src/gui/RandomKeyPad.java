package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import javax.crypto.Mac;
import javax.swing.JButton;
import javax.swing.JPanel;

public class RandomKeyPad extends JPanel{
	
	public RandomKeyPad() {
		setBackground(Color.white);
		setLayout(new GridLayout(4, 3));
		
		List<String> list = getRandomNumList();
		
		for(int i = 0; i < list.size(); i++) {
			JButton btn = new JButton(list.get(i));
			
			btn.setSelected(false);
			btn.setBackground(Color.white);
			btn.setFont(Macro.getFont(1, 20));
			btn.setBorder(null);
			btn.setFocusable(false);
			btn.setPreferredSize(new Dimension(160, 80));
			add(btn);
		}
	}
	
	
	private List<String> getRandomNumList() {
		List<String> list = new ArrayList<String>();
		
		for(int i = 0; i < 10; i++) list.add(i + "");
		Collections.shuffle(list);
		list.add(9, " ");
		list.add(11, "←");
		
		return list;
	}
	
}
