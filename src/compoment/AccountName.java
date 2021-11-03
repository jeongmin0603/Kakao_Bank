package compoment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.AccountBuild;
import gui.Macro;
import model.User;

public class AccountName extends JPanel {
	private JTextField name = Macro.getTextField(400, 50);

	public AccountName() {
		setLayout(new FlowLayout());

		JPanel panel = new JPanel(new BorderLayout(120, 120));
		panel.add(Macro.coverFlowlayout(FlowLayout.LEFT, Macro.getLabel("계좌 이름을 입력하세요.", 30)), BorderLayout.NORTH);
		panel.add(getCenter(), BorderLayout.CENTER);
		panel.add(Macro.coverFlowlayout(new TextButton(400, 40, "다음", new ClickNextButton(), name)), BorderLayout.SOUTH);
		panel.setBorder(BorderFactory.createEmptyBorder(40, 0, 20, 0));

		add(panel);
	}

	private JPanel getCenter() {
		JPanel panel = new JPanel(new GridLayout(0, 1));

		JPanel info = new JPanel(new GridLayout(0, 1));
		for (String data : new String[] { "이름 : " + User.getName(), "주민등록번호 : " + User.getBirth() + " - *******",
				"전화번호 : " + User.getPhone() }) {
			info.add(Macro.coverFlowlayout(FlowLayout.LEFT, Macro.getLabel(data, 20)));
		}

		panel.add(Macro.coverFlowlayout(FlowLayout.LEFT, info));
		panel.add(Macro.coverFlowlayout(Macro.coverVertical(
				Macro.coverFlowlayout(FlowLayout.LEFT, Macro.getLabel("통장명")), Macro.coverFlowlayout(name))));

		return Macro.coverFlowlayout(panel);
	}
	
	private class ClickNextButton implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JPanel panel = AccountBuild.getInstance().getPanel();
			panel.removeAll();

			AccountBuild.getInstance().setAccountName(name.getText());
			
			panel.add(new AccountPassword());

			AccountBuild.getInstance().revalidate();
			AccountBuild.getInstance().repaint();
		}
	}
}
