package model;

import java.awt.Point;
import java.util.List;
import java.util.Random;

import model.univers.Cell;
import model.univers.Directions;

public class Snake {
	

	private Cell head;
	
	private List<Cell> body;

	private int pitSize;
	
	private Directions direction;
	
	
	public Snake() {
		
	}
	
	public Snake(Cell head, List<Cell> body, int pitSize) {
		super();
		this.head = head;
		this.body = body;
		this.pitSize = pitSize;
		this.direction = getRandomDirection();
	}
	
	public void eat() {		
		Cell newTail = new Cell(body.get(body.size() - 1));
		body.add(newTail);
	}
	
	
	public Directions getRandomDirection() {
		Random r = new Random();
		Directions result;
		 
		switch(r.nextInt(4) + 1) {
		case 1:
			result = Directions.NORTH;
			break;
		case 2:
			result = Directions.SOUTH;
			break;
		case 3:
			result = Directions.EST;
			break;
		case 4:
			result = Directions.WEST;
			break;
		default:
			result = Directions.EST;
		}
		return result;

	}
	
	
	private void moveHead(Point newPosition) {
		this.head.setX(newPosition.x);
		this.head.sety(newPosition.y);
	}
	
	//@depreciated
	public void grow(Point newHead) {
		this.bodyFollowHead(new Point(this.head.getPosition().x, this.head.getPosition().y));
		moveHead(newHead);
	}
	
	
	public void move() {
		Point newPosition;
		switch(direction) {
		case NORTH:
			newPosition = new Point(head.getPosition().x, head.getPosition().y - 1);
			break;
		case WEST:
			newPosition = new Point(head.getPosition().x - 1, head.getPosition().y);
			break;
		case EST:
			newPosition = new Point(head.getPosition().x + 1, head.getPosition().y);
			break;
		case SOUTH:
			newPosition = new Point(head.getPosition().x, head.getPosition().y + 1);
			break;
		default:
			newPosition = head.getPosition();
		}
		newPosition = new Point(snakeOutOfBound(newPosition));
	
		this.bodyFollowHead(new Point(this.head.getPosition().x, this.head.getPosition().y));
		moveHead(newPosition);
		
	}
	
	public boolean isInvalidMovement(Directions direction) {
		
		boolean result = false;
		
		switch(this.direction) {
		case NORTH:
			if(direction == Directions.SOUTH) {
				result = true;
			}
			break;
		case WEST:
			if(direction == Directions.EST) {
				result = true;
			}
			break;
		case EST:
			if(direction == Directions.WEST) {
				result = true;
			}
			break;
		case SOUTH:
			if(direction == Directions.NORTH) {
				result = true;
			}
			break;
		default:
			result = false;
		}
		return result;
	}
	
	public Point snakeOutOfBound(Point newHead){
		Point result = new Point();
		if(newHead.x > pitSize) {
			result.x = 0;
			result.y = newHead.y;
		} else if(newHead.x < 0) {
			result.x = pitSize;
			result.y = newHead.y;
		} else if(newHead.y > pitSize) {
			result.y = 0;
			result.x = newHead.x;
		} else if(newHead.y < 0) {
			result.y = pitSize;
			result.x = newHead.x;
		} else {
			result = new Point(newHead);
		}
		return result;
	}
	
	private void bodyFollowHead(Point oldHead) {
		for(int i = 0; i < body.size(); i++) {
			if(i == body.size() - 1) {
				this.body.get(i).setPosition(oldHead);		
			}
			try{
				this.body.get(i).setPosition(this.body.get(i + 1).getPosition());			
			}catch(IndexOutOfBoundsException e) {
				
			}	
		}
	}
	
	
	public boolean isPartOfMe(Point point) {
		if(head.getPosition().equals(point)) {
			return true;
		} else if(body.stream().anyMatch(b -> b.getPosition().equals(point))) {
			return true;
		}
		return false;
	}
	
	public int getPitSize() {
		return pitSize;
	}
	
	public void setPitSize(int pitSize) {
		this.pitSize = pitSize;
	}
	
	public Cell getHead() {
		return head;
	}
	
	public void setHead(Cell head) {
		this.head = head;
	}
	
	public List<Cell> getBody() {
		return body;
	}
	public Directions getDirection() {
		return direction;
	}
	
	public void setDirection(Directions direction) {
		this.direction = direction;
	}
}


