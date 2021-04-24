package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class ComputerPlayer extends Player {
	
	public ComputerPlayer(String name, String color, int row, int col) {
		super(name, color, row, col);
		// TODO Auto-generated constructor stub
	
	}
	
	public Solution createSuggestion(ArrayList<Card> deck, Room room) {
		 
		Collections.shuffle(deck);																				//shuffling
		Solution possibleSolution = new Solution();
		
		for(int i = 0; i < deck.size(); i++) 																	//if certain parts match, set equal
		{
			if(deck.get(i).getCardName().equals(room.getName()) && deck.get(i).getType() == CardType.ROOM) 
			{
				possibleSolution.setRoom(deck.get(i));
			}
			
			if(hand.contains(deck.get(i)) || seenCards.contains(deck.get(i))) 									//continue statement
			{
				continue;
			}
			
			if(deck.get(i).getType() == CardType.WEAPON && possibleSolution.getWeapon() == null) 				//if type equals weapon and null set equal
			{
				possibleSolution.setWeapon(deck.get(i));
			}
			
			else if(deck.get(i).getType() == CardType.PERSON && possibleSolution.getPerson() == null) 
			{
				possibleSolution.setPerson(deck.get(i));
			}																									//if type equals person and null set equal
		}
		
		return possibleSolution;
	}
	
	public BoardCell selectTargets(Set<BoardCell> targets) {
		
		ArrayList<BoardCell> newDeck = new ArrayList<BoardCell>();
		
		for(BoardCell i: targets) 																				//range base for set
		{
			newDeck.add(i);
		}
		
		Collections.shuffle(newDeck);																			//shuffling
		
		for(int i = 0; i < newDeck.size(); i++) 
		{
			boolean saw = false;																				//boolean equal false
			
			for(int j = 0; j < seenCards.size(); j++) 
			{
				if(newDeck.get(i).isRoomCenter() && seenCards.get(j).getType() == CardType.ROOM) 				//if seen cards and roomcenter true set saw equal to true
				{
					if(newDeck.get(i).getInitial() == seenCards.get(j).getCardName().charAt(0)) 
					{
						saw = true;
					}
				}
			}
			if(saw == false && newDeck.get(i).isRoomCenter()) 													//setting newdeck to the index
			{
				return newDeck.get(i);
			}
		}
		
		for(int i = 0; i < newDeck.size(); i++) 																//finding last option
		{
			if(!newDeck.get(i).isRoomCenter()) 
			{
				return newDeck.get(i);
			}
		}
		
		return null;
	}
}





