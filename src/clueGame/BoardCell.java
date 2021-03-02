package clueGame;

import java.util.HashSet;
import java.util.Set;

import experiment.TestBoardCell;

public class BoardCell {

	private int row;
    private int col;
    private char initial;
    private DoorDirection doorDirection;
    private boolean roomLabel;
    private boolean roomCenter;
    private char secretPassage;
    private Set<BoardCell> adjList;
    
    public BoardCell() {
      
    }
    
    public DoorDirection getDoorDirection() {
    	return doorDirection;
    }

	public boolean isDoorway() {
		
		return false;
	}

	public char getSecretPassage() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isRoomCenter() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isLabel() {
		// TODO Auto-generated method stub
		return false;
	}
    
	

}
