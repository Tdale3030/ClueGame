package clueGame;

import java.util.Map;

public class Board {
	private static Board theInstance = new Board();
	private int numRows, numColumns;
	private String layoutConfiFile, setupConfigFile;
	private Map<Character, Room> roomMap;

	/*
	 * variable and methods used for singleton pattern
	 */
	// constructor is private to ensure only one can be created
	private Board() {
		super() ;
	}
	// this method returns the only Board
	public static Board getInstance() {
		return theInstance;
	}
	/*
	 * initialize the board (since we are using singleton pattern)
	 */
	public void initialize(){

	}

	public void loadConfigFiles() {

	}
	public void loadSetupConfig() {

	}
	public void setConfigFiles(String one, String two) {
       
    }
	public void loadLayoutConfig() {
		
	}
	
	public Room getRoom(char symbol) {
        return new Room();
    }
    public Room getRoom(BoardCell cell) {
        return new Room();
    }
    public BoardCell getCell(int row, int col) {
        return new BoardCell();
    }
	public int getNumRows() {
		// TODO Auto-generated method stub
		return 0;
	}
	public int getNumColumns() {
		// TODO Auto-generated method stub
		return 0;
	}

}
