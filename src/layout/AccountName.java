package layout;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

import compoment.Label;
import compoment.Layout;
import compoment.TextButton;
import compoment.TextField;
import gui.AccountBuild;
import model.User;

public class AccountName extends JPanel {
	private JTextField name = new TextField(400, 50);

	public AccountName() {
		setLayout(new FlowLayout());

		JPanel panel = new JPanel(new BorderLayout(120, 120));
		panel.add(Layout.coverFlowlayout(FlowLayout.LEFT, new Label("계좌 이름을 입력하세요.", 30)), BorderLayout.NORTH);
		panel.add(getCenter(), BorderLayout.CENTER);
		panel.add(Layout.coverFlowlayout(new TextButton(400, 40, "다음", new ClickNextButton(), name)),
				BorderLayout.SOUTH);
		panel.setBorder(BorderFactory.createEmptyBorder(40, 0, 20, 0));

		add(panel);
	}

	private JPanel getCenter() {
		JPanel panel = new JPanel(new GridLayout(0, 1));

		JPanel info = new JPanel(new GridLayout(0, 1));
		for (String data : new String[] { "이름 : " + User.getName(), "주민등록번호 : " + User.getBirth() + " - *******",
				"전화번호 : " + User.getPhone() }) {
			info.add(Layout.coverFlowlayout(FlowLayout.LEFT, new Label(data, 20)));
		}

		panel.add(Layout.coverFlowlayout(FlowLayout.LEFT, info));
		panel.add(Layout.coverFlowlayout(Layout.coverVertical(
				Layout.coverFlowlayout(FlowLayout.LEFT, new Label("통장명")), Layout.coverFlowlayout(name))));

		return Layout.coverFlowlayout(panel);
	}

	private class ClickNextButton implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JPanel panel = AccountBuild.getInstance().getPanel();
			panel.removeAll();

			AccountBuild.getInstance().getAccount().setName(name.getText());

			panel.add(new AccountPassword());

			AccountBuild.getInstance().revalidate();
			AccountBuild.getInstance().repaint();
		}
	}
}
