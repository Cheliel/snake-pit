package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import model.SnakeTypes;
import model.univers.Cell;
import model.univers.Map;

public class PitBoard extends JPanel {
	
	private static final long serialVersionUID = 5013562630737965394L;
	private Map univers;
	
	public PitBoard(Map univers) {
		this.univers = univers;
	}
	
   @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCell(g);
    }
   
   private void drawCell(Graphics g) {
	   
	   //g.drawLine(0, 10 * 10, 50 * 11, 10 * 10);
	   	   
	   g.drawLine(0, 51 * 10, 51 * 10, 51 * 10);
	   g.drawLine(51 * 10, 0, 51 * 10, 51 * 10);
	   
    	List<Cell> blueSnake = univers.getSnake(SnakeTypes.blueSnake);
    	List<Cell> redSnake = univers.getSnake(SnakeTypes.redSnake);
    	
    	paintSnake(blueSnake, Color.BLUE, g);
    	paintSnake(redSnake, Color.RED, g);
    	paintFood(g);

   }
   
   private void paintSnake(List<Cell> snake, Color headColor, Graphics g) {
   	for(Cell bodyPart : snake) {
		if(bodyPart.isSnakeHead()) {
			paint(bodyPart.getPosition(), g, headColor);	
		}else if(bodyPart.isSnake()){
			paint(bodyPart.getPosition(), g, Color.BLACK);
		}	
	}
   }
   
   private void paintFood(Graphics g) {
   	for(Cell berry : univers.getFood()) {
		paint(berry.getPosition(), g, Color.PINK);
	}
   }
   
   private void paint(Point point, Graphics g, Color c) {
       g.setColor(c);
       g.fillRect(point.x * 10, point.y *10,10,10);
   }
   

}
