package clueGame;

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

}
