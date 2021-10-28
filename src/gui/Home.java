package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.plaf.ScrollBarUI;
import javax.swing.plaf.ScrollPaneUI;
import javax.swing.plaf.basic.BasicScrollBarUI;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import model.Server;
import model.User;

public class Home extends Frame {
	private static Home instance = new Home();
	
	public static Home getInstance() {
		if (instance == null)
			instance = new Home();
		return instance;
	}

	private Home() {
		super(500, 850, "홈화면");
		Macro.changeJPanelColor(Color.white);

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(getNorth(), BorderLayout.NORTH);
		panel.add(getCenter(), BorderLayout.CENTER);
		
		add(panel);
		setVisible(true);
	}
	
	private Component getCenter() {
		JScrollPane scroll = new JScrollPane(getAccountList());
		scroll.setBorder(null);
		scroll.setBackground(MAIN_DARKBLUE);
		scroll.getViewport().setBackground(MAIN_DARKBLUE);
		scroll.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = Color.white;
				this.trackColor = MAIN_DARKBLUE;
			}
		});
		return scroll;
	}
	
	private JPanel getAccountList() {
		JPanel panel = new JPanel(new FlowLayout());
		
		try (Server get = new Server("GET", "/account/find/my", null)){
			if(get.getResponsesCode() == 200) {
				JSONArray arr = (JSONArray) get.getResponsesBody().get("data");
				
				for(Object obj : arr) {
					JSONObject json = (JSONObject) obj;
					
					String accountId = (String) json.get("accountId");
					int money = Integer.parseInt((String) json.get("money"));
					panel.add(new Account(accountId, money));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		//panel.add(new Account("계좌 추가하기", Frame.MAIN_YELLOW));
		//panel.add(new Account());
		
		return panel;
	}

	private JPanel getNorth() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		panel.add(Macro.getLabel("<html>안녕하세요.<br>" + User.getName() + "님</html>", 35));

		panel.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 20));
		return panel;
	}

}
