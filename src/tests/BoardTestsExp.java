package tests;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import experiment.TestBoard;
import experiment.TestBoardCell;



class BoardTestsExp {
	TestBoard board;
	
	
	@BeforeEach
	public void setUp() {
		board= new TestBoard();
		
		
	}
	
	@Test
	public void testAdjacency() {
		TestBoardCell cell=board.getCell(0,0);
		Set<TestBoardCell> testList= cell.getAdjList();
		Assert.assertTrue(testList.contains(board.getCell(1,0)));
		Assert.assertTrue(testList.contains(board.getCell(0,1)));
		Assert.assertEquals(2, testList.size());
	}
	
	
	
	@Test
	public void testTargetsNormal() {
		TestBoardCell cell=board.getCell(0,0);
		
		
		
		
		
		
	}

}
