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
import clueGame.Room;
import clueGame.Solution;

class ComputerAITest {

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
	public void testSelectTargets() {
		
		BoardCell cell = board.getCell(3, 4);
		board.calcTargets(cell, 3);
		Set<BoardCell> target = board.getTargets();
		Player newPlayer = new ComputerPlayer("Boba", "Green", 5, 0);
		BoardCell newCell = new BoardCell(0, 0);
		newCell = newPlayer.selectTargets(target);
		assertTrue(newCell.getInitial() == 'H');

	}
	
	@Test
	public void testCreateSuggestion()  {
		
	    Player player = new ComputerPlayer("Boba", "Green", 5, 0);
        ArrayList<Card> deck = board.getDeck();
        Room room = new Room("Church");

        for(int i = 0; i < deck.size(); i++) 
        {
        	if(deck.get(i).equals(new Card("Gungi", "Character")))
        	{
        		player.updateSeen(deck.get(i));
        	}
        	
        	if(deck.get(i).equals(new Card("Kenobi", "Character")))
        	{
        		player.updateSeen(deck.get(i));
        	}
        	
        	if(deck.get(i).equals(new Card("Windu", "Character")))
        	{
        		player.updateSeen(deck.get(i));
        	}
        	if(deck.get(i).equals(new Card("Maul", "Character")))
        	{
        		player.updateSeen(deck.get(i));
        	}
        	
        }
		
		Solution computerSolution = player.createSuggestion(board.getDeck(), room);
		assertTrue(computerSolution.getPerson() != null);
		assertTrue(computerSolution.getRoom() != null);
		assertTrue(computerSolution.getWeapon() != null);
	}
	
}
