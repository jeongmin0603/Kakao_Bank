package layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import compoment.Button;
import compoment.Label;
import compoment.Layout;
import compoment.Style;
import gui.Frame;
import gui.Home;
import gui.Remittance;

public class RemittanceComplete extends JPanel {
	
	public static void main(String[] args) {
		Frame jf = new Frame(500, 850, "테스트");
		UIManager.put("Panel.background", Color.white);
		
		jf.add(new RemittanceComplete());
	
		jf.setVisible(true);
	}
	
	public RemittanceComplete() {
		setLayout(new FlowLayout());
		
		JPanel panel = new JPanel(new BorderLayout(70, 70));
		panel.add(getNorth(), BorderLayout.NORTH);
		panel.add(getCenter(), BorderLayout.CENTER);
		panel.add(Layout.coverFlowlayout(new Button(300, 50, "확인", new ClickComfirm())), BorderLayout.SOUTH);
		panel.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
		
		add(panel);
	}
	
	private JPanel getCenter() {
		JPanel panel = new JPanel(new GridLayout(3, 2, 20, 20));
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("받는계좌", "<html>카카오뱅크<br>" + "234234234234</html>");
		map.put("보낸 금액", String.format("%,d원", Remittance.getInstance().getMoney()));
		map.put("수수료", String.format("%,d원", (int) (Remittance.getInstance().getMoney() * Remittance.getFee())));
		
		for(String title : map.keySet()) {
			panel.add(Layout.coverFlowlayout(0, new Label(title, 20, Color.LIGHT_GRAY)));
			panel.add(Layout.coverFlowlayout(2, new Label(map.get(title), 20)));
		}
	
		return Layout.coverFlowlayout(panel);
	}
	
	private JPanel getNorth() {
		JPanel panel = new JPanel(new BorderLayout(10, 10));
		
		panel.add(new JLabel(Style.getImageIcon(60, 60, "\\check.png")), BorderLayout.CENTER);
		panel.add(Layout.coverFlowlayout(new Label("이체완료", 25)), BorderLayout.SOUTH);
		panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 70, 0));
		
		JPanel flow = Layout.coverFlowlayout(panel);
		flow.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.DARK_GRAY));
		
		return flow;
	}
	
	private class ClickComfirm implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Remittance.getInstance().dispose();
			
			Home.getInstance().setVisible(true);
		}
	}

}
