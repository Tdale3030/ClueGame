package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

public class ComputerPlayer extends Player {
	
	public ComputerPlayer(String name, String color, int row, int col) {
		super(name, color, row, col);
		// TODO Auto-generated constructor stub
	
	}
	
	public Solution createSuggestion(ArrayList<Card> deck, Room room) {
		
		Collections.shuffle(deck);
		Solution possibleSolution = new Solution();
		
		for(int i = 0; i < deck.size(); i++) 
		{
			if(deck.get(i).getCardName().equals(room.getName()) && deck.get(i).getType() == CardType.ROOM) 
			{
				possibleSolution.setRoom(deck.get(i));
			}
			
			if(hand.contains(deck.get(i)) || seenCards.contains(deck.get(i))) 
			{
				continue;
			}
			
			if(deck.get(i).getType() == CardType.WEAPON && possibleSolution.getWeapon() == null) 
			{
				possibleSolution.setWeapon(deck.get(i));
			}
			
			else if(deck.get(i).getType() == CardType.PERSON && possibleSolution.getPerson() == null) 
			{
				possibleSolution.setPerson(deck.get(i));
			}
		}
		
		return possibleSolution;
	}
	
	public BoardCell selectTargets() {
		
		return new BoardCell(0, 0);
	}
}
