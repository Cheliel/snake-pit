package GUI.solo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.Context;
import model.univers.Map;

public class SoloEndBoard extends JPanel implements ActionListener {

		
	private static final long serialVersionUID = -6458948581476377465L;

	public SoloEndBoard() {
		this.setSize(300, 300);
		this.setBackground(Color.darkGray);
		this.setLayout(new BorderLayout());
		initLayout();
	}
	
	
	public void initLayout() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		initButtonPannel(mainPanel);
		initLabels(mainPanel);
	
		add(mainPanel, BorderLayout.CENTER);

		
	}
	
	private void initButtonPannel(JPanel mainPanel) {
		
		JPanel buttonPannel = new JPanel();
		JButton restartButton = new JButton("Restart");
		JButton menu = new JButton("Menu");
		
		restartButton.addActionListener(this);
		menu.addActionListener(this);
		
		restartButton.setFocusable(false);
		menu.setFocusable(false);
		
		buttonPannel.add(restartButton);
		buttonPannel.add(menu);
		
		mainPanel.add(buttonPannel, BorderLayout.SOUTH);
	}
	
	private void initLabels(JPanel mainPanel) {
		
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new GridLayout(4,0,0,0)); 
		
		JLabel title = new JLabel("GAME OVER");
		JLabel pseudo = new JLabel("Pseudo : " + Context.getLocalPlayer().getPseudo());
		JLabel berries = new JLabel("Berries : " + Context.getLocalPlayer().getEatenBerries());
		
		title.setFont(new Font("Serif", Font.PLAIN, 40));
		pseudo.setFont(new Font("Serif", Font.PLAIN, 16));
		berries.setFont(new Font("Serif", Font.PLAIN, 16));
		
		labelPanel.add(title);
		labelPanel.add(pseudo);
		labelPanel.add(berries);
		
		mainPanel.add(labelPanel, BorderLayout.CENTER);

	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		
		switch(action) {
			case "Restart":
				Context.setStartSoloGame(true);
				break;			
			case "Menu":
				Context.setGoToMenu(true);
				break;
		}
	}
	

}
