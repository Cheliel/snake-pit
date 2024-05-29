package launcher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import GUI.SnakePitGUI;
import model.univers.Map;

public class SnakePit {
	
	public static int ticRate = 95;
	public volatile static Map univers;

	SnakePit(Map univers, int ticRate){
		SnakePit.ticRate = ticRate;
		SnakePit.univers = univers;
		start();
	}
	
	/*
	 * This function launch the game
	 */
	
	
	public static void start() {
		
		Timer timer = new Timer(ticRate, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(SnakePit.univers.isGameStarted()) {
					SnakePit.univers.newGeneration();
				}
			}});
		
		timer.start();

	}

}
