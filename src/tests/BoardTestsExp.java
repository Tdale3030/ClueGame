package tests;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.Assert;
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
		
		cell=board.getCell(3,3);
		testList= cell.getAdjList();
		Assert.assertTrue(testList.contains(board.getCell(2,3)));
		Assert.assertTrue(testList.contains(board.getCell(3,2)));
		Assert.assertEquals(2, testList.size());
		
		cell=board.getCell(1,3);
		testList= cell.getAdjList();
		Assert.assertTrue(testList.contains(board.getCell(0,3)));
		Assert.assertTrue(testList.contains(board.getCell(1,2)));
		Assert.assertTrue(testList.contains(board.getCell(2,3)));
		Assert.assertEquals(3, testList.size());
		
		cell=board.getCell(3,0);
		testList= cell.getAdjList();
		Assert.assertTrue(testList.contains(board.getCell(2,0)));
		Assert.assertTrue(testList.contains(board.getCell(3,1)));
		Assert.assertEquals(2, testList.size());
		
		cell=board.getCell(2,2);
		testList= cell.getAdjList();
		Assert.assertTrue(testList.contains(board.getCell(2,1)));
		Assert.assertTrue(testList.contains(board.getCell(1,2)));
		Assert.assertTrue(testList.contains(board.getCell(3,2)));
		Assert.assertTrue(testList.contains(board.getCell(2,3)));
		Assert.assertEquals(4, testList.size());
		
	}
	
	@Test
	public void testTargetsNormal() {
		TestBoardCell cell = board.getCell(0, 0);
		board.calcTargets(cell, 3);
		Set<TestBoardCell> targets = board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(3,0)));
		Assert.assertTrue(targets.contains(board.getCell(2,1)));
		Assert.assertTrue(targets.contains(board.getCell(0,1)));		
		Assert.assertTrue(targets.contains(board.getCell(1,2)));		
		Assert.assertTrue(targets.contains(board.getCell(0,3)));		
		Assert.assertTrue(targets.contains(board.getCell(1,0)));
		
		cell = board.getCell(1, 0);
		board.calcTargets(cell, 3);
		targets = board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(0,0)));
		Assert.assertTrue(targets.contains(board.getCell(1,1)));
		Assert.assertTrue(targets.contains(board.getCell(3,1)));		
		Assert.assertTrue(targets.contains(board.getCell(2,0)));		
		Assert.assertTrue(targets.contains(board.getCell(3,1)));		
		Assert.assertTrue(targets.contains(board.getCell(2,2)));
		
		cell = board.getCell(1, 1);
		board.calcTargets(cell, 3);
		targets = board.getTargets();
		Assert.assertEquals(9, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(0,0)));
		Assert.assertTrue(targets.contains(board.getCell(0,1)));
		Assert.assertTrue(targets.contains(board.getCell(3,1)));		
		Assert.assertTrue(targets.contains(board.getCell(1,0)));		
		Assert.assertTrue(targets.contains(board.getCell(2,1)));		
		Assert.assertTrue(targets.contains(board.getCell(1,2)));
		Assert.assertTrue(targets.contains(board.getCell(3,2)));
		Assert.assertTrue(targets.contains(board.getCell(3,0)));
		Assert.assertTrue(targets.contains(board.getCell(2,3)));
		
		cell = board.getCell(0, 1);
		board.calcTargets(cell, 3);
		targets = board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(0,0)));
		Assert.assertTrue(targets.contains(board.getCell(2,0)));
		Assert.assertTrue(targets.contains(board.getCell(3,1)));		
		Assert.assertTrue(targets.contains(board.getCell(2,0)));		
		Assert.assertTrue(targets.contains(board.getCell(2,2)));		
		Assert.assertTrue(targets.contains(board.getCell(1,3)));
		
		cell = board.getCell(2, 0);
		board.calcTargets(cell, 3);
		targets = board.getTargets();
		Assert.assertEquals(7, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(1,0)));
		Assert.assertTrue(targets.contains(board.getCell(0,1)));
		Assert.assertTrue(targets.contains(board.getCell(2,1)));		
		Assert.assertTrue(targets.contains(board.getCell(3,0)));		
		Assert.assertTrue(targets.contains(board.getCell(1,2)));		
		Assert.assertTrue(targets.contains(board.getCell(3,2)));
		Assert.assertTrue(targets.contains(board.getCell(2,3)));
		
		cell = board.getCell(0, 2);
		board.calcTargets(cell, 3);
		targets = board.getTargets();
		Assert.assertEquals(7, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(1,0)));
		Assert.assertTrue(targets.contains(board.getCell(0,1)));
		Assert.assertTrue(targets.contains(board.getCell(2,1)));		
		Assert.assertTrue(targets.contains(board.getCell(1,2)));		
		Assert.assertTrue(targets.contains(board.getCell(0,3)));		
		Assert.assertTrue(targets.contains(board.getCell(3,2)));
		Assert.assertTrue(targets.contains(board.getCell(2,3)));
		
		
	}		
	
	@Test
	public void testTargetsMixed() {
		board.getCell(0,2).setOccupied(true);
		board.getCell(0,2).setRoom(true);
		TestBoardCell cell=board.getCell(0,3);
		board.calcTargets(cell,3);
		Set<TestBoardCell> targets= board.getTargets();
		Assert.assertEquals(3,targets.size());
		Assert.assertTrue(targets.contains(board.getCell(1, 2)));
		Assert.assertTrue(targets.contains(board.getCell(2, 2)));
		Assert.assertTrue(targets.contains(board.getCell(3, 3)));
		
		board.getCell(2,2).setOccupied(true);
		board.getCell(2,2).setRoom(true);
		cell=board.getCell(3,2);
		board.calcTargets(cell,3);
		targets= board.getTargets();
		Assert.assertEquals(3,targets.size());
		Assert.assertTrue(targets.contains(board.getCell(1,3)));
		Assert.assertTrue(targets.contains(board.getCell(1,1)));
		Assert.assertTrue(targets.contains(board.getCell(2,0)));
		
		board.getCell(1,1).setOccupied(true);
		board.getCell(0,1).setRoom(true);
		cell=board.getCell(0,0);
		board.calcTargets(cell,3);
		targets= board.getTargets();
		Assert.assertEquals(2,targets.size());
		Assert.assertTrue(targets.contains(board.getCell(3,0)));
		Assert.assertTrue(targets.contains(board.getCell(2,1)));
		
		board.getCell(1,3).setOccupied(true);
		board.getCell(1,0).setRoom(true);
		cell=board.getCell(1,2);
		board.calcTargets(cell,2);
		targets= board.getTargets();
		Assert.assertEquals(5,targets.size());
		Assert.assertTrue(targets.contains(board.getCell(0,3)));
		Assert.assertTrue(targets.contains(board.getCell(0,1)));
		Assert.assertTrue(targets.contains(board.getCell(2,1)));
		Assert.assertTrue(targets.contains(board.getCell(2,3)));
		Assert.assertTrue(targets.contains(board.getCell(3,2)));


		
		board.getCell(1,0).setOccupied(true);
		board.getCell(0,1).setRoom(true);
		cell=board.getCell(0,2);
		board.calcTargets(cell,3);
		targets= board.getTargets();
		Assert.assertEquals(5,targets.size());
		Assert.assertTrue(targets.contains(board.getCell(2,1)));		
		Assert.assertTrue(targets.contains(board.getCell(1,2)));		
		Assert.assertTrue(targets.contains(board.getCell(0,3)));		
		Assert.assertTrue(targets.contains(board.getCell(3,2)));
		Assert.assertTrue(targets.contains(board.getCell(2,3)));
		
		
	}
	
	

}
