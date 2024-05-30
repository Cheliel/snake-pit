
package GUI;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.function.Function;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import Controller.Ambidextrie;
import GUI.multiplayer.MultiPlayerRoom;
import GUI.solo.SoloEndBoard;
import GUI.solo.SoloStatsBoard;
import model.univers.Directions;
import model.univers.Map;
import model.univers.SnakeTypes;

public class SnakePitGUI extends JFrame implements ActionListener, KeyListener {
	
	private static final long serialVersionUID = -8788925460035705786L;
	
	private Map univers;
	private PitBoard pitBoard;
	private JPanel withBoard;
	private SoloStatsBoard soloStatsBoard;
	private SoloEndBoard soloEndBoard;
	private MultiPlayerRoom multiPlayerRoom;
	private Timer timer = new Timer(200, this);


	public SnakePitGUI(Map univers) {
		this.univers = univers;
		withBoard = new JPanel();

		initFrame();
		initMenu();
		
		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) { 
				requestFocus();	
			}
		});
	}
	
	
	public void startAmbidextrie() {
		clearFrame();
		initPitBoard();
		//univers.setGameStatus(true);		
	}
	
	
	public void endSoloGame() {
		clearFrame();
		initSoloEndBoard();
	}
	
	private void initPitBoard() {
		 SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				getContentPane().remove(soloStatsBoard);
				univers.reload();
				univers.setGameStatus(true);
				pitBoard = new PitBoard(univers);
				add(pitBoard);
				revalidate();
				setVisible(true);
			}
		 });
	}
	
	
	public void soloStatBoard() {
		clearFrame();
		initSoloStatBoard();
	}
	
	private void initSoloStatBoard() {
	 SwingUtilities.invokeLater(new Runnable() {
		@Override
		public void run() {
			
			clearFrame();
			
			soloStatsBoard = new SoloStatsBoard();
			Ambidextrie.getStat();
			add(soloStatsBoard);
			revalidate();
			setVisible(true);
		}
	 });
	}
	
	
	private void initMultiplayerRoom() {
	 SwingUtilities.invokeLater(new Runnable() {
		@Override
		public void run() {
			
			clearFrame();
			
			multiPlayerRoom = new MultiPlayerRoom();
			//Ambidextrie.getStat();
			add(multiPlayerRoom);
			revalidate();
			setVisible(true);
		}
	 });
	}
	
	private void initSoloEndBoard() {
		 SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {	
				clearFrame();
				soloEndBoard = new SoloEndBoard();
				getContentPane().remove(pitBoard);
				add(soloEndBoard);
				revalidate();
				setVisible(true);

			}
		 });
	}
	
	
	private void clearFrame() {
		try {
			getContentPane().remove(pitBoard);
		}catch(NullPointerException e) {}
		
		try {
			getContentPane().remove(soloStatsBoard);
		}catch(NullPointerException e) {}
		
		
		try {
			getContentPane().remove(soloEndBoard);
		}catch(NullPointerException e) {}
		
		
		try {
			getContentPane().remove(multiPlayerRoom);
		}catch(NullPointerException e) {}
		
	}
	
	public void refresh() {
		pitBoard.repaint();
	}
	
	
	private void initFrame() {
		this.setSize(600, 600);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setTitle("Snake pit");
		this.addKeyListener(this);
		this.setFocusable(true);
		this.setResizable(false);

	}
	
	public void initMenu() {
		
		JMenuBar menuBar = new JMenuBar();
		JMenu Game = new JMenu("Game");
		JMenu Mode = new JMenu("Mode");
		JMenuItem start = new JMenuItem("Reload");
		JMenuItem Ambidextrie = new JMenuItem("Ambidextrie");
		JMenuItem ami = new JMenuItem("Multiplayer");
		JMenuItem Versus = new JMenuItem("Versus");
		JMenuItem Pause= new JMenuItem("Pause");
		
		
		Game.add(start);
		Game.add(Pause);
		Mode.add(Ambidextrie);
		Mode.add(ami);
		Mode.add(Versus);
		menuBar.add(Game);
		menuBar.add(Mode);

		
		/*
		 * SET Actions listener 
		 */
		
		start.addActionListener(this);
		Ambidextrie.addActionListener(this);
		Versus.addActionListener(this);
		Pause.addActionListener(this);
		ami.addActionListener(this);
		this.setJMenuBar(menuBar);
		
	}
	
	
	public void fireAmbidextrieStats() {
		soloStatsBoard.refreshStatTable();
	}
	
	
	//move to univers 
	private void restart() {
		if(univers.isGameStarted()) {
			univers.setGameStatus(false);
		}
		univers.clearPit();
		univers.setGameStatus(true);

	}
	
	
	private void pause() {
		if(univers.isGameStarted()) {
			univers.setGameStatus(false);
		} else {
			univers.setGameStatus(true);
		}	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		
		System.out.println(action);
		
		switch(action) {
			case "Pause":
				pause();
				break;
			case "Reload":
				restart();
				break;
			case "Multiplayer":
				initMultiplayerRoom();
				break;
			case "Ambidextrie":
				initSoloStatBoard();
				break;
			case "Versus":
				restart();
				break;
			default: 
				System.out.println("action cannot be performed : SnakePitGUI.actionPerformed");
		}		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
        switch(e.getKeyChar()) {
        
        	case 'z':
        		univers.setSnakeDirection(SnakeTypes.blueSnake, Directions.NORTH);
        		break;
        	case 'q':
        		univers.setSnakeDirection(SnakeTypes.blueSnake, Directions.WEST);
        		break;
        	case 'd':
        		univers.setSnakeDirection(SnakeTypes.blueSnake, Directions.EST);
        		break;
        	case 's':
        		univers.setSnakeDirection(SnakeTypes.blueSnake, Directions.SOUTH);
        		break;
        		
        		
        		
        	case 'i':
        		univers.setSnakeDirection(SnakeTypes.redSnake, Directions.NORTH);
        		break;
        	case 'j':
        		univers.setSnakeDirection(SnakeTypes.redSnake, Directions.WEST);
        		break;
        	case 'l':
        		univers.setSnakeDirection(SnakeTypes.redSnake, Directions.EST);
        		break;
        	case 'k':
        		univers.setSnakeDirection(SnakeTypes.redSnake, Directions.SOUTH);
        		break;
        	default:
        }
	}
	
	

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
