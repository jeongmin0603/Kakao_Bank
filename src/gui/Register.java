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

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

import compoment.Button;
import compoment.Label;
import compoment.Layout;
import compoment.Style;
import compoment.TextButton;
import compoment.TextField;
import model.Server;
import model.User;

public class Register extends Frame {
	private static Register instance = new Register();

	private JLabel error = new Label("", 18, Color.red);
	private TextField id = new TextField(400, 30);
	private TextField pw = new TextField(400, 30);
	private TextField pw_check = new TextField(400, 30);
	private TextField phone = new TextField(400, 30);
	private TextField[] birth = new TextField[8];
	private TextField name = new TextField(400, 30);
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
		UIManager.put("Button.background", Style.MAIN_YELLOW);
		UIManager.put("Button.foreground", Color.black);

		UIManager.put("Panel.background", Color.white);

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(getpanel(), BorderLayout.CENTER);
		panel.add(Layout.coverFlowlayout(new Button(200, 40, "이전", new ClickLast()),
				new TextButton(200, 40, "다음", new ClickRegistert(), phone, id, pw, pw_check, birth[0], birth[1],
						birth[2], birth[3], birth[4], birth[5], birth[6], birth[7], name)),
				BorderLayout.SOUTH);

		add(panel);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Start.getInstance().setVisible(true);
			}
		});
	}

	private JPanel getpanel() {
		JPanel panel = new JPanel(new GridLayout(0, 1, 20, 20));

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

		panel.add(getInputPanel("아이디", id));
		panel.add(getInputPanel("비밀번호", pw));
		panel.add(getInputPanel("비밀번호 확인", pw_check));
		panel.add(getInputPanel("전화번호", phone));
		panel.add(getInputPanel("생년월일", birth[0], birth[0], birth[1], birth[2], birth[3], birth[4], birth[5], birth[6],
				birth[7]));
		panel.add(getInputPanel("이름(실명)", name));
		
		panel.add(Layout.coverFlowlayout(FlowLayout.LEFT, radio));
		panel.add(Layout.coverFlowlayout(FlowLayout.LEFT, error));

		panel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		return Layout.coverFlowlayout(panel);
	}

	private JPanel getInputPanel(String title, TextField... input) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(Layout.coverFlowlayout(FlowLayout.LEFT, new Label(title, 1, 15)), BorderLayout.NORTH);

		JPanel inputs = new JPanel(new FlowLayout());
		for (TextField text : input) {
			inputs.add(text);
		}

		panel.add(inputs, BorderLayout.CENTER);

		return Layout.coverFlowlayout(panel);
	}

	private TextField getNumberField(int w, int h, int index) {
		TextField text = new TextField(w, h);
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

	private class ClickLast implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			Start.getInstance().setVisible(true);
		}
	}

	private class ClickRegistert implements ActionListener {

		private boolean checkPW() {
			if (!pw.getText()
					.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$")) {
				pw.setText("");
				pw.requestFocus();
				error.setText("비밀번호를 확인해주세요.");
				return false;
			}

			if (!pw.getText().equals(pw_check.getText())) {
				pw_check.requestFocus();
				pw_check.setText("");
				error.setText("비밀번호가 일치하지 않습니다.");
				return false;
			}

			return true;
		}

		private boolean checkID() {
			if (!id.getText().matches("^[a-zA-Z]{1}[a-zA-Z0-9_]{3,12}$")) {
				id.setText("");
				id.requestFocus();
				error.setText("아이디를 확인해주세요.");
				return false;
			}

			return true;
		}

		private boolean checkNumber() {
			for (int i = 0; i < 8; i++) {
				if (birth[i].getText().length() != 1 || !birth[i].getText().matches("^[0-9]+$")) {
					for (int j = 0; j < 6; j++)
						birth[j].setText("");
					birth[0].requestFocus();
					error.setText("주민등록번호를 확인해주세요.");
					return false;
				}
			}
			return true;
		}

		private boolean isAgree() {
			if (!radio.isSelected()) {
				error.setText("약관을 체크해 주세요.");
				return false;
			}

			return true;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!(checkID() && checkPW() && checkNumber() && isAgree())) {
				return;
			}

			User.setId(id.getText());
			User.setBirth(birth[0].getText() + birth[1].getText() + birth[2].getText() + birth[3].getText()
					+ birth[4].getText() + birth[5].getText() + birth[6].getText() + birth[7].getText());
			User.setName(name.getText());
			User.setPhone(phone.getText());
			User.setPw(pw.getText());

			register();
		}

		private void register() {
			try (Server post = new Server("POST", "/auth/register", User.getJSON())) {
				int code = post.getResponsesCode();
				System.out.println("register : " + code);
				
				if (code == 200) {
					JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.", "확인", JOptionPane.INFORMATION_MESSAGE);

					Start.getInstance().setVisible(true);
					dispose();
				} else if (code == 403) {
					error.setText("아이디 또는 핸드폰번호가 중복되었습니다.");
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
