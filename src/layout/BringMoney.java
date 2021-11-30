package layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.json.simple.JSONObject;

import compoment.Label;
import compoment.Layout;
import compoment.Style;
import compoment.TextButton;
import compoment.TextField;
import gui.Bring;
import gui.Home;
import model.Account;
import model.Server;

public class BringMoney extends JPanel {
	private final static String[] UNIT = { "", "십", "백", "천", "만", "십만", "백만", "천만", "억", "십업", "백억", "천억" };
	private Label error = new Label("", 17, Color.red);
	private TextField money = new TextField();
	private Label korean = new Label("영", 17, Color.LIGHT_GRAY);
	private Account account;

	public BringMoney() {
		setLayout(new BorderLayout(120, 120));
		setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

		this.account = Bring.getInstance().getReceive();

		add(Bring.getInstance().getSend(), BorderLayout.NORTH);
		add(getCenter(), BorderLayout.CENTER);
		add(Layout.coverFlowlayout(Layout.coverVertical(Layout.coverFlowlayout(error),
				Layout.coverFlowlayout(new TextButton(300, 45, "다음", new ClickComplete(), money)))), BorderLayout.SOUTH);
	}
	
	private class ClickComplete implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!money.getText().replace(",", "").matches("^[0-9]+$")) {
				error.setText("숫자로 입력해주세요.");
				return;
			}
			
			JSONObject json = new JSONObject();
			json.put("sendAccountId", Bring.getInstance().getSend().getAccountId());
			json.put("receiveAccountId", Bring.getInstance().getReceive().getId());
			json.put("money", money.getText().replace(",", ""));
			
			try (Server server = new Server("POST", "/remittance/receive", json)) {
				if(server.getResponsesCode() == 200) {
					new BringComplete(Integer.parseInt(money.getText().replace(",", ""))).setVisible(true);
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			Bring.getInstance().dispose();
			Home.getInstance().setVisible(true);;
		}
	}

	private JPanel getCenter() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		panel.add(Layout.coverFlowlayout(money));
		panel.add(Layout.coverFlowlayout(korean));

		money.addKeyListener(new KeyMoney());
		money.addFocusListener(new FocusMoney());

		return Layout.coverFlowlayout(panel);
	}

	private String getKoreanNum() {
		String text = "";

		int j = money.getText().length() - 1;

		for (int i = 0; i < money.getText().length(); i++) {
			int n = money.getText().charAt(i) - '0';
			if (getNum(n) != null) {
				text += getNum(n) + UNIT[j];
			}
			j--;
		}

		return (text.length() == 0 ? "영" : text) + "원";
	}

	private String getNum(int num) {
		switch (num) {
		case 1:
			return "일";
		case 2:
			return "이";
		case 3:
			return "삼";
		case 4:
			return "사";
		case 5:
			return "오";
		case 6:
			return "육";
		case 7:
			return "칠";
		case 8:
			return "팔";
		case 9:
			return "구";
		}
		return null;
	}

	private class FocusMoney implements FocusListener {
		@Override
		public void focusGained(FocusEvent e) {
			money.setText(money.getText().replace(",", ""));
		}

		@Override
		public void focusLost(FocusEvent e) {
			if (money.getText().matches("^[0-9]+$")) {
				money.setText(String.format("%,d", Integer.parseInt(money.getText())));
			}
		}
	}

	private class KeyMoney implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (money.getText().matches("^[0-9]+$")) {
				korean.setText(getKoreanNum());
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
		}

	}

}
