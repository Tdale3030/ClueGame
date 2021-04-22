package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
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
    private boolean flag=false;
    private int size=0;
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
		else if (initial == 'Z') 
		{									//if a black space, do nothing
		}else if('G'==getSecretPassage() || 'B'==getSecretPassage() || 'L'==getSecretPassage() || 'C'==getSecretPassage()) {
			graphics.setColor(Color.DARK_GRAY);
			graphics.fillRect(size * col + size / 10,size * row + size / 10,size - size / 5,size - size / 5);			//creates correct size
				
		}
			else														//else paint it black
		{
			graphics.setColor(Color.GRAY);
			graphics.fillRect(size * col,size * row,size,size);											//creates the correct size
		}
		if(flag)
		{
			graphics.setColor(Color.CYAN);
			
			if(initial=='Z') 
			{
				return;
			}
			
			graphics.fillRect(size * col + size / 10,size * row + size / 10,size - size / 5,size - size / 5);			//creates correct size
			
			
		}

	}
	public void drawDoorway(Graphics graphics) {

		
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
			graphics.setColor(Color.BLUE);												//sets door colors to blue so player can see where to access a room
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
	
	public boolean containsClick(int mouseX,int mouseY) {
		
		int x = size * col;
		int y = size * row;
		
		Rectangle box = new Rectangle(x,y,size,size);
		
		if(box.contains(new Point(mouseX,mouseY))) {
			return true;
			
		}else {
			return false;
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

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
		
		
	


}
