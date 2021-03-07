package clueGame;

import java.util.HashSet;
import java.util.Set;

import experiment.TestBoardCell;
//AUTHORS:Brody Clark and Tyner Dale

public class BoardCell {

	private int row;
    private int col;
    private char initial;
	private DoorDirection doorDirection = DoorDirection.NONE;
    private boolean roomLabel;
    private boolean roomCenter;
    private char secretPassage;
    private Set<BoardCell> adjList;
    private boolean doorway;
    private char c;
    
    public BoardCell(int row, int col) {
      this.row = row;
      this.col = col;
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
	//fin

}
