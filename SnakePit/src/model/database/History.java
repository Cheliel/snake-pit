package model.database;

public class History implements Comparable<History> {

	private Long id;
	private String pseudo;
	private int berries;
	private Long gameModeId;
	
	
	public History(String pseudo, int berries, Long gameModeId) {
		this.pseudo = pseudo;
		this.berries = berries;
		this.gameModeId = gameModeId;
	}
	
	public History() {
		
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public int getBerries() {
		return berries;
	}
	public void setBerries(int berries) {
		this.berries = berries;
	}
	public Long getGameModeId() {
		return gameModeId;
	}
	public void setGameModeId(Long gameModeId) {
		this.gameModeId = gameModeId;
	}

	@Override
	public int compareTo(History o) {
        return Integer.compare(o.getBerries(), getBerries());	
	}
}
