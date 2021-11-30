package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicScrollBarUI;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import compoment.Account;
import compoment.Button;
import compoment.Label;
import compoment.Layout;
import compoment.RoundPanel;
import compoment.Style;
import model.Server;
import model.User;

public class Home extends Frame {
	private static Home instance = null;
	
	public static void main(String[] args) {
		Login.getInstance().setVisible(true);
	}
	
	public static Home getInstance() {
		if (instance == null)
			instance = new Home();
		return instance;
	}

	private Home() {
		super(400, 750, "홈화면");
		UIManager.put("Panel.background", Color.white);
		
		System.out.println(User.getToken());
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(getNorth(), BorderLayout.NORTH);
		panel.add(getCenter(), BorderLayout.CENTER);
		
		add(panel);
	}
	
	private Component getCenter() {
		JScrollPane scroll = new JScrollPane(getAccountList());
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
		return scroll;
	}
	
	private JPanel getAddNewAccountPanel() {
		RoundPanel panel = new RoundPanel(340, 190);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(null);
		
		JLabel text = new Label("새 계좌 추가하기", 0, 20);
		text.setPreferredSize(new Dimension(400, 50));
		text.setBounds(120, 5, 400, 50);
		
		Button btn = new Button(400, 200, "+", v -> {
			dispose();
			AccountBuild.getInstance().setVisible(true);
		});
		btn.setFont(Style.getFont(1, 30));
		btn.setContentAreaFilled(false);
		btn.setFocusPainted(false);
		btn.setBorder(null);
		btn.setBounds(0, 0, 400, 200);
		
		panel.add(btn);
		panel.add(text);
		
		return Layout.coverFlowlayout(panel);
	}
	
	private JPanel getAccountList() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(getAddNewAccountPanel());
		
		try (Server get = new Server("GET", "/account/find/my", null)){
			if(get.getResponsesCode() == 200) {
				JSONArray arr = (JSONArray) get.getResponsesBody().get("data");
				
				for(Object obj : arr) {
					JSONObject json = (JSONObject) obj;
					
					String accountId = (String) json.get("accountId");
					int money = Integer.parseInt((String) json.get("money"));
					panel.add(Layout.coverFlowlayout(new Account(accountId, money)));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Layout.coverFlowlayout(panel);
	}

	private JPanel getNorth() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		panel.add(new Label("<html>안녕하세요.<br>" + User.getName() + "님</html>", 35));

		panel.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 20));
		return panel;
	}

}
