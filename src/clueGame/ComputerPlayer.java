package clueGame;

import java.awt.Color;
import java.util.ArrayList;

public class ComputerPlayer extends Player {
	
	public ComputerPlayer(String name, String color, int row, int col) {
		super(name, color, row, col);
		// TODO Auto-generated constructor stub
	
	}
	
	
	
	
	
	
	
	
	public Solution createSuggestion() {
		
		return new Solution();
	}
	
	public BoardCell selectTargets() {
		
		return new BoardCell(0, 0);
	}
}
