package experiment;

import java.util.HashSet;
import java.util.Set;

public class TestBoard {

	public TestBoard() {
		// TODO Auto-generated constructor stub
	}
	
	public void calcTargets( TestBoardCell startCell, int pathlength) {
	
	}
	
	public Set<TestBoardCell> getTargets(){
		int TestBoardcell;
		HashSet<TestBoardCell> targets=new HashSet();
		return  targets;
	
	}
	
	public TestBoardCell getCell( int row, int col ) {
		TestBoardCell experiment = new TestBoardCell(row, col);
		return experiment;
		
	}

}
