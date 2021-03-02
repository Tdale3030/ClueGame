package experiment;
import java.util.*;

public class TestBoardCell {
    private int col, row;
    private boolean isRoom;
    private boolean getOccupied;
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
    boolean isRoom() {
    
    	return true;
    }
    
    public void setRoom( boolean isRoom) {
    	isRoom=isRoom();
    	
    }
    boolean isOccupied() {
        
    	return true;
    }
    
    public void setOccupied(boolean isOccupied) {
    	isOccupied=isOccupied();

    }

    
    
    
    
}