package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.json.simple.JSONObject;

import compoment.TextButton;
import model.Server;
import model.User;

public class Register extends Frame {
	private static Register instance = new Register();

	private JLabel profile = new JLabel(Macro.getCircleImageIcon(100, 100, "basic.png"));
	private JLabel error = Macro.getLabel("", 18, Color.red);
	
	private JTextField id = Macro.getTextField(400, 30);
	private JTextField pw = Macro.getTextField(400, 30);
	private JTextField pw_check = Macro.getTextField(400, 30);
	private JTextField phone = Macro.getTextField(400, 30);
	private JTextField[] birth = new JTextField[8];
	private JTextField name = Macro.getTextField(400, 30);
	private JRadioButton radio = new JRadioButton("약관 동의");

	public static void main(String[] args) {
		getInstance().setVisible(true);
	}

	public static Register getInstance() {
		if (instance == null)
			instance = new Register();
		return instance;
	}
	
	@Override
	public void dispose() {
		instance = null;
		super.dispose();
	}

	private Register() {
		super(500, 850, "회원가입");
		UIManager.put("Button.background", Frame.MAIN_YELLOW);
		UIManager.put("Button.foreground", Color.black);

		UIManager.put("Panel.background", Color.white);

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(getCenter(), BorderLayout.CENTER);
		panel.add(Macro.coverFlowlayout(Macro.getButton(200, 40, "이전", v -> {
			dispose();
			InitialScreen.getInstance().setVisible(true);
		}), new TextButton(200, 40, "다음", new ClickRegistertButton(), phone, id, pw, pw_check, birth[0], birth[1], birth[2],
				birth[3], birth[4], birth[5], birth[6], birth[7], name)), BorderLayout.SOUTH);

		add(panel);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				InitialScreen.getInstance().setVisible(true);
			}
		});
	}

	private JPanel getCenter() {
		JPanel panel = new JPanel(new BorderLayout());

		for (int i = 0; i < 8; i++) {
			birth[i] = getNumberField(45, 30, i);
		}

		phone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (phone.getText().length() == 11 && phone.getText().matches("^[0-9]+$"))
					phone.setText(phone.getText().replaceFirst("(^02|[0-9]{3})([0-9]{3,4})([0-9]{4})$", "$1-$2-$3"));
			}
		});

		JPanel center = new JPanel(new GridLayout(0, 1));
		center.add(getInputPanel("아이디", Macro.coverFlowlayout(id)));
		center.add(getInputPanel("비밀번호", Macro.coverFlowlayout(pw)));
		center.add(getInputPanel("비밀번호 확인", Macro.coverFlowlayout(pw_check)));
		center.add(getInputPanel("전화번호", Macro.coverFlowlayout(phone)));
		center.add(getInputPanel("생년월일", Macro.coverFlowlayout(birth[0], birth[0], birth[1], birth[2], birth[3],
				birth[4], birth[5], birth[6], birth[7])));
		center.add(getInputPanel("이름(실명)", Macro.coverFlowlayout(name)));
		center.add(Macro.coverFlowlayout(FlowLayout.LEFT, radio));
		center.add(Macro.coverFlowlayout(FlowLayout.LEFT, error));

		panel.add(Macro.coverFlowlayout(FlowLayout.LEFT, profile), BorderLayout.NORTH);
		panel.add(Macro.coverFlowlayout(center), BorderLayout.CENTER);
		panel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

		return Macro.coverFlowlayout(panel);
	}

	private JPanel getInputPanel(String title, JPanel input) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(Macro.coverFlowlayout(FlowLayout.LEFT, Macro.getLabel(title, 1, 15)), BorderLayout.NORTH);
		panel.add(input, BorderLayout.CENTER);

		return Macro.coverFlowlayout(panel);
	}

	private JTextField getNumberField(int w, int h, int index) {
		JTextField text = new JTextField();
		text.setPreferredSize(new Dimension(w, h));
		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (index + 1 != 8) {
					birth[index + 1].requestFocus();
				}
			}
		});
		return text;
	}

	private class ClickRegistertButton implements ActionListener {
		private boolean checkInfo() {
			String text = null;

			if (!id.getText().matches("^[a-zA-Z]{1}[a-zA-Z0-9_]{3,12}$")) {
				id.setText("");
				id.requestFocus();
				text = "아이디를 확인해주세요.";
			} else if (!pw.getText()
					.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$")) {
				pw.setText("");
				pw.requestFocus();
				text = "비밀번호를 확인해주세요.";
			} else if (!pw.getText().equals(pw_check.getText())) {
				pw_check.requestFocus();
				pw_check.setText("");
				text = "비밀번호가 일치하지 않습니다.";
			} else if (!numberCheck()) {
				for (int i = 0; i < 6; i++)
					birth[i].setText("");
				birth[0].requestFocus();
				text = "주민등록번호를 확인해주세요.";
			} else if (!radio.isSelected()) {
				text = "약관을 체크해 주세요.";
			}

			if (text != null) {
				error.setText(text);
				return false;
			}

			return true;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!checkInfo())
				return;
			error.setText("");

			User.setId(id.getText());
			User.setBirth(birth[0].getText() + birth[1].getText() + birth[2].getText() + birth[3].getText()
					+ birth[4].getText() + birth[5].getText() + birth[6].getText() + birth[7].getText());
			User.setName(name.getText());
			User.setPhone(phone.getText());
			User.setPw(pw.getText());

			try (Server post = new Server("POST", "/auth/register", User.getJSON())) {
				int code = post.getResponsesCode();
				
				if (code == 200) {
					JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.", "확인", JOptionPane.INFORMATION_MESSAGE);
					
					InitialScreen.getInstance().setVisible(true);
					dispose();
				} else if (code == 403) {
					error.setText("아이디 또는 핸드폰번호가 중복되었습니다.");
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

		private boolean numberCheck() {
			for (int i = 0; i < 8; i++) {
				if (birth[i].getText().length() != 1) {
					return false;
				}

				if (!birth[i].getText().matches("^[0-9]+$")) {
					return false;
				}
			}
			return true;
		}
	}

}
