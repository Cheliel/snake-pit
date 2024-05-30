package GUI.multiplayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

import Controller.Context;
import GUI.solo.IndexScoreBoard;
import model.database.History;
import model.univers.Map;
import model.univers.Player;

public class MultiPlayerRoom extends JPanel implements ActionListener {

	private static final long serialVersionUID = -995012187399452263L;
	
	public JTextArea Pseudo = new JTextArea("");
	
	public IndexScoreBoard indexScoreBoard;
	
	public JTable table;
	
	JPanel statPanel;
	
	JPanel joiningPanel;
	
	JPanel hostingPanel;
	
	Player player1 = Context.getLocalPlayer();
	
	Player player2 = Context.getOnlinePlayer();
	
		
	public MultiPlayerRoom() {
		
		setSize(300, 300);
		setBackground(Color.darkGray);
		setLayout(new BorderLayout());
		initStatView();
		initTable();
	}
	
	public void initStatView() {
		
		clearFrame();
		
		Pseudo.setColumns(10);
		
		JPanel buttonPan = new JPanel();
		JPanel pseudoPan = new JPanel();

		statPanel = new JPanel();
		statPanel.setLayout(new BorderLayout());

		JButton creatButton = new JButton("Create");
		JButton joinButton = new JButton("Join");
				
		JLabel title = new JLabel("Multiplayer");
		JLabel p = new JLabel("Pseudo : ");
		
		title.setFont(new Font("Serif", Font.PLAIN, 22));
		p.setFont(new Font("Serif", Font.PLAIN, 18));
		
		buttonPan.add(creatButton);
		buttonPan.add(joinButton);

		pseudoPan.add(p);
		pseudoPan.add(Pseudo);
		
		statPanel.add(title, BorderLayout.NORTH);
		statPanel.add(buttonPan, BorderLayout.SOUTH);
		statPanel.add(pseudoPan, BorderLayout.WEST);
		
		
		creatButton.addActionListener(this);
		joinButton.addActionListener(this);
		creatButton.setFocusable(false);
		joinButton.setFocusable(false);


		
		add(statPanel, BorderLayout.WEST);
		
		revalidate();
		setVisible(true);

		
	}
	
	private void initTable() {
		indexScoreBoard = new IndexScoreBoard(new ArrayList<History>());
		table = new JTable(indexScoreBoard);
		table.setFocusable(false);
		this.add(table, BorderLayout.CENTER);
		
	}
	
	public void refreshStatTable() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				//indexScoreBoard.histories = Context.getAmbidextrieStats();
				indexScoreBoard.fireTableDataChanged();		              
	        }
		});
	}
	
	private void clearFrame() {
		
		for( Component c : getComponents()) {
			c.setVisible(false);
		}
	}
	
	public void updatePlayer2() {
		
	}
	
	private JPanel getPlayersPan() {
		
		JPanel playersInfosPan = new JPanel();
		JPanel player1Pan = new JPanel();
		JPanel player2Pan = new JPanel();
		playersInfosPan.setLayout(new BorderLayout());
		
		JLabel labelPlayer1 = new JLabel("Player 1 : ");
		JLabel labelPlayer2 = new JLabel("Player 2 : ");
		JLabel player1 = new JLabel(this.player1.getPseudo());
		JLabel player2 = new JLabel(this.player2.getPseudo());
		setFonts(20,labelPlayer1, labelPlayer2, player1, player2);
		
		player1Pan.add(labelPlayer1);
		player1Pan.add(player1);
		player2Pan.add(labelPlayer2);
		player2Pan.add(player2);
		playersInfosPan.add(player1Pan, BorderLayout.NORTH);
		playersInfosPan.add(player2Pan, BorderLayout.SOUTH);
		
		return playersInfosPan;
		
		
	}
	
	
	private void initHostRoom() {
		clearFrame();
		JPanel buttonPan = new JPanel();


		hostingPanel = new JPanel();
		hostingPanel.setLayout(new BorderLayout());
		

		JButton creatButton = new JButton("kik");
		JButton joinButton = new JButton("start");
		creatButton.setFocusable(false);
		joinButton.setFocusable(false);
		
		buttonPan.add(creatButton);
		buttonPan.add(joinButton);
		
		
		JLabel title = new JLabel("Multiplayer");
		setFonts(30, title);

		

		
		hostingPanel.add(title, BorderLayout.NORTH);
		hostingPanel.add(buttonPan, BorderLayout.SOUTH);
		hostingPanel.add(getPlayersPan(), BorderLayout.CENTER);
		
		
		creatButton.addActionListener(this);
		joinButton.addActionListener(this);
		
		
		add(hostingPanel, BorderLayout.NORTH);
		repaint();
		revalidate();
		
		
	}
	
	private void initJoiningRoom() {
		
	}
	
	
	private void setFonts(int fontSize, JLabel...jLabels ) {
		for(JLabel jl : jLabels) {
			jl.setFont(new Font("Serif", Font.PLAIN, fontSize));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		
		System.out.println(action);
		
		switch(action) {
			case "Create":
				Context.setLocalPlayerPseudo(Pseudo.getText());
				initHostRoom();
				break;
			case "join":
				initJoiningRoom();
				break;
		}
	}
	

}
