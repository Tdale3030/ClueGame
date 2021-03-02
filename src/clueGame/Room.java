package clueGame;

public class Room {
	private String name;
	BoardCell CenterCell;
	BoardCell LabelCell;
	public Room() {

	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BoardCell getCenterCell() {
		return CenterCell;
	}
	public void setCenterCell(BoardCell centerCell) {
		CenterCell = centerCell;
	}
	public BoardCell getLabelCell() {
		return LabelCell;
	}
	public void setLabelCell(BoardCell labelCell) {
		LabelCell = labelCell;
	}

}
