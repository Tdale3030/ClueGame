package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

import experiment.TestBoardCell;
//AUTHORS:Brody Clark and Tyner Dale

public class BoardCell {
	private boolean isRoom = false;
	private int row;
    private int col;
    private char initial;
	private DoorDirection doorDirection = DoorDirection.NONE;
    private boolean roomLabel;
    private boolean roomCenter;
    private boolean isOccupied = false;
    private Color color;
    private int height;
    private int width;
    
    private boolean doorway;
    private char c = ' ';
    HashSet<BoardCell> adjList;
    
    public BoardCell(int row, int col) {
      this.row = row;
      this.col = col;
      this.adjList = new HashSet<BoardCell>();
    }
    
    public DoorDirection getDoorDirection() {
    	return doorDirection;
    }
    public void setDoorDirection(DoorDirection doorDir) {
    	this.doorDirection=doorDir;
    }
    

	public boolean isDoorway() {
		//if this not a doorway it will return false otherwise it will return true
		if(doorDirection == DoorDirection.NONE) {
    		return false;
    	}
		return true;
	}

	public char getSecretPassage() {
		// TODO Auto-generated method stub
		return c;
	}
	public void setSecretPassage(char c) {
		this.c = c;
	}
	
	public void setDoorway(boolean door) {
		this.doorway=door;
		
	}

	public boolean isLabel() {
		return roomLabel;
	}
	
	public boolean isRoomCenter() {
		return roomCenter;
	}

	public void setRoomLabel(boolean roomLabel) {
		this.roomLabel = roomLabel;
	}

	public void setRoomCenter(boolean roomCenter) {
		this.roomCenter = roomCenter;
	}
	
	public void setInitial(char initial) {
		this.initial = initial;
	}
	public char getInitial() {
		return initial;
	}
    public void addAdj(BoardCell cell) {
    	adjList.add(cell);
    }
    
    void addAdjacency( BoardCell cell ) {
    	adjList.add(cell);
    }
    public HashSet<BoardCell> getAdjList(){
		return adjList;
    }
	

    public boolean isRoom() {
        
    	return isRoom;
    }

	public boolean getOccupied() {
		// TODO Auto-generated method stub
		return isOccupied;
	}
	public void setRoom( boolean isRoom) {
    	this.isRoom=isRoom;
    	
    }
	public void setOccupied(boolean isOccupied) {
    	this.isOccupied=isOccupied;

    }
	
	public void draw(Graphics graphics) {
		
		int size = 0;
																	//allows for user to move the window
		if(height > width) 
		{
			size = width;
		}
		else if(width > height) 
		{
			size = height;
		}
		
		if(initial == 'W') 											//if a walkway paint it yellow
		{
			graphics.setColor(Color.YELLOW);
			graphics.fillRect(size * col + size / 10,size * row + size / 10,size - size / 5,size - size / 5);			//creates correct size
				
		}
		else if (initial == 'Z') {									//if a black space, do nothing
		}	
		else														//else paint it black
		{
			graphics.setColor(Color.GRAY);
			graphics.fillRect(size * col,size * row,size,size);											//creates the correct size
		}


	}
	public void drawDoorway(Graphics graphics) {

		int size = 0;
		//allows for user to move the window
		if(height > width) 
		{
			size = width;
		}
		else if(width > height) 
		{
			size = height;
		}
		switch(doorDirection){

		case UP:
			graphics.setColor(Color.BLUE);
			graphics.fillRect(size * col,size * row - size / 5,size,size / 5);
			break;

		case DOWN:
			graphics.setColor(Color.BLUE);
			graphics.fillRect(size * col,size * (row + 1),size,size / 5);
			break;

		case RIGHT:
			graphics.setColor(Color.BLUE);
			graphics.fillRect(size * (col + 1),size * row,size / 5,size);
			break;

		case LEFT:
			graphics.setColor(Color.BLUE);
			graphics.fillRect(size * col - size / 5,size * row,size / 5,size);
			break;

		default:
		}
	}


	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	
	
	
	

}
