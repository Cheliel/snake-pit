package Controller;

import java.util.List;

import Controller.jdbc.JdbcHistoryDao;
import GUI.Context;
import model.database.History;
import model.univers.Player;

public class Ambidextrie {
	
	public static void endGame(int eatenBerries) {
		JdbcHistoryDao historyDB = new JdbcHistoryDao();
		
		Context.endAmbidextrie(eatenBerries);
		Player localPlayer = Context.getLocalPlayer();
		
		History history = new History(localPlayer.getPseudo(), localPlayer.getEatenBerries(), (long) 1);
		
		
	    new Thread(){
	    	public void run(){
	    		historyDB.addHistory(history);	          
		    }
        }.start();
		
	}
	
	
	public static void getStat() {
		JdbcHistoryDao historyDB = new JdbcHistoryDao();
		
	    new Thread(){
	    	public void run(){
	    		List<History> historyList = historyDB.getHistoryByMode((long) 1);    
	    		Context.setAmbidextrieStats(historyList);
	    		Context.setFireAmbidextrieStats(true);
		    }
        }.start();
	

	}

}
