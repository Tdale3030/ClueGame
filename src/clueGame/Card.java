package clueGame;

public class Card {

	private String cardName;
	private CardType cardType;

	public Card(String cardName, String type) {
		
		super();
		this.cardName = cardName;
		
		if(type.equals("Room")) 
		{
			cardType = CardType.ROOM;
		}
		if(type.equals("Character")) 
		{
			cardType = CardType.PERSON;
		}
		if(type.equals("Weapon")) 
		{
			cardType = CardType.WEAPON;
		}
	}
	
	public boolean equals(Card target) {
		
		if(target.getCardName().equals(cardName) && target.getType() == cardType) 
		{
			return true;
			
		}else {
			return false;
		}
	}

	public String getCardName() {
		
		return cardName;
	}

	public void setCardName(String cardName) {
		
		this.cardName = cardName;
	}
	
	public CardType getType() {
		
		return cardType;
		
	}
	
	
}
