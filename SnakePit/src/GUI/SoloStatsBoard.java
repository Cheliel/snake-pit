package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.univers.Map;

public class SoloStatsBoard extends JPanel implements ActionListener {

	private static final long serialVersionUID = -995012187399452263L;
		
	public SoloStatsBoard() {
		this.setSize(300, 300);
		this.setBackground(Color.darkGray);
		this.setLayout(new BorderLayout());
		initInputs();
	}
	
	public void initInputs() {
		JPanel inputsPanel = new JPanel();
		inputsPanel.setLayout(new BorderLayout());

		JButton startButton = new JButton("Start");
		startButton.setFocusable(false);
		JLabel title = new JLabel("Ambidextrie");
		title.setFont(new Font("Serif", Font.PLAIN, 22));
		inputsPanel.add(title, BorderLayout.CENTER);
		inputsPanel.add(startButton, BorderLayout.SOUTH);
		
		
		startButton.addActionListener(this);
		
		
		add(inputsPanel, BorderLayout.NORTH);
		//setVisible(true);

		//inputsPanel.setLayout();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		
		switch(action) {
			case "Start":
				Context.setStartSoloGame(true);
				break;
		}
	}
	

}
