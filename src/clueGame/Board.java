package clueGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
//AUTHORS:Brody Clark and Tyner Dale
public class Board {
	private static Board theInstance = new Board();
	private int numRows, numColumns;
	private String layoutConfiFile, setupConfigFile;
	private Map<Character, Room> roomMap;
	private BoardCell[][] grid;
	/*
	 * variable and methods used for singleton pattern
	 */
	// constructor is private to ensure only one can be created
	private Board() {
		super() ;
		roomMap = new HashMap<Character, Room>();
	}
	// this method returns the only Board
	public static Board getInstance() {
		return theInstance;
	}
	/*
	 * initialize the board (since we are using singleton pattern)
	 */
	public void initialize() throws BadConfigFormatException, FileNotFoundException{
		initializeTryCatch();
		
	}

	public void loadSetupConfig() throws BadConfigFormatException {
		loadSetUpConfigTryCatch();


	}
	
	public void setConfigFiles(String layoutConfiFile, String setupConfigFile) {
       this.layoutConfiFile = layoutConfiFile;
       this.setupConfigFile = setupConfigFile;
    }
	public void loadLayoutConfig() {
		loadLayoutConfigTryCatch();
		
	
		
	}
	private void initializeTryCatch() {
		try {
			loadSetupConfig();
			loadLayoutConfig();
		}catch(BadConfigFormatException exp){
			
		}
	}
	private void loadSetUpConfigTryCatch() throws BadConfigFormatException {
		try {
			//reads in Layout.txt file//
			FileReader read = new FileReader(setupConfigFile);
			Scanner input = new Scanner(read);
			while(input.hasNextLine()) {
				String components = input.nextLine();
				if(components.charAt(0) == '/') {
					continue;
				}
				
				String[] list = components.split(",");
				for (int i = 0; i < list.length; i++) {
					list[i] = list[i].trim();
				}
				
				if(list[0].equals("Room") || list[0].equals("Space")) {
					roomMap.put(list[2].charAt(0), new Room(list[1]));
				}
				else {
					throw new BadConfigFormatException("Bad Exception");
				}
				
			}
			input.close();
		}catch(FileNotFoundException exp){
			System.out.println("File cannot open ");

		}
	}
	private void loadLayoutConfigTryCatch() {
		try {
			ArrayList<String> list = new ArrayList<String>();
			 File file = new File(layoutConfiFile);
	         Scanner input = new Scanner(file);
	         
	         while (input.hasNext()) {
	        	
	        	 list.add(input.nextLine());
	        
	        	 
	         }
	        
	         input.close();
	         
	        numRows=list.size();
	        String[] list1 = list.get(0).split(",");
	        numColumns = list1.length;
	        
	        
	        //Creates a grid and then runs a for loop through the entire Array creating locations for the each space on the board
	        grid = new BoardCell[numRows][numColumns];
	        for(int i = 0; i<list.size(); i++) {
	        	String[] list2 = list.get(i).split(",");
	        	for(int j = 0; j<list2.length;j++) {
	        		BoardCell BoardCell2 = new BoardCell(i, j);
	        		BoardCell2.setInitial(list2[j].charAt(0));
	        		if(list2[j].length() == 2) {
	        			
	        			if(list2[j].charAt(1)== '*') {
	        				BoardCell2.setRoomCenter(true);//setting room location to center of the room
	        				roomMap.get(list2[j].charAt(0)).setCenterCell(BoardCell2);
	        			}
	        			if(list2[j].charAt(1) == '#') {
	        				BoardCell2.setRoomLabel(true);//Sets the room label
	        				roomMap.get(list2[j].charAt(0)).setLabelCell(BoardCell2);
	        			}
	        			if(list2[j].charAt(1) == '^') {
	        				BoardCell2.setDoorDirection(DoorDirection.UP);//sets door direction
	        				BoardCell2.setDoorway(true);//says that theres a doorway at this location
	        				
	        			}
	        			if(list2[j].charAt(1) == 'v') {
	        				BoardCell2.setDoorDirection(DoorDirection.DOWN);//sets door direction
	        				BoardCell2.setDoorway(true);//says that theres a doorway at this location
	        				
	        			}
	        			if(list2[j].charAt(1) == '<') {
	        				BoardCell2.setDoorDirection(DoorDirection.LEFT);//sets door direction
	        				BoardCell2.setDoorway(true);//says that theres a doorway at this location
	        				
	        			}
	        			if(list2[j].charAt(1) == '>') {
	        				BoardCell2.setDoorDirection(DoorDirection.RIGHT);//sets door direction
	        				BoardCell2.setDoorway(true);//says that theres a doorway at this location
	        				
	        			}
	        			else {
	        				BoardCell2.setSecretPassage(list2[j].charAt(1));
	        			}
	        			
	        		}
	        		grid[i][j] = BoardCell2;//Sets all values of temp equal to the location on the grid
	        		
	        	}
	        	
	        }
	       	
	        
		}catch(FileNotFoundException exp) {
			System.out.println("File cannot open ");//File was unable to open
		}
	}
	
	public Room getRoom(char symbol) {
        return roomMap.get(symbol);
    }
    public Room getRoom(BoardCell cell) {
        return roomMap.get(cell.getInitial());
    }
    public BoardCell getCell(int row, int col) {
        return grid[row][col];//returns the value of the cell
    }
	public int getNumRows() {
		
		return numRows;
	}
	public int getNumColumns() {
		
		return numColumns;
	}
	public Set<BoardCell> getAdjList(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}
	public void calcTargets(BoardCell cell, int i) {
		// TODO Auto-generated method stub
		
	}
	public Set<BoardCell> getTargets() {
		// TODO Auto-generated method stub
		return null;
	}

}
