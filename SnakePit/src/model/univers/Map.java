package model.univers;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import GUI.Context;
import model.Snake;
import model.SnakeTypes;

public class Map {
	
	Cell[][] pit;
	
	int pitSize;
	
	Snake redSnake;
	
	Snake blueSnake;
	
	List<Cell> food;
	
	volatile boolean gameStarted;
	
	private static int [] initialRedSnake = {4, 5, 6, 7, 8};
	
	private static int [] initialBlueSnake = {6, 7, 8, 9, 10};
	
	Long lastFoodIncrement;
	
	
	private static boolean allowMoveBackyard = true;
	

	
	public Map(int xSize, int Ysize) {
		pitSize = xSize;
		initSnakes(xSize);
		manageFood();
	}
	
	private void initSnakes(int pitSize) {
		Cell redHead = new Cell(new Point(pitSize - 6, 9), false, false, true);
		Cell blueHead = new Cell(new Point(6, 11), false, false, true);
		this.redSnake = new Snake(redHead, getBody(SnakeTypes.redSnake), pitSize); 
		this.blueSnake = new Snake(blueHead, getBody(SnakeTypes.blueSnake), pitSize); 
	}
	
	
	
	public void newGeneration() {
		this.blueSnake.move();
		this.redSnake.move();
		manageFood();
		applyRules();
		
	}
	
	public void applyRules() {
		isSnakeEating();
		isSnakeDying();
	}
	
	public void isSnakeEating() {
		for(int i = 0; i < food.size(); i++) {
			if(food.get(i).getPosition().equals(blueSnake.getHead().getPosition())) {
				blueSnake.eat();
				food.remove(i);
			} else if(food.get(i).getPosition().equals(redSnake.getHead().getPosition())) {
				redSnake.eat();
				food.remove(i);
			}
		}
	}
	
	public void addFood() {
		
		Random r = new Random();
		Point point = new Point();
				
		if(food == null) {
			food = new ArrayList<Cell>();
		}
		
		do {
			point.x = r.nextInt(pitSize) + 1 ;
			point.y =r.nextInt(pitSize) + 1;
		} while (isCellOccuped(point));
		
		Cell berry = new Cell(point);
		berry.setFood(true);
		food.add(berry);
	}
	
	public boolean isCellOccuped(Point point) {
		List<Cell> blueSnake = getSnake(SnakeTypes.blueSnake);
		List<Cell> redSnake = getSnake(SnakeTypes.redSnake);
		
		if(!isSnakePart(point, redSnake) || !isSnakePart(point, blueSnake) || !isFood(point)) {
			return false;
		}
		return true;
	}
	
	public void	isSnakeDying() {
		if(getCollisions()){
			setGameStatus(false);
			Context.setEndSoloGame(true);
		}

	}
	
	public boolean getCollisions() {
		List<Cell> blueSnake = getSnake(SnakeTypes.blueSnake);
		List<Cell> redSnake = getSnake(SnakeTypes.redSnake);
		
		if(isSnakePart(this.blueSnake.getHead().getPosition(), redSnake)) {
			return true;
		} else if(isSnakePart(this.redSnake.getHead().getPosition(), blueSnake)) {
			return true;
		}
		return false;
		
	}
	
	public boolean isSnakePart(Point point, List<Cell> snake) {
		for(Cell bodyPart : snake) {
			if(bodyPart.getPosition().equals(point)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isFood(Point point) {
		for(Cell berry : food) {
			if(berry.getPosition().equals(point)) {
				return true;
			}
		}
		return false;
		
	}

	public void manageFood() {
		if(lastFoodIncrement == null) {
			lastFoodIncrement = new java.util.Date().getTime();
			addFood();
		}
		
		if((new java.util.Date().getTime() - lastFoodIncrement) > 3000) {
			lastFoodIncrement = new java.util.Date().getTime();
			addFood();
		}
		spoilFood();
	}
	
	private List<Cell> getBody(SnakeTypes type) {
		List<Cell> body = new ArrayList<Cell>();
		for(int i = 0; i < initialBlueSnake.length; i++) {
			Point p = new Point(type == SnakeTypes.redSnake ? pitSize - 6 : 6, 
								type == SnakeTypes.redSnake ? initialRedSnake[i] : initialBlueSnake[i] );
			Cell c = new Cell(p, false, true, false);
			body.add(c);
		}
		return body;
	}
	

	public void spoilFood() {	
	  for(int i = 0; i < food.size(); i++) { 
		  if(new java.util.Date().getTime() - food.get(i).getfoodLifeTime() > 8000) {
			  food.remove(i);
		  } 
	  }	
	}
	
	public void clearPit() {
		initSnakes(pitSize);
		manageFood();
	}
	
	public int getMapSize() {
		return pit.length;
	}
	
	public Cell[][] getPit(){
		return this.pit;
	}
	

	
	
	public void displaySnake(Snake s) {
		System.out.println("Head => x : " + s.getHead().getPosition().x + " y : " + s.getHead().getPosition().y);
		System.out.println("Body => ");
		for(Cell bodyPart : s.getBody()) {
			System.out.println("X : " + bodyPart.getPosition().x + " | y : " + bodyPart.getPosition().y);
		}
		
	}
	
	public List<Cell> getSnake(SnakeTypes type){
		List<Cell> snake = new ArrayList<Cell>();
		snake.add(type == SnakeTypes.redSnake ? redSnake.getHead() : blueSnake.getHead());
		List<Cell> body = type == SnakeTypes.redSnake ? redSnake.getBody() : blueSnake.getBody();
		snake.addAll(body);
		return snake;
	}
	
	
	public List<Cell> getFood(){
		return food;
	}
	
	public boolean isGameStarted() {
		return gameStarted;
	}
	
	public void setGameStatus(boolean status) {
		gameStarted = status;
	}
	
	public void setSnakeDirection(SnakeTypes type, Directions direction) {
		if(type == SnakeTypes.redSnake) {
			if(!redSnake.isInvalidMovement(direction) || allowMoveBackyard) {
				redSnake.setDirection(direction);				
			}
		}else if(!blueSnake.isInvalidMovement(direction) || allowMoveBackyard){
			blueSnake.setDirection(direction);
		}
	}

	
}
