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
	private boolean inRoom = false;
	private int pos;
	private Room room;
	private boolean guessedCorrect = false;
	
	
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
	
	public void setInRoom(boolean inRoom) {
		this.inRoom = inRoom;
	}
	
	public void setPos(int Pos) {
		this.pos = Pos;
	}
	
	public void draw(Graphics graphics) {
		
		int size = 0;
		
		if(height > width) 																				//allows user to move the window
		{
			size = width;
		}
		else if(width > height) 
		{
			size = height;
		}
		if (inRoom) {
			graphics.setColor(color);																	//creates the player circle at the the correct size
			graphics.fillOval(size * col+2 + (10 * pos),size * row+2,size - size / 5,size - size / 5);
		}
		else {
			graphics.setColor(color);																	//creates the player circle at the the correct size
			graphics.fillOval(size * col+2,size * row+2,size - size / 5,size - size / 5);
		}
	
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
	public void setCol(int col) {
		
		this.col= col;
		
	}
	public void setRow(int row) {
		
		this.row= row;
		
	}
	public boolean isInRoom() {
		return inRoom;
	}


	public int[] getLocation() {
		
		return location;
		
	}
	
	public void setRoom(Room room) {
		this.room = room;
	}
	
	public Room getRoom() {
		return room;
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
			if(hand.get(i) == solution.getPerson()) 															//if statements to find the correct hand
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
	public void setGuessedCorrect(boolean bool) {
		this.guessedCorrect = true;
	}
	public boolean getGuessedCorrect() {
		return guessedCorrect;
	}
	public abstract Solution createSuggestion(ArrayList<Card> deck, Room room);
	
	public abstract BoardCell selectTargets(Set<BoardCell> targets);

}
