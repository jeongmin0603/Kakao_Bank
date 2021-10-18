package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.crypto.Mac;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class SignUp extends Frame {
	private JLabel profile = new JLabel(Macro.getCircleImageIcon(100, 100, "basic.png"));
	private JTextField id = getTextField(300, 30);
	private JTextField pw = getTextField(400, 30);
	private JTextField pw_check = getTextField(400, 30);
	private JTextField email = getTextField(400, 30);
	private JTextField[] number = new JTextField[9];
	private JTextField name = getTextField(400, 30);
	private JTextField nickname = getTextField(300, 30);
	private JRadioButton radio = new JRadioButton("약관 동의");
	
	public static void main(String[] args) {
		new SignUp();
	}

	public SignUp() {
		super(500, 900, "회원가입");
		Macro.changeJPanelColor(Color.white);

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(getCenter(), BorderLayout.CENTER);
		panel.add(Macro.coverFlowlayout(Macro.getButton(200, 40, "이전", v -> {
			dispose();
			new Start();
		}), new TextButton(200, 40, "다음", id, pw, pw_check)), BorderLayout.SOUTH);

		add(panel);
		setVisible(true);
	}

	private JPanel getCenter() {
		JPanel panel = new JPanel(new BorderLayout());

		for (int i = 0; i < 9; i++) {
			number[i] = getTextField(37, 30);
		}
		
		JPanel center = new JPanel(new GridLayout(0, 1));
		center.add(getInputPanel("아이디", Macro.coverFlowlayout(id, new TextButton(90, 35, "중복 확인", id))));
		center.add(getInputPanel("비밀번호", Macro.coverFlowlayout(pw)));
		center.add(getInputPanel("비밀번호 확인", Macro.coverFlowlayout(pw_check)));
		center.add(getInputPanel("이메일", Macro.coverFlowlayout(email)));
		center.add(getInputPanel("주민등록번호", Macro.coverFlowlayout(number[0], number[1], number[2], number[3], number[4],
				number[5], number[6], number[7], Macro.getLabel("-", 30, Color.LIGHT_GRAY), number[8])));
		center.add(getInputPanel("이름(실명)", Macro.coverFlowlayout(name)));
		center.add(getInputPanel("별명", Macro.coverFlowlayout(nickname, new TextButton(90, 35, "중복 확인", nickname))));
		center.add(Macro.coverFlowlayout(FlowLayout.LEFT, radio));
		center.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		
		panel.add(Macro.coverFlowlayout(FlowLayout.LEFT, profile), BorderLayout.NORTH);
		panel.add(Macro.coverFlowlayout(center), BorderLayout.CENTER);

		return Macro.coverFlowlayout(panel);
	}

	private JPanel getInputPanel(String title, JPanel input) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(Macro.coverFlowlayout(FlowLayout.LEFT, Macro.getLabel(title, 1, 15)), BorderLayout.NORTH);
		panel.add(input, BorderLayout.CENTER);

		return Macro.coverFlowlayout(panel);
	}

	private JTextField getTextField(int w, int h) {
		JTextField text = new JTextField();
		text.setPreferredSize(new Dimension(w, h));
		return text;
	}
}
