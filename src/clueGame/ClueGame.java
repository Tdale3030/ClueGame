package clueGame;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.RootPaneContainer;

public class ClueGame extends JFrame {
	
	public ClueGame() {
		setSize(new Dimension(1000,1000)); // size the frame
		Player player = new ComputerPlayer("Boba","Green",5,0);					//computer players
		JPanel east = new clueCardsGUI(player);
		JPanel south = new GameControlPanel();
		add(east, BorderLayout.EAST);
		add(south, BorderLayout.SOUTH);
		
	}
	
	public static void main(String[] args) {
		
		ClueGame frame = new ClueGame();  // create the panel
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		frame.setVisible(true); // make it visible

	}
	
	
	

}
