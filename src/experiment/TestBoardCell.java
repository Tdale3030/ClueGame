package experiment;
import java.util.*;

public class TestBoardCell {
    private int col, row;
    private boolean isRoom;
    private boolean getOccupied;
    
    public TestBoardCell(int col, int row) {
        super();
        this.col = col;
        this.row = row;
    }

    void addAdjacency( TestBoardCell cell ) {

    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }


    Set<TestBoardCell> getAdjList(){

		int TestBoardcell;
		HashSet<TestBoardCell> test=new HashSet();
		return  test;

    }
    boolean isRoom() {ß
    
    	return true;
    }
    
    void setRoom( boolean isRoom) {
    	isRoom=isRoom();
    	
    }
    boolean isOccupied() {
        
    	return true;
    }
    
    void setOccupied(boolean isOccupied) {
    	isOccupied=isOccupied();


    }

    
    
    
    
}