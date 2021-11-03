package compoment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.AccountBuild;
import gui.Macro;
import model.User;

public class UserInfo extends JPanel {
	private JLabel error = Macro.getLabel("", 18, Color.red);
	private JTextField name = Macro.getTextField(400, 30);
	private JTextField birth = Macro.getTextField(250, 30);
	
	public UserInfo() {
		setLayout(new FlowLayout());
		
		JPanel panel = new JPanel(new BorderLayout(120, 120));
		panel.add(Macro.coverFlowlayout(FlowLayout.LEFT, Macro.getLabel("개인 정보 확인", 30)), BorderLayout.NORTH);
		panel.add(getCenter(), BorderLayout.CENTER);
		panel.add(
				Macro.coverVertical(Macro.coverFlowlayout(error),
						Macro.coverFlowlayout(new TextButton(400, 40, "다음", new CheckUserInfo(), name, birth))),
				BorderLayout.SOUTH);		
		panel.setBorder(BorderFactory.createEmptyBorder(40, 0, 20, 0));
		
		add(panel);
	}
	
	private JPanel getCenter() {		
		JPanel panel = new JPanel(new GridLayout(0, 1, 30, 30));
		birth.addKeyListener(new BirthKeyListener());
		panel.add(Macro.coverFlowlayout(Macro.coverVertical(
				Macro.coverFlowlayout(FlowLayout.LEFT, Macro.getLabel("이름")), Macro.coverFlowlayout(name))));
		panel.add(Macro
				.coverFlowlayout(Macro.coverVertical(Macro.coverFlowlayout(FlowLayout.LEFT, Macro.getLabel("주민등록번호")),
						Macro.coverFlowlayout(birth, Macro.getLabel(" - *******", 35, Color.LIGHT_GRAY)))));
		return panel;
	}
	
	private class CheckUserInfo implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!User.getBirth().equals(birth.getText()) || !User.getName().equals(name.getText())) {
				error.setText("이름 또는 주민등록 앞자리가 일치하지 않습니다.");
				return;
			}
			
			JPanel panel = AccountBuild.getInstance().getPanel();
			panel.removeAll();
			panel.add(new AccountName());
			
			AccountBuild.getInstance().revalidate();
			AccountBuild.getInstance().repaint();
		}
	}
	
	private class BirthKeyListener implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			if (!String.valueOf(e.getKeyChar()).matches("^[0-9]+$") && e.getKeyCode() != 8) {
				JOptionPane.showMessageDialog(null, "숫자만 입력 가능합니다.", "경고", JOptionPane.ERROR_MESSAGE);
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	}

}
