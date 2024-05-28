package launcher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import GUI.Context;
import GUI.SnakePitGUI;
import model.univers.Map;

public class App {
	
	App(){
		Map univers = new Map(50, 50);
		int ticRate = 95;
		SnakePitGUI GUI = new SnakePitGUI(univers);
		GUI.setVisible(true);
		SnakePit game = new SnakePit(univers, ticRate);
		
		Timer timer = new Timer(ticRate, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(Context.isSoloGameStarting()) {
					GUI.startAmbidextrie();
					Context.setStartSoloGame(false);
				}
				
				if(Context.isSoloGameEnding()) {
					GUI.endSoloGame();
					Context.setEndSoloGame(false);
				}
				
				if(Context.isGoToMenu()) {
					GUI.soloStatBoard();
					Context.setGoToMenu(false);
				}
				
				if(SnakePit.univers.isGameStarted()) {
					GUI.refresh();
				}
			}});
		
		timer.start();
	}

}
