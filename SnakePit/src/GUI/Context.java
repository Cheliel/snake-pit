package GUI;

public class Context {
	
	private static boolean startSoloGame = false;


	Context(){
		
	}
	
	public static void setStartSoloGame(boolean startSoloGame) {
		Context.startSoloGame = startSoloGame;
	}
	
	public static boolean isSoloGameStarting() {
		return Context.startSoloGame;
	}
	
}
