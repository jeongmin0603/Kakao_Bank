package layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicScrollBarUI;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import compoment.AccountNode;
import compoment.Label;
import compoment.Layout;
import model.Account;
import model.Server;

public class BringAccountList extends JPanel {
	Account account;
	public BringAccountList(Account account) {
		setLayout(new BorderLayout());
		
		this.account = account;
		
		add(Layout.coverFlowlayout(0, new Label("돈을 가져올 계좌를 선택하세요.", 20)), BorderLayout.NORTH);
		add(getCenter(), BorderLayout.CENTER);
		setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
	}

	private JPanel getCenter() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		try (Server get = new Server("GET", "/account/find/my", null)) {
			if (get.getResponsesCode() == 200) {
				JSONArray arr = (JSONArray) get.getResponsesBody().get("data");

				for (Object obj : arr) {
					JSONObject json = (JSONObject) obj;

					String accountId = (String) json.get("accountId");
					int money = Integer.parseInt((String) json.get("money"));
					if(!accountId.equals(account.getId()) ) {
						panel.add(Layout.coverFlowlayout(new AccountNode(accountId, money)));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JScrollPane scroll = new JScrollPane(panel);
		scroll.setPreferredSize(new Dimension(380, 600));
		scroll.setBorder(null);
		scroll.setBackground(Color.white);
		scroll.getViewport().setBackground(Color.white);
		scroll.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = Color.gray;
				this.trackColor = Color.LIGHT_GRAY;
			}
		});
		return Layout.coverFlowlayout(scroll);
	}
}
