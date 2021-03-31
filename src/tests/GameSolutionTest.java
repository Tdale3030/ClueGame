package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.CardType;
import clueGame.ComputerPlayer;
import clueGame.HumanPlayer;
import clueGame.Player;
import clueGame.Solution;

class GameSolutionTest {

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

	@Test
	public void testCheckAccusation() {
		
		Card card = new Card("Anakin", "Person");
        Card cardOne = new Card("Force", "Weapon");
        Card cardTwo = new Card("Pool", "Room");
        board.setSolution(cardTwo, cardOne, card);
        Solution answer = board.getSolution();
        Solution solution = new Solution();
        solution.setRoom(cardTwo);
        solution.setPerson(card);
        solution.setWeapon(cardOne);
        assertTrue(board.checkAccusation(solution));
        Card cardThree = new Card("Maul", "Person");
        cardTwo = new Card("Bedroom", "Room");
        cardThree = new Card("Blaster", "Weapon");
        solution.setRoom(cardTwo);
        solution.setWeapon(cardOne);
        solution.setPerson(cardThree);
        assertFalse(board.checkAccusation(solution));

	}

	@Test
	public void testDisproveSuggestion() {
		
		Solution solution = new Solution();
		Card cardOne = new Card("Anakin", "Person");
		Card cardTwo = new Card("Blue Saber", "Weapon");
		Card cardThree = new Card("Pool", "Room");
		solution.setPerson(cardOne);
		solution.setWeapon(cardTwo);
		solution.setRoom(cardThree);
		Player newPlayer = new ComputerPlayer("Boba", "Green", 5, 0);
		newPlayer.updateHand(cardOne);
		newPlayer.updateHand(cardTwo);
		newPlayer.updateHand(cardThree);
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		
		for(int i = 0; i < 200; i++) 
		{
			Card disprove = newPlayer.disproveSuggestion(solution);
			
			if(disprove.equals(cardOne)) 
			{
				count1 += 1;
			}
			else if(disprove.equals(cardTwo)) 
			{
				count2 += 1;
			}
			else if(disprove.equals(cardThree)) 
			{
				count3 += 1;
			}
		}
		
		assertTrue(count1 > 0);
		assertTrue(count3 > 0);
		assertTrue(count2 > 0);
		Card cardFour = new Card("Gungi", "Person");
		Card cardFive = new Card("Force", "Weapon");
		Card cardSix = new Card("Bedroom", "Room");
		solution.setPerson(cardFour);
		solution.setWeapon(cardFive);
		solution.setRoom(cardSix);
		assertTrue(newPlayer.disproveSuggestion(solution) == null);
		
	}
	
	@Test
	public void testHandleSuggestion() {
		
		ArrayList<Player> newList = new ArrayList<Player>();
		
		Card cardZero = new Card("Gungi","Character");
		Card cardOne = new Card("Maul","Character");
		Card cardTwo = new Card("Kenobi","Character");
		Card cardThree = new Card("Attic","Room");
		Card cardFour = new Card("Pool","Room");
		Card cardFive = new Card("Kitchen","Room");
		Card cardSix = new Card("TwoSided Saber","Weapon");
		Card cardSeven = new Card("Force","Weapon");
		Card cardEight = new Card("Blaster","Weapon");

		Player firstPlayer = new ComputerPlayer("Boba", "Green", 5,0);
		
		firstPlayer.updateHand(cardZero);
		firstPlayer.updateHand(cardThree);
		firstPlayer.updateHand(cardSix);
		newList.add(firstPlayer);
		
		Player secondPlayer = new ComputerPlayer("Boba", "Green", 5,0);
		secondPlayer.updateHand(cardTwo);
		secondPlayer.updateHand(cardFour);
		secondPlayer.updateHand(cardSeven);
		newList.add(secondPlayer);
		
		Solution solution = new Solution();
		solution.setPerson(cardZero);
		solution.setRoom(cardFive);
		solution.setWeapon(cardEight);
		board.playerList(newList);
		
		assertFalse(board.handleSuggestions(solution).equals(cardOne));
		
		
	}
	
}