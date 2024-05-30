package model.univers;

import java.awt.Point;

public class Cell {

	private Point position;
	
	private boolean isFood;
	
	private boolean isSnake;
	
	private boolean isSnakeHead;
	
	private long foodLifeTime; 
	
	
	/*
	 * - A Cell is the representation of anything that could be in the Map.class
	 */
	
	
	public Cell(Point point) {
		this.position = new Point(point);
		this.isFood = false;
		this.isSnake = false;
		this.isSnakeHead = false;
	}
	
	
	public Cell(Cell cell) {
		this.position = new Point(cell.getPosition().x, cell.getPosition().y);
		this.isFood = cell.isFood();
		this.isSnake = cell.isSnake();
		this.isSnakeHead = cell.isSnakeHead();
	}
	
	public Cell() {
		this.position = new Point(0,0);
		this.isFood = false;
		this.isSnake = false;
		this.isSnakeHead = false;
	}
	
	public Cell(Point position, boolean isFood, boolean isSnake, boolean isSnakeHead) {
		this.position = position;
		this.isFood = isFood;
		this.isSnake = isSnake;
		this.isSnakeHead = isSnakeHead;
	}
	
	public long getfoodLifeTime() {
		return foodLifeTime;
	}
	
	public Point getPosition() {
		return position;
	}
	
	public void setPosition(Point point) {
		this.position = new Point(point);
	}
	
	public void setX(int x) {
		this.position.x = x;
	}
	
	public void sety(int y) {
		this.position.y = y;
	}
	
	public boolean isFood() {
		return isFood;
	}
	
	public void setFood(boolean isFood) {
		if(isFood) {
			this.foodLifeTime = new java.util.Date().getTime();
		}
		
		this.isFood = isFood;
	}
	
	public boolean isSnake() {
		return isSnake;
	}
	
	public void setSnake(boolean isSnake) {
		this.isSnake = isSnake;
	}
	
	public boolean isSnakeHead() {
		return isSnakeHead;
	}
	
	public void setSnakeHead(boolean isSnakeHead) {
		this.isSnakeHead = isSnakeHead;
	}
	
	
}


