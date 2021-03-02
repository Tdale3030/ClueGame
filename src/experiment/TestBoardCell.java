package experiment;
import java.util.*;

public class TestBoardCell {
    private int col, row;
    private boolean isRoom = false;
    private boolean isOccupied = false;
    HashSet<TestBoardCell> adjList;
    
    public TestBoardCell(int col, int row) {
        super();
        this.col = col;
        this.row = row;
        this.adjList = new HashSet<TestBoardCell>();
    }

    void addAdjacency( TestBoardCell cell ) {
    	adjList.add(cell);
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }


    public Set<TestBoardCell> getAdjList(){
		return adjList;
    }
    public boolean isRoom() {
    
    	return isRoom;
    }
    
    public void setRoom( boolean isRoom) {
    	this.isRoom=isRoom;
    	
    }
    public boolean getOccupied() {
        
    	return isOccupied;
    }
    
    public void setOccupied(boolean isOccupied) {
    	this.isOccupied=isOccupied;

    }
    
}