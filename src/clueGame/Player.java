package clueGame;

import java.awt.Color;
import java.util.ArrayList;

public abstract class Player {
	
	private String name;
	private String color;
	protected int row;
	protected int col;
	
	
	public Player(String name, String color, int row, int col) {
		super();
		this.name = name;
		this.color = color;
		this.row = row;
		this.col = col;
	}
	
	
	public abstract void updateHand(Card card);
	

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Color getColor() {
		return Color.RED;
	}



	public void setColor(String color) {
		
		this.color = color;
	}



	public int getRow() {
		return row;
	}



	public void setRow(int row) {
		this.row = row;
	}



	public int getCol() {
		return col;
	}


	public void setCol(int col) {
		this.col = col;
	}


	public abstract int[] getLocation();


	public abstract ArrayList<Card> getHand();
	

}
