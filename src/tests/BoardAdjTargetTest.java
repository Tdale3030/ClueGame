//tyner and dale
package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.BoardCell;

public class BoardAdjTargetTest {
	// We make the Board static because we can load it one time and 
	// then do all the tests. 
	private static Board board;
	
	@BeforeAll
	public static void setUp() throws FileNotFoundException, BadConfigFormatException {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("data/ClueLayout.csv", "data/ClueSetup.txt");		
		// Initialize will load config files 
		board.initialize();
	}
 
	// Ensure that player does not move around within room
	// These cells are LIGHT ORANGE on the planning spreadsheet
	@Test
	public void testAdjacenciesRooms()
	{ 
		// we want to test a couple of different rooms.
		// First, the Conservatory that only has a single door but a secret room
		Set<BoardCell> testList = board.getAdjList(2, 19);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCell(3, 19)));
		assertTrue(testList.contains(board.getCell(2, 18)));
		
		// now test the ballroom (note not marked since multiple test here)
		testList = board.getAdjList(19, 21);
		assertEquals(2, testList.size());
		assertTrue(testList.contains(board.getCell(18, 17)));
		
		// one more room, the kitchen
		testList = board.getAdjList(19, 19);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCell(19, 20)));
		assertTrue(testList.contains(board.getCell(20, 19)));
	}

	
	// Ensure door locations include their rooms and also additional walkways
	// These cells are LIGHT ORANGE on the planning spreadsheet
	@Test
	public void testAdjacencyDoor()
	{
		Set<BoardCell> testList = board.getAdjList(14, 24);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCell(14, 25)));
		assertTrue(testList.contains(board.getCell(14, 23)));

		testList = board.getAdjList(19, 5);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCell(18, 5)));
		assertTrue(testList.contains(board.getCell(19, 4)));
		assertTrue(testList.contains(board.getCell(19, 6)));
		
		testList = board.getAdjList(18, 7);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCell(17, 7)));
		assertTrue(testList.contains(board.getCell(19, 7)));
		assertTrue(testList.contains(board.getCell(18, 8)));
		assertTrue(testList.contains(board.getCell(19, 3)));
	}
	
	// Test a variety of walkway scenarios
	// These tests are Dark Orange on the planning spreadsheet
	@Test
	public void testAdjacencyWalkways()
	{
		// Test on bottom edge of board, just one walkway piece
		Set<BoardCell> testList = board.getAdjList(17, 10);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCell(18, 10)));
		assertTrue(testList.contains(board.getCell(16, 10)));
		assertTrue(testList.contains(board.getCell(17, 11)));

		// Test near a door but not adjacent
		testList = board.getAdjList(18, 4);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCell(18, 5)));
		assertTrue(testList.contains(board.getCell(19, 4)));
		assertTrue(testList.contains(board.getCell(17, 4)));
		assertTrue(testList.contains(board.getCell(18, 3)));


		// Test adjacent to walkways
		testList = board.getAdjList(19, 6);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCell(18, 6)));
		assertTrue(testList.contains(board.getCell(20, 6)));
		assertTrue(testList.contains(board.getCell(19, 5)));


		// Test next to closet
		testList = board.getAdjList(9,14);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCell(9, 13)));
		assertTrue(testList.contains(board.getCell(8, 14)));
		assertTrue(testList.contains(board.getCell(10, 14)));
		assertTrue(testList.contains(board.getCell(9, 15)));

	}
	
	
	// Tests out of room center, 1, 3 and 4
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsInBedRoom() {
		// test a roll of 1
		board.calcTargets(board.getCell(10, 21), 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(4, targets.size());
		assertFalse(targets.contains(board.getCell(2, 21)));
		assertTrue(targets.contains(board.getCell(11, 21)));	
		
		// test a roll of 3
		board.calcTargets(board.getCell(10, 21), 3);
		targets= board.getTargets();
		assertEquals(14, targets.size());
		assertTrue(targets.contains(board.getCell(9, 23)));
		assertTrue(targets.contains(board.getCell(10, 24)));	
		assertTrue(targets.contains(board.getCell(11, 21)));
		assertTrue(targets.contains(board.getCell(8, 20)));	
		
		// test a roll of 4
		board.calcTargets(board.getCell(10, 21), 4);
		targets= board.getTargets();
		assertEquals(17, targets.size());
		assertTrue(targets.contains(board.getCell(10, 19)));
		assertTrue(targets.contains(board.getCell(9, 24)));	
		assertTrue(targets.contains(board.getCell(11,24 )));
		assertTrue(targets.contains(board.getCell(12, 19)));	
	}
	
	@Test
	public void testTargetsInKitchen() {
		// test a roll of 1
		board.calcTargets(board.getCell(2, 10), 1);
		Set<BoardCell> targets= board.getTargets();
		//assertEquals(1, targets.size());
		assertFalse(targets.contains(board.getCell(4, 11)));
		
		
		// test a roll of 3
		board.calcTargets(board.getCell(2, 10), 3);
		targets= board.getTargets();
		//assertEquals(4, targets.size());
		//assertTrue(targets.contains(board.getCell(4, 13)));
		//assertTrue(targets.contains(board.getCell(5,10)));	
		//assertTrue(targets.contains(board.getCell(5, 12)));
		//assertTrue(targets.contains(board.getCell(4, 9)));	
		
		// test a roll of 4
		board.calcTargets(board.getCell(2, 10), 4);
		targets= board.getTargets();
		assertEquals(9, targets.size());
		assertTrue(targets.contains(board.getCell(2, 8)));
		assertTrue(targets.contains(board.getCell(1, 9)));	
		assertTrue(targets.contains(board.getCell(3, 11)));
		assertTrue(targets.contains(board.getCell(1, 11)));	
		assertTrue(targets.contains(board.getCell(0, 10)));	
		assertTrue(targets.contains(board.getCell(2, 12)));	
		assertTrue(targets.contains(board.getCell(0, 12)));	
		assertTrue(targets.contains(board.getCell(2, 11)));	
		assertTrue(targets.contains(board.getCell(3, 9)));
	}

	// Tests out of room center, 1, 3 and 4
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsAtDoor() {
		// test a roll of 1, at door
		board.calcTargets(board.getCell(5, 3), 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCell(5, 4)));
		assertTrue(targets.contains(board.getCell(6, 3)));	
		assertTrue(targets.contains(board.getCell(5, 2)));	
		assertTrue(targets.contains(board.getCell(4, 3)));	

		// test a roll of 3
		board.calcTargets(board.getCell(10, 17), 3);
		targets= board.getTargets();
		assertEquals(8, targets.size());
		//assertTrue(targets.contains(board.getCell(10, 21)));
		assertTrue(targets.contains(board.getCell(10, 16)));
		assertTrue(targets.contains(board.getCell(7, 17)));	
		assertTrue(targets.contains(board.getCell(8, 16)));
		assertTrue(targets.contains(board.getCell(12, 16)));	
		
		// test a roll of 4
		board.calcTargets(board.getCell(17, 7), 4);
		targets= board.getTargets();
		assertEquals(14, targets.size());
		assertTrue(targets.contains(board.getCell(15, 9)));
		assertTrue(targets.contains(board.getCell(19, 9)));
		assertTrue(targets.contains(board.getCell(18, 8)));	
		assertTrue(targets.contains(board.getCell(19, 7)));
		assertTrue(targets.contains(board.getCell(15, 5)));	
	}

	@Test
	public void testTargetsInWalkway1() {
		// test a roll of 1
		board.calcTargets(board.getCell(5, 4), 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCell(6, 4)));
		assertTrue(targets.contains(board.getCell(5, 3)));	
		
		// test a roll of 3
		board.calcTargets(board.getCell(8, 18), 3);
		targets= board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(9, 18)));
		assertTrue(targets.contains(board.getCell(9, 20)));
		assertTrue(targets.contains(board.getCell(11, 18)));	
		
		// test a roll of 4
		board.calcTargets(board.getCell(19, 9), 4);
		targets= board.getTargets();
		assertEquals(9, targets.size());
		assertTrue(targets.contains(board.getCell(19, 3)));
		assertTrue(targets.contains(board.getCell(19, 7)));
		assertTrue(targets.contains(board.getCell(17, 9)));	
		assertTrue(targets.contains(board.getCell(16, 8)));	
		assertTrue(targets.contains(board.getCell(17, 7)));	
		assertTrue(targets.contains(board.getCell(20, 8)));	
		
	}
	@Test
	public void testTargetsInWalkway2() {
		// test a roll of 1
		board.calcTargets(board.getCell(13, 7), 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCell(13, 6)));
		assertTrue(targets.contains(board.getCell(12, 7)));
		// test a roll of 3
		board.calcTargets(board.getCell(13, 7), 3);
		targets= board.getTargets();
		assertEquals(15, targets.size());
		assertTrue(targets.contains(board.getCell(15, 8)));
		assertTrue(targets.contains(board.getCell(16, 7)));
		assertTrue(targets.contains(board.getCell(12, 7)));
		// test a roll of 4
		board.calcTargets(board.getCell(13, 7), 4);
		targets= board.getTargets();
		assertEquals(19, targets.size());
		assertTrue(targets.contains(board.getCell(12, 8)));
		assertTrue(targets.contains(board.getCell(10, 8)));
		assertTrue(targets.contains(board.getCell(15, 5)));
		}
	@Test
	public void testTargetsOccupied() {
		// test a roll of 4 blocked 2 down
		
		board.getCell(15, 7).setOccupied(true);
		board.calcTargets(board.getCell(13, 7), 4);
		board.getCell(15, 7).setOccupied(false);
		Set<BoardCell> targets = board.getTargets();
		//assertEquals(18, targets.size());
		assertTrue(targets.contains(board.getCell(13, 3)));
		assertTrue(targets.contains(board.getCell(14, 8)));
		assertTrue(targets.contains(board.getCell(12, 4)));
		assertFalse( targets.contains( board.getCell(15, 7))) ;
		assertFalse( targets.contains( board.getCell(17, 7))) ;
		// we want to make sure we can get into a room, even if flagged as occupied
		board.getCell(12, 20).setOccupied(true);
		board.getCell(8, 18).setOccupied(true);
		board.calcTargets(board.getCell(8, 17), 1);
		board.getCell(12, 20).setOccupied(false);
		board.getCell(8, 18).setOccupied(false);
		targets= board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCell(7, 17)));
		assertTrue(targets.contains(board.getCell(9, 17)));
		assertTrue(targets.contains(board.getCell(8, 16)));
		// check leaving a room with a blocked doorway
		board.getCell(12, 15).setOccupied(true);
		board.calcTargets(board.getCell(12, 20), 3);
		board.getCell(12, 15).setOccupied(false);
		targets= board.getTargets();
		assertEquals(9, targets.size());
		assertTrue(targets.contains(board.getCell(11, 22)));
		assertTrue(targets.contains(board.getCell(12, 19)));
		assertTrue(targets.contains(board.getCell(12, 23)));
		
	}
	
}