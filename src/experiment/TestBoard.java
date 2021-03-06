package experiment;

import java.util.HashSet;
import java.util.Set;


public class TestBoard {
	private TestBoardCell[][] grid;
	private Set<TestBoardCell> targets;
	private Set<TestBoardCell> visited;
	public final static int COLS = 4;
	public final static int ROWS = 4;
	Set<TestBoardCell> adjList;
	
	public TestBoard() {
		super();
		this.adjList = new HashSet<TestBoardCell>();
		this.grid=new TestBoardCell[ROWS][COLS];
		for(int i=0;i<ROWS;i++) {
			for(int j=0;j<COLS;j++) {
				grid[i][j] = new TestBoardCell(i, j);
			}
		}
		allAdj();
	}
	
	public void calcTargets( TestBoardCell startCell, int pathlength) {
		visited = new HashSet<TestBoardCell>();
		targets = new HashSet<TestBoardCell>();
		visited.add(startCell);
		
		findAllTargets(startCell, pathlength);
	}
	
	public Set<TestBoardCell> getTargets(){
		return targets;
	
	}
	
	public TestBoardCell getCell( int row, int col ) {
		return grid[row][col];
		
	}
	
	public void allAdj() {

		for(int i=0;i<ROWS;i++) {
			for(int j=0;j<COLS;j++) {
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

	
	public void findAllTargets(TestBoardCell thisCell, int numSteps) {
		adjList = thisCell.getAdjList();
		for(TestBoardCell i: adjList) {
			if(visited.contains(i)) {
				continue;
			}
			if((i.getOccupied())) {
				continue;
			}
			if(thisCell.isRoom() != i.isRoom()) {
				targets.add(i);
				continue;
			}
			visited.add(i);
			if (numSteps == 1) {
				targets.add(i);
			}
			else {
				findAllTargets(i, numSteps - 1);
			}
			visited.remove(i);
		}
	}

}
