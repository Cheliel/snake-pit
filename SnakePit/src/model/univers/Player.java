package model.univers;

public class Player {
	

	private int[] adress;
	
	private String pseudo; 
	
	private int eatenBerries;
	
	public Player(){
		
	}
	
	public int[] getAdress() {
		return adress;
	}

	public void setAdress(int[] adress) {
		this.adress = adress;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public int getEatenBerries() {
		return eatenBerries;
	}

	public void setEatenBerries(int eatenBerries) {
		this.eatenBerries = eatenBerries;
	}
	

}
