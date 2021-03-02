package experiment;

import java.util.HashSet;
import java.util.Set;

public class TestBoard {
	private TestBoardCell[][] grid;
	private Set<TestBoardCell> targets;
	private Set<TestBoardCell> visited;
	public final static int COLS = 4;
	public final static int ROWS = 4;
	
	
	public TestBoard() {
		this.grid=new TestBoardCell[ROWS][COLS];
		for(int i=0;i<ROWS-1;i++) {
			for(int j=0;j<COLS-1;j++) {
				grid[i][j] = new TestBoardCell(i, j);
			}
		}
		allAdj();
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
	
	public void allAdj() {
		for(int i=0;i<ROWS-1;i++) {
			for(int j=0;j<COLS-1;j++) {
				if ((i-1) >= 0) {
					grid[i][j].addAdjacency(grid[i-1][j]);
				    
				}
				if ((i+1) <= ROWS-1) {
					grid[i][j].addAdjacency(grid[i+1][j]);

				}
				if ((j-1) >= 0) {
					grid[i][j].addAdjacency(grid[i][j-1]);

				}
				if ((j+1) <= COLS-1) {
					grid[i][j].addAdjacency(grid[i][j+1]);

				}				
				
			}
			
		}
	}

}
