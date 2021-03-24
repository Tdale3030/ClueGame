package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.Card;
import clueGame.CardType;

class gameSetupTest {

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
    public void cardTesting() {
        ArrayList<Card> deck = board.getDeck();
        assertEquals(21, deck.size());
        assertTrue(deck.contains(board.getCard("Anakin", CardType.PERSON)));
        assertTrue(deck.contains(board.getCard("Bedroom", CardType.ROOM)));
        assertTrue(deck.contains(board.getCard("Blaster", CardType.WEAPON)));
        assertTrue(deck.contains(board.getCard("Gungi", CardType.PERSON)));

    }

}
