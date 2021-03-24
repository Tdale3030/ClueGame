package clueGame;

import java.awt.Color;
import java.util.ArrayList;

public abstract class Player {
	
	private String name;
	private Color color;
	protected int row;
	protected int col;
	private int[] location;
	private ArrayList<Card> hand;
	
	
	public Player(String name, String color, int row, int col) {
		super();
		this.name = name;
	
		
		
		if(color.equals("Blue")) {
			this.color=Color.blue;
			
		}
		if(color.equals("Red")) {
			this.color=Color.red;
			
		}
		if(color.equals("Green")) {
			this.color=Color.green;
			
		}
		if(color.equals("Orange")) {
			this.color=Color.orange;
			
		}
		if(color.equals("Purple")) {
			this.color=Color.magenta;
			
		}
		if(color.equals("Yellow")) {
			this.color=Color.yellow;
			
		}

		this.row=row;
		this.col=col;
		this.location= new int[2];
		this.location[0]=this.row;
		this.location[1]=this.col;
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
		
		return null;
	}
	

}
