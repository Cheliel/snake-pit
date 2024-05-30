package model.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Errors.SQLConnectionException;
import model.database.History;
import model.interfaces.GameModeDao;

public class JdbcGameModeDao extends DataBaseConnexion implements GameModeDao {

	public JdbcGameModeDao() throws SQLConnectionException {
		super();
	}
	

	/*
	 * - Update the name of ONE game mode 
	 * - ID of gameMode is needed
	 */
	
	@Override
	public Long updateGameModeName(Long id, String name) {
		try {
			String sql = "UPDATE gameMode SET label=? WHERE id = ?";
			
			  PreparedStatement pstmt = this.getConnexion().prepareStatement(sql);
			  pstmt.setString(1, name);
			  pstmt.setLong(2, id);
			  pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	/*
	 * - Delete  ONE game mode 
	 * - ID of gameMode is needed
	 */
	
	@Override
	public boolean deleteGameMode(Long gameModeId) {
		
		try {
			String sql = "DELETE FROM gameMode WHERE id = ?";
			
			  PreparedStatement pstmt = this.getConnexion().prepareStatement(sql);
			  pstmt.setLong(1, gameModeId);
			  pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
