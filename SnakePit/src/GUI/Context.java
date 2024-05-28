package GUI;

public class Context {
	
	private static boolean startSoloGame = false;
	
	private static boolean endSoloGame = false;
	
	private static boolean goToMenu = false;


	Context(){
		
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
	
}
