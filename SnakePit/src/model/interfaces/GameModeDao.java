package model.interfaces;

public interface GameModeDao {
	Long updateGameModeName(Long id, String name);
	boolean deleteGameMode(Long gameModeId);
}

