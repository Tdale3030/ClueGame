package clueGame;

public class Room {
	private String name;
	BoardCell CenterCell;
	BoardCell LabelCell;
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

}
