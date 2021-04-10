package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public abstract class Player {
	
	private String name;
	private Color color;
	protected int row;
	protected int col;
	private int[] location;
	protected ArrayList<Card> hand;
	protected ArrayList<Card> seenCards;
	private int height;
	private int width;
	
	
	public Player(String name, String color, int row, int col) {
		super();
		this.name = name;
	 
		if(color.equals("Blue")) 
		{
			this.color=Color.blue;
			
		}
		if(color.equals("Red")) 
		{
			this.color=Color.red;
			
		}
		if(color.equals("Green")) 
		{
			this.color=Color.green;
			
		}
		if(color.equals("Orange")) 
		{
			this.color=new Color(255,120,10);
			
		}
		if(color.equals("Purple")) 
		{
			this.color=Color.magenta;
			
		}
		if(color.equals("Brown")) 
		{
			this.color=new Color(87,65,47);
			
		}

		this.row=row;
		this.col=col;
		this.location= new int[2];
		this.location[0]=this.row;
		this.location[1]=this.col;
		this.hand=new ArrayList<Card>();
		this.seenCards=new ArrayList<Card>();
	}
	
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	
	public void draw(Graphics graphics) {
		
		int size = 0;
		
		if(height > width) 											//allows user to move the window
		{
			size = width;
		}
		else if(width > height) 
		{
			size = height;
		}
		
		graphics.setColor(color);													//creates the player circle at the the correct size
		graphics.fillOval(size * col,size * row,size - size / 5,size - size / 5);
	}
	
	public void updateHand(Card card) {
		
		hand.add(card);
		
	}

	public String getName() {
		
		return name;
	}

	public Color getColor() {
		
		return color;
	}

	public int getRow() {
		
		return row;
	}

	public int getCol() {
		
		return col;
	}

	public int[] getLocation() {
		
		return location;
		
	}

	public ArrayList<Card> getHand(){
		
		return hand;
	}
	
	public ArrayList<Card> getSeenCards() {
		return seenCards;
	}


	public void setSeenCards(ArrayList<Card> seenCards) {
		this.seenCards = seenCards;
	}


	public Card disproveSuggestion(Solution solution) {
		
		Collections.shuffle(hand);
		for(int i = 0; i < hand.size(); i++) 
		{
			if(hand.get(i) == solution.getPerson()) 			//if statements to find the correct hand
			{
				return hand.get(i);
			}
			if(hand.get(i) == solution.getRoom()) 
			{
				return hand.get(i);
			}
			if(hand.get(i) == solution.getWeapon()) 
			{
				return hand.get(i);
			}
		}
		return null;
	}
	
	public void updateSeen(Card seenCard) {
		
		seenCards.add(seenCard);
	}
	
	public abstract Solution createSuggestion(ArrayList<Card> deck, Room room);
	
	public abstract BoardCell selectTargets(Set<BoardCell> targets);

}
