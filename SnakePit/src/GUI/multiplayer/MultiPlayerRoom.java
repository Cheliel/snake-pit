package GUI.multiplayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;

import GUI.Context;
import GUI.solo.IndexScoreBoard;
import model.database.History;
import model.univers.Map;

public class MultiPlayerRoom extends JPanel implements ActionListener {

	private static final long serialVersionUID = -995012187399452263L;
	
	public JTextArea Pseudo = new JTextArea("Enter a Pseudo");
	
	public IndexScoreBoard indexScoreBoard;
	
	public JTable table;
	
	JPanel statPanel;
	
	JPanel joiningPanel;
	
	JPanel hostingPanel;
	
		
	public MultiPlayerRoom() {
		this.setSize(300, 300);
		this.setBackground(Color.darkGray);
		this.setLayout(new BorderLayout());
		initStatView();
		initTable();
	}
	
	public void initStatView() {
		
		JPanel buttonPan = new JPanel();

		statPanel = new JPanel();
		statPanel.setLayout(new BorderLayout());

		JButton creatButton = new JButton("Creat");
		creatButton.setFocusable(false);
		
		JButton joinButton = new JButton("Join");
		joinButton.setFocusable(false);
				
		JLabel title = new JLabel("Multiplayer");
		title.setFont(new Font("Serif", Font.PLAIN, 22));
		JLabel p = new JLabel("Pseudo : ");
		p.setFont(new Font("Serif", Font.PLAIN, 18));
		
		buttonPan.add(creatButton);
		buttonPan.add(joinButton);

		
		
		statPanel.add(title, BorderLayout.NORTH);
		statPanel.add(buttonPan, BorderLayout.SOUTH);
		statPanel.add(p, BorderLayout.WEST);
		statPanel.add(Pseudo, BorderLayout.CENTER);
		
		
		creatButton.addActionListener(this);
		joinButton.addActionListener(this);
		
		
		add(statPanel, BorderLayout.NORTH);

		
	}
	
	private void initTable() {
		indexScoreBoard = new IndexScoreBoard(new ArrayList<History>());
		table = new JTable(indexScoreBoard);
		table.setFocusable(false);
		this.add(table, BorderLayout.CENTER);
		
	}
	
	public void refreshStatTable() {
		//indexScoreBoard.histories = Context.getAmbidextrieStats();
		indexScoreBoard.fireTableDataChanged();
	}
	
	private void clearFrame() {
		try {
			getRootPane().removeAll();
		}catch(NullPointerException e) {}
	}
	
	
	private void initHostRoom() {
		
	}
	
	private void initJoiningRoom() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		
		switch(action) {
			case "Create":
				initHostRoom();
				//Context.startAmbidextrie(Pseudo.getText());
				break;
			case "join":
				initJoiningRoom();
				//Context.startAmbidextrie(Pseudo.getText());
				break;
		}
	}
	

}
