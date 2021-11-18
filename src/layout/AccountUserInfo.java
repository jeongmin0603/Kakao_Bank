package layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import compoment.Label;
import compoment.Layout;
import compoment.TextButton;
import compoment.TextField;
import event.NumberKeyListener;
import gui.AccountBuild;
import model.User;

public class AccountUserInfo extends JPanel {
	private JLabel error = new Label("", 18, Color.red);
	private JTextField name = new TextField(400, 30);
	private JTextField birth = new TextField(250, 30);

	public AccountUserInfo() {
		setLayout(new FlowLayout());

		JPanel panel = new JPanel(new BorderLayout(120, 120));
		panel.add(Layout.coverFlowlayout(FlowLayout.LEFT, new Label("개인 정보 확인", 30)), BorderLayout.NORTH);
		panel.add(getCenter(), BorderLayout.CENTER);
		panel.add(
				Layout.coverVertical(Layout.coverFlowlayout(error),
						Layout.coverFlowlayout(new TextButton(400, 40, "다음", new CheckUserInfo(), name, birth))),
				BorderLayout.SOUTH);
		panel.setBorder(BorderFactory.createEmptyBorder(40, 0, 20, 0));

		add(panel);
	}

	private JPanel getCenter() {
		JPanel panel = new JPanel(new GridLayout(0, 1, 30, 30));
		birth.addKeyListener(new NumberKeyListener(birth));
		panel.add(Layout.coverFlowlayout(Layout.coverVertical(Layout.coverFlowlayout(FlowLayout.LEFT, new Label("이름")),
				Layout.coverFlowlayout(name))));
		panel.add(Layout
				.coverFlowlayout(Layout.coverVertical(Layout.coverFlowlayout(FlowLayout.LEFT, new Label("주민등록번호")),
						Layout.coverFlowlayout(birth, new Label(" - *******", 35, Color.LIGHT_GRAY)))));
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

}
