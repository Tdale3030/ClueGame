package clueGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Room {
	private String name;
	BoardCell CenterCell;
	BoardCell LabelCell;
	private int width;
	private int height;
	public Room(String name) {
		this.name = name; 
	}
	
	public String getName() {
		return name;
	}

	public BoardCell getCenterCell() {
		return CenterCell;
	}
	public void setCenterCell(BoardCell centerCell) {
		this.CenterCell = centerCell;
	}
	public BoardCell getLabelCell() {
		return LabelCell;
	}
	public void setLabelCell(BoardCell labelCell) {
		this.LabelCell = labelCell;
	}
	public void drawRoom(Graphics graphics){
		int size = 0;
		
		if(height > width) 
		{
			size = width;
		}
		else if(width > height) 
		{
			size = height;
		}

		graphics.setFont(new Font("Arial", Font.BOLD, size / 2));										//creating the correct writing for the room labels
		graphics.setColor(Color.BLUE);
		graphics.drawString(name,size * LabelCell.getCol(),size * LabelCell.getRow()); 



	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}


}
