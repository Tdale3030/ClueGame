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
import clueGame.Card;
import clueGame.CardType;
import clueGame.ComputerPlayer;
import clueGame.HumanPlayer;
import clueGame.Player;

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
	@Test
    public void playerTesting() {
        ArrayList<Player> players = board.getPlayerList();
        assertEquals(6, players.size());
        Player player = board.getPlayer("Anakin");
         assertTrue(player instanceof HumanPlayer);
        player = board.getPlayer("Boba");
        assertTrue(player instanceof ComputerPlayer);
        assertTrue(players.contains(board.getPlayer("Gungi")));
        assertTrue(players.contains(board.getPlayer("Kenobi")));
        assertTrue(Arrays.equals(board.getPlayer("Anakin").getLocation(), new int[] {15,0}));
        assertTrue(Arrays.equals( board.getPlayer("Maul").getLocation(), new int[] {21,16}));
        assertTrue(board.getPlayer("Maul").getColor() == Color.RED);
        assertTrue(board.getPlayer("Windu").getColor() == Color.BLUE);
    }

}