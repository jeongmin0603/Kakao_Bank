package compoment;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.Style;

public class RandomKeyPad extends JPanel {
	private JTextField[] numbers;
	private int index = 0;

	public RandomKeyPad(int w, int h, JTextField[] numbers) {
		setBackground(Color.white);
		setLayout(new GridLayout(4, 3));
		this.numbers = numbers;

		List<String> list = getRandomNumList();

		for (int i = 0; i < list.size(); i++) {
			JButton btn = new JButton(list.get(i));
			btn.setSelected(false);
			btn.setBackground(Color.white);
			btn.setName(list.get(i).equals("←") ? "remove" : list.get(i));
			btn.setFont(Style.getFont(1, 20));
			btn.setBorder(null);
			btn.setFocusable(false);
			btn.setPreferredSize(new Dimension(w, h));
			btn.addActionListener(new ClickButton(btn.getName()));
			add(btn);
		}
	}

	private List<String> getRandomNumList() {
		List<String> list = new ArrayList<String>();

		for (int i = 0; i < 10; i++)
			list.add(i + "");
		Collections.shuffle(list);
		list.add(9, "");
		list.add(11, "←");

		return list;
	}

	private class ClickButton implements ActionListener {
		String name;

		@Override
		public void actionPerformed(ActionEvent e) {
			if (name.isEmpty()) {
				return;
			} else if (name.equals("remove")) {
				if(index > 0) {
					index -= 1;
					numbers[index].setText("");
				}
			} else {
				if(index >= numbers.length) return;
				
				numbers[index].setText(name);
				index += 1;
			}

		}

		public ClickButton(String name) {
			this.name = name;
		}
	}
}
