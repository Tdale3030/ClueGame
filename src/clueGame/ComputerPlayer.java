package clueGame;

import java.awt.Color;
import java.util.ArrayList;

public class ComputerPlayer extends Player {
	
	private String name;
	private String color;
	private int row;
	private int col;
	
	public ComputerPlayer(String name, String color, int row, int col) {
		super(name, color, row, col);
		// TODO Auto-generated constructor stub
		this.name=name;
		this.color=color;
		this.row=row;
		this.col=col;
	
	}

	@Override
	public void updateHand(Card card) {
		
		
		
	}

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

	@Override
	public int[] getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Card> getHand() {
		// TODO Auto-generated method stub
		return null;
	} 
	
	
	
	

}
