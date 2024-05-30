package model.interfaces;

import java.util.List;

import model.database.History;

public interface historyDAO {
	Long addHistory(History h);
	List<History> getHistoryByMode(Long gameModeId);
}
