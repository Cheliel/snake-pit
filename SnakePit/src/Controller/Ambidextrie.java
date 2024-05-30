package Controller;

import java.util.List;

import Errors.SQLConnectionException;
import model.database.History;
import model.jdbc.JdbcHistoryDao;
import model.univers.Player;

public class Ambidextrie {
	
	public static void endGame(int eatenBerries) {
		
		JdbcHistoryDao historyDB;
		try {
			
			historyDB = new JdbcHistoryDao();
			Context.endAmbidextrie(eatenBerries);
			Player localPlayer = Context.getLocalPlayer();
			
			History history = new History(localPlayer.getPseudo(), localPlayer.getEatenBerries(), (long) 1);
			
			
			new Thread(){
				public void run(){
					historyDB.addHistory(history);	          
				}
			}.start();
					
		} catch (SQLConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public static void getStat() {
		JdbcHistoryDao historyDB;
		
		try {
			historyDB = new JdbcHistoryDao();
			new Thread(){
				public void run(){
					List<History> historyList = historyDB.getHistoryByMode((long) 1);    
					Context.setAmbidextrieStats(historyList);
					Context.setFireAmbidextrieStats(true);
				}
			}.start();
		} catch (SQLConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
