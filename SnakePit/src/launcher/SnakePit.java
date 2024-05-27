package launcher;

import GUI.SnakePitGUI;
import model.univers.Map;

public class SnakePit {
	
	public static Long ticRate = (long) 95;
	
	public static void main(String[] args) {
		
		startGame();
	}
	
	/*
	 * This function start the game
	 */
	
	public static void startGame() {
		
		Map univers = new Map(50, 50);
		SnakePitGUI GUI = new SnakePitGUI(univers);
		
		GUI.setVisible(true);
		univers.setGameStatus(true);
		

		
		while(true) {
			Long tic = new java.util.Date().getTime();
			while(univers.isGameStarted()) {
				if((new java.util.Date().getTime() - tic) > ticRate) {
					univers.newGeneration();
					GUI.refresh();
					tic = new java.util.Date().getTime();
				}
				
			}			
		}	
	}
}
