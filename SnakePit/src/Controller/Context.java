package Controller;

import java.util.ArrayList;
import java.util.List;

import model.database.History;
import model.univers.Player;

public class Context {
	
	/**
	 * Navigation 
	 */
	
	private static boolean startSoloGame = false;
	
	private static boolean endSoloGame = false;
	
	private static boolean goToMenu = false;
	
	
	
	/**
	 * State
	 */
	
	private static Player localPlayer = new Player();
	
	private static Player onlinePlayer = new Player();
	
	
	
	/*
	 * BDD 
	 */
	
	private static boolean fireAmbidextrieStats = false;

	private static List<History> ambidextrieStats = new ArrayList<History>();
	
	


	Context(){
		
	}
	
	public static List<History> getAmbidextrieStats() {
		return ambidextrieStats;
	}

	public static void setAmbidextrieStats(List<History> ambidextrieStats) {
		Context.ambidextrieStats = ambidextrieStats;
	}
	
	
	public static void setFireAmbidextrieStats(boolean b) {
		fireAmbidextrieStats = b;
	}
	
	public static boolean isFireAmbidextrieStats() {
		return fireAmbidextrieStats;
	}
	
	
	public static void setGoToMenu(boolean goToMenu) {
		Context.goToMenu = goToMenu;
	}
	
	public static boolean isGoToMenu() {
		return Context.goToMenu;
	}
	
	public static void setEndSoloGame(boolean endSoloGame) {
		Context.endSoloGame = endSoloGame;
	}
	
	public static boolean isSoloGameEnding() {
		return Context.endSoloGame;
	}
	
	public static void setStartSoloGame(boolean startSoloGame) {
		Context.startSoloGame = startSoloGame;
	}
	
	public static boolean isSoloGameStarting() {
		return Context.startSoloGame;
	}
	
	public static void endAmbidextrie(int eatenBerries) {
		localPlayer.setEatenBerries(eatenBerries);
		Context.setEndSoloGame(true);
	}
	
	public static void startAmbidextrie(String pseudo) {
		localPlayer.setPseudo(pseudo);
		localPlayer.setEatenBerries(0);
		Context.setStartSoloGame(true);
	}
	
	public static Player getLocalPlayer() {
		return localPlayer;
	}
	
	public static void setLocalPlayerPseudo(String pseudo) {
		localPlayer.setPseudo(pseudo);
	}
	
	public static Player getOnlinePlayer() {
		return onlinePlayer;
	}
	
}
