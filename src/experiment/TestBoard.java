package experiment;

import java.util.HashSet;
import java.util.Set;

public class TestBoard {

	public TestBoard() {
		// TODO Auto-generated constructor stub
	}
	
	void calcTargets( TestBoardCell startCell, int pathlength) {
		
		
	}
	
	Set<TestBoardCell> getTargets(){
		int TestBoardcell;
		HashSet<TestBoardCell> targets=new HashSet();
		return  targets;
	
	}
	
	TestBoardCell getCell( int row, int col ) {
		TestBoardCell experiment= new TestBoardCell(row, col);
		return experiment;
		
	}

}
