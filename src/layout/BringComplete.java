package layout;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import compoment.Button;
import compoment.Label;
import compoment.Layout;
import compoment.Style;
import gui.Bring;
import gui.Home;

public class BringComplete extends JDialog {

	public static void main(String[] args) {
		new BringComplete(1000).setVisible(true);
	}
	public BringComplete(int money) {
		setModal(true);
		setSize(300, 250);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		UIManager.put("Panel.background", Color.white);
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(getNorth(), BorderLayout.NORTH);
		panel.add(Layout.coverFlowlayout(new Label(String.format("%,d", money) + "원을 가져왔습니다.")), BorderLayout.CENTER);
		panel.add(Layout.coverFlowlayout(new Button(200, 30, "확인", v -> {
			dispose();
			Bring.getInstance().dispose();
			Home.getInstance().setVisible(true);
		})), BorderLayout.SOUTH);
		
		add(panel);
		
	}
	
	private JPanel getNorth() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(Layout.coverFlowlayout(new JLabel(Style.getImageIcon(50, 50, "\\check.png"))), BorderLayout.CENTER);
		panel.add(Layout.coverFlowlayout(new Label("가져오기 완료")), BorderLayout.SOUTH);
		
		return Layout.coverFlowlayout(panel);
	}

}
