package layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.UIManager;

import compoment.Button;
import compoment.Label;
import compoment.Layout;
import gui.AccountBuild;
import gui.Home;

public class AccountComplete extends JPanel {

	public static void main(String[] args) {
		UIManager.put("Panel.background", Color.white);
		AccountBuild jf = AccountBuild.getInstance();
		jf.getPanel().removeAll();

		jf.getPanel().add(new AccountComplete());

		jf.setVisible(true);
	}

	public AccountComplete() {
		setLayout(new BorderLayout(100,100));
		
		add(getNorth(), BorderLayout.NORTH);
		add(getCenter(), BorderLayout.CENTER);
		add(Layout.coverFlowlayout(new Button(300, 50, "완료", new ClickComplete())), BorderLayout.SOUTH);
		
		setBorder(BorderFactory.createEmptyBorder(70, 0, 70, 0));
	}

	private JPanel getNorth() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(Layout.coverFlowlayout(new Label("입출금통장 개설완료", 1, 30)), BorderLayout.CENTER);
		panel.add(Layout.coverFlowlayout(new Label("<html>입출금 통장이 개설되었습니다.<br>아래의 내용을 확인해주세요.</html>", 20, Color.LIGHT_GRAY)), BorderLayout.SOUTH);
		
		return Layout.coverFlowlayout(panel);
	}
	
	private JPanel getCenter() {
		JPanel panel = new JPanel(new GridLayout(2, 2, 20, 20));
		
		panel.add(Layout.coverFlowlayout(FlowLayout.LEFT, new Label("계좌종류", 20, Color.gray)));
		panel.add(Layout.coverFlowlayout(FlowLayout.RIGHT, new Label("자유입출금", 20)));
		panel.add(Layout.coverFlowlayout(FlowLayout.LEFT, new Label("이체한도", 20, Color.gray)));
		panel.add(Layout.coverFlowlayout(FlowLayout.RIGHT, new Label("1일 최대 1만원", 20)));
		panel.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
		
		JPanel flow = Layout.coverFlowlayout(panel);
		flow.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.DARK_GRAY));
		
		return flow;
	}
	
	private class ClickComplete implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			AccountBuild.getInstance().dispose();
			
			Home.getInstance().setVisible(true);
		}
	}

}
