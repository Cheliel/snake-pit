package model.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Errors.SQLConnectionException;
import model.database.History;
import model.interfaces.historyDAO;

public class JdbcHistoryDao extends DataBaseConnexion implements historyDAO {
	
	public JdbcHistoryDao() throws SQLConnectionException {
		
		super();
	}

	@Override
	public Long addHistory(History h) {
		try {
			String sql = "INSERT INTO history(pseudo, berries, id_gameMode) values(?, ?, ?)";
			
			  PreparedStatement pstmt = this.getConnexion().prepareStatement(sql);
			  pstmt.setString(1, h.getPseudo());
			  pstmt.setLong(2, h.getBerries());
			  pstmt.setLong(3, h.getGameModeId());

			  pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<History> getHistoryByMode(Long gameModeId) {
		try {
			String sql = "select * from history where id_gameMode = " + gameModeId + " AND berries > 0";
			// ORDER BY berries DESC
			ResultSet result = this.getSTMT().executeQuery(sql);
			
			List<History> historyList = new ArrayList<History>();
			
			while(result.next()) {
				ResultSetMetaData rsmd = result.getMetaData();
				History history = new History();
				for (int i = 1; i <= rsmd.getColumnCount(); i++)
				{
					String key = rsmd.getColumnName(i);
					
					switch(rsmd.getColumnTypeName(i)) {
						case "INT":
							setHistoryProperty(history, key, null, result.getInt(i));
							break;
						case "VARCHAR":
							setHistoryProperty(history, key, result.getString(i), 0);
							break;
					}
					
				}
				
				historyList.add(history);						
				
			}
			Collections.sort(historyList);			
			return historyList;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	private void setHistoryProperty(History history, String key, String sValue, int iValue) {
		switch(key) {
		case "pseudo":
			history.setPseudo(sValue);
			break;
		case "berries":
			history.setBerries(iValue);
			break;
	}
	}

}
