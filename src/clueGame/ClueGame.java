package clueGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.RootPaneContainer;

public class ClueGame extends JFrame {
	
	private static Board board;
	
	public ClueGame() throws FileNotFoundException, BadConfigFormatException {
		//board = new Board();
		
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("data/ClueLayout.csv", "data/ClueSetup.txt");		
		// Initialize will load config files 
		board.initialize();
		
		setSize(new Dimension(1100,1000)); // size the frame
		Player player = new ComputerPlayer("Boba","Green",5,0);					//computer players
		JPanel east = new clueCardsGUI(player);
		JPanel south = new GameControlPanel();
		JPanel center = new JPanel();
		east.setPreferredSize(new Dimension(250,400));
		south.setPreferredSize(new Dimension(800,200));
		add(board, BorderLayout.CENTER);
		add(east, BorderLayout.EAST);
		add(south, BorderLayout.SOUTH); 
		
		
	}
	
	public static void main(String[] args) throws FileNotFoundException, BadConfigFormatException {
		
		ClueGame frame = new ClueGame();  // create the panel
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		frame.setVisible(true); // make it visible

	}
	
}
