package clueGame;

import java.util.ArrayList;

public class HumanPlayer extends Player {
	
	public HumanPlayer(String name, String color, int row, int col) {
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
