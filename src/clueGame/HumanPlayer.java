package clueGame;

import java.util.ArrayList;
import java.util.Set;

public class HumanPlayer extends Player {
	
	private boolean suggestionMade;
	
	
	public boolean isSuggestionMade() {
		return suggestionMade;
	}


	public void setSuggestionMade(boolean suggestionMade) {
		this.suggestionMade = suggestionMade;
	}


	public HumanPlayer(String name, String color, int row, int col) {
		super(name, color, row, col);
		// TODO Auto-generated constructor stub
	}
	
	
	public Solution createSuggestion(ArrayList<Card> deck, Room room) {
		
		return new Solution();
	}
	
	public BoardCell selectTargets(Set<BoardCell> targets) {
		
		return new BoardCell(0, 0);
	}
}
