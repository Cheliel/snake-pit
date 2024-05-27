package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

import model.SnakeTypes;
import model.univers.Directions;
import model.univers.Map;

public class SnakePitGUI extends JFrame implements ActionListener, KeyListener {
	
	private static final long serialVersionUID = -8788925460035705786L;
	
	private Map univers;
	private PitBoard pitBoard;


	public SnakePitGUI(Map univers) {
		this.univers = univers;
		initFrame();
		initMenu();
		initPitBoard();
		
		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) { 
				requestFocus();	
			}
		});
	}
	
	private void initPitBoard() {
		pitBoard = new PitBoard(univers);
		this.add(pitBoard);
	}
	
	public void refresh() {
		pitBoard.repaint();
	}
	
	
	private void initFrame() {
		this.setSize(600, 600);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setTitle("Snake pit");
		this.addKeyListener(this);
		this.setResizable(false);

	}
	
	public void initMenu() {
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		JMenuItem start = new JMenuItem("Reload");
		
		menu.add(start);
		menuBar.add(menu);
		start.addActionListener(this);
		
		this.setJMenuBar(menuBar);
		
	}
	
	private void restart() {
		if(univers.isGameStarted()) {
			univers.setGameStatus(false);
		}
		univers.clearPit();
		univers.setGameStatus(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		
		switch(action) {
			case "Reload":
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
