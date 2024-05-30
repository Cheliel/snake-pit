package GUI.solo;

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
import javax.swing.SwingUtilities;

import Controller.Context;
import model.database.History;
import model.univers.Map;

public class SoloStatsBoard extends JPanel implements ActionListener {

	private static final long serialVersionUID = -995012187399452263L;
	
	public JTextArea Pseudo = new JTextArea("");
	
	public IndexScoreBoard indexScoreBoard;
	
	public JTable table;
	
		
	public SoloStatsBoard() {
			
		setSize(300, 300);
		setBackground(Color.darkGray);
		setLayout(new BorderLayout());
		initInputs();
		initTable();	              
	  	
	}
	
	public void initInputs() {
		
		Pseudo.setColumns(10);
		
		JPanel inputsPanel = new JPanel();
		inputsPanel.setLayout(new BorderLayout());
		JPanel pseudoPanel = new JPanel();

		JButton startButton = new JButton("Start");
		startButton.setFocusable(false);
				
		JLabel title = new JLabel("Ambidextrie");
		JLabel p = new JLabel("Pseudo : ");
		p.setFont(new Font("Serif", Font.PLAIN, 18));
		title.setFont(new Font("Serif", Font.PLAIN, 22));
		inputsPanel.add(title, BorderLayout.NORTH);
		inputsPanel.add(startButton, BorderLayout.SOUTH);
		
		pseudoPanel.add(p);
		pseudoPanel.add(Pseudo);
		
		inputsPanel.add(pseudoPanel, BorderLayout.CENTER);
		inputsPanel.setSize(20, 20);
		
		
		startButton.addActionListener(this);
		
		
		add(inputsPanel, BorderLayout.WEST);

		
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
				indexScoreBoard.histories = Context.getAmbidextrieStats();
				indexScoreBoard.fireTableDataChanged();
	              
	        }
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		
		switch(action) {
			case "Start":
				Context.startAmbidextrie(Pseudo.getText());
				break;
		}
	}
	

}
