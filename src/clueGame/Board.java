package clueGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import experiment.TestBoardCell;
//AUTHORS:Brody Clark and Tyner Dale
public class Board {
	private static Board theInstance = new Board();
	private int numRows, numColumns;
	private String layoutConfiFile, setupConfigFile;
	private Map<Character, Room> roomMap;
	private BoardCell[][] gridBoard;
	Set<BoardCell> adjList;
	private Set<BoardCell> pathTargets;
	private Set<BoardCell> visited;
	private ArrayList<Player> players;
	private ArrayList<Card> deck;
	private Solution theAnswer;
	private ArrayList<Card> usedCards;
	/*
	 * variable and methods used for singleton pattern
	 */
	// constructor is private to ensure only one can be created
	
	
	private Board() {
		super() ;
		
		roomMap = new HashMap<Character, Room>();
		this.adjList = new HashSet<BoardCell>();
		this.gridBoard=new BoardCell[numRows][numColumns];
		
		boardCreation();
		
		this.adjList = new HashSet<BoardCell>();
		this.pathTargets = new HashSet<BoardCell>();
		this.visited = new HashSet<BoardCell>();
		this.deck = new ArrayList<Card>();
		this.players = new ArrayList<Player>();
	}


	private void boardCreation() {
		for(int i=0;i<numRows;i++) 
		{
			for(int j=0;j<numColumns;j++) 
			{
				gridBoard[i][j] = new BoardCell(i, j);
			}
		}
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
		//deal();
		allAdj();
																		//refactoring
	}
	
	private void initializeTryCatch() {
		
		try {
			
			loadSetupConfig();
			loadLayoutConfig();
			
		}catch(BadConfigFormatException exp){
			
			System.out.println("Bad exception");
		}
	}

	
	public void loadSetupConfig() throws BadConfigFormatException {
		
		loadSetUpConfigTryCatch();										//refactoring

	}
	
	private void loadSetUpConfigTryCatch() throws BadConfigFormatException {
		
		
		try {
			
			//reads in Layout.txt file//
			FileReader read = new FileReader(setupConfigFile);				//opening file
			Scanner input = new Scanner(read);
			
			while(input.hasNextLine()) 
			{
				String components = input.nextLine();
				if(components.charAt(0) == '/') {							//if statements for comments in file
					
					continue;
				}
				
				String[] list = components.split(",");
				
				for (int i = 0; i < list.length; i++) 
				{					//trimming the file
					list[i] = list[i].trim();
				}
				if(list[0].equals("Character")) 
				{
					if(list[3].equals("Human")) 
					{
						players.add(new HumanPlayer(list[1],list[2],Integer.parseInt(list[4]),Integer.parseInt(list[5])));		//adds to human player arrayList
						deck.add(new Card(list[1]));									//adds human player to deck
					}
					else if(list[3].equals("Computer"))
					{
						players.add(new ComputerPlayer(list[1],list[2],Integer.parseInt(list[4]),Integer.parseInt(list[5])));	//adds to computer plan arrayList
						deck.add(new Card(list[1]));									//adds computer player to deck
					}
					continue;
				}
				else if (list[0].equals("Weapon")) {
					deck.add(new Card(list[1]));
					continue;//adds weapons to deck
				}
				if(list[0].equals("Room") || list[0].equals("Space")) 
				{		//making the map correctly
					roomMap.put(list[2].charAt(0), new Room(list[1]));
					if ( list[0].equals("Room") ) {
						deck.add(new Card(list[1]));
					}
				}
				
				else {
					throw new BadConfigFormatException("Bad Exception");
				}
				
			}
			
			input.close();													//close file
		
		}catch(FileNotFoundException exp){
			System.out.println("File cannot open ");						//file cannot open thrown exception

		}
	}
	
	
	public void loadLayoutConfig() throws BadConfigFormatException {
		
		loadLayoutConfigTryCatch();
																			//refactoring
	}
	
	private void loadLayoutConfigTryCatch() throws BadConfigFormatException {
		
		try {
			
			ArrayList<String> list = new ArrayList<String>();				//creates new array
			 
			File file = new File(layoutConfiFile);							//reads file
	        Scanner input = new Scanner(file);
	         
	         nextLine(list, input);
	        
	         input.close();													//closes file
	         
	         
	        numRows=list.size();											//rows size
	        String[] list1 = list.get(0).split(",");
	        numColumns = list1.length;										//finding column size
	        
	        for (String i: list) 
	        {
	        	 String[] temp = i.split(",");
	        	 
	        	 if (temp.length != numColumns) 
	        	 {					//for loop for throw exception
	        		 throw new BadConfigFormatException("Wrong number of columns.");
	        	 }
	         }
	        
	        //Creates a grid and then runs a for loop through the entire Array creating locations for the each space on the board
	        gridBoard = new BoardCell[numRows][numColumns];
	        
	        for(int i = 0; i<list.size(); i++) 
	        {
	        	String[] list2 = list.get(i).split(",");
	        	
	        	for(int j = 0; j<list2.length;j++) 
	        	{
	        		BoardCell BoardCell2 = new BoardCell(i, j);
	        		
	        		BoardCell2.setInitial(list2[j].charAt(0));
	        		
	        		ifStatementsForDoorway(list2, j, BoardCell2);
	        		
	        		gridBoard[i][j] = BoardCell2;//Sets all values of temp equal to the location on the grid
	        		
	        	}
	        	
	        }
	       	
	        
		}catch(FileNotFoundException exp) 
		{
			System.out.println("File cannot open ");//File was unable to open
		}
	}


	private void nextLine(ArrayList<String> list, Scanner input) throws BadConfigFormatException {
		while (input.hasNext()) 
		 {								//while loop
			 String next = input.nextLine();
			 list.add(next);
			 String[] badRoom = next.split(", ");
			 
			 for(int i = 0; i<badRoom.length; i++) 
			 {
				if(!roomMap.containsKey(badRoom[i].charAt(0))) 
				{
					throw new BadConfigFormatException("Bad Room");
				}
			 }
		 }
	}


	private void ifStatementsForDoorway(String[] list2, int j, BoardCell BoardCell2) {
		if(list2[j].length() == 2) 
		{
			if(list2[j].charAt(1)== '*') 
			{
				BoardCell2.setRoomCenter(true);//setting room location to center of the room
				roomMap.get(list2[j].charAt(0)).setCenterCell(BoardCell2);
				
			}else if(list2[j].charAt(1) == '#') 
			{
				BoardCell2.setRoomLabel(true);//Sets the room label
				roomMap.get(list2[j].charAt(0)).setLabelCell(BoardCell2);
			}else if(list2[j].charAt(1) == '^') 
			{
				BoardCell2.setDoorDirection(DoorDirection.UP);//sets door direction
				setDoorwayTrue(BoardCell2);//says that theres a doorway at this location
				
			}else if(list2[j].charAt(1) == 'v') 
			{
				BoardCell2.setDoorDirection(DoorDirection.DOWN);//sets door direction
				setDoorwayTrue(BoardCell2);
				
			}else if(list2[j].charAt(1) == '<') 
			{
				BoardCell2.setDoorDirection(DoorDirection.LEFT);//sets door direction
				setDoorwayTrue(BoardCell2);
				
			}else if(list2[j].charAt(1) == '>') 
			{
				BoardCell2.setDoorDirection(DoorDirection.RIGHT);//sets door direction
				setDoorwayTrue(BoardCell2);
				
			}else {
				BoardCell2.setSecretPassage(list2[j].charAt(1));
			}
		}
	}


	private void setDoorwayTrue(BoardCell BoardCell2) {
		BoardCell2.setDoorway(true);
	}
	
	
	public void findAllTargets(BoardCell cell, int numSteps) 
	{
		adjList = cell.getAdjList();
		
		addingTo(numSteps);
		
	}


	private void addingTo(int numSteps) {
		for(BoardCell i: adjList) 
		{								//for loop for adjlist
			if(visited.contains(i)) 
			{							//see if visited
				continue;
			}
			if((i.getOccupied())) 
			{								//if it is occupied set room center to false
				if(i.isRoomCenter()) 
				{
					i.setOccupied(false);
					
				} else {
					continue;
				}
				
			}if(i.isRoomCenter()) 
			{								//add if room center
				pathTargets.add(i);
				continue;
			}
			visited.add(i);	
										//if visited add
			if (numSteps == 1) 
			{					
				pathTargets.add(i);
			}else {
				findAllTargets(i, numSteps - 1);
			}
			
			visited.remove(i);
		}
	}
	
	public void allAdj() {
		
		for(int i=0;i<numRows;i++) 
		{							//for loop for size
			for(int j=0;j<numColumns;j++) 
			{
				if(gridBoard[i][j].isRoomCenter()) 
				{					//for loop for if room center is true
					secretPassageForLoops();	
					
					findingDoorDirection(i, j);		//refactored
					continue;
				}
				
				isDoorwayRefactored(i, j);		//refactored
			
				getInitialAdding(i, j);		//refactored
				
			}
		}
	}


	private void secretPassageForLoops() {
		for(int a = 0; a<numRows; a++) 
		{
			for (int b = 0; b<numColumns; b++) 
			{
				if(gridBoard[a][b].getSecretPassage() != ' ') 
				{		//adding to grid for secret passage
					BoardCell one = roomMap.get(gridBoard[a][b].getSecretPassage()).getCenterCell();
					BoardCell two = roomMap.get(gridBoard[a][b].getInitial()).getCenterCell();
					
					one.addAdj(two);
					
				}
			}
		}
	}
	
	
	private void findingDoorDirection(int i, int j) 
	{
		for(int a = 0; a<numRows; a++) 
		{
			for (int b = 0; b<numColumns; b++)
			{			//see the door direction
				
				ifStatementsForDoorDir(i, j, a, b);
			}
		}
	}


	private void ifStatementsForDoorDir(int i, int j, int a, int b) {
		if(gridBoard[a][b].isDoorway()) 
		{
			if(gridBoard[a][b].getDoorDirection() == DoorDirection.UP)
			{																		//for up
				if(gridBoard[a-1][b].getInitial() == gridBoard[i][j].getInitial()) 
				{
					addToAdj(i, j, a, b);	//adds to list 
				}
				
			}if(gridBoard[a][b].getDoorDirection() == DoorDirection.DOWN)
			{																		//for down
				if(gridBoard[a+1][b].getInitial() == gridBoard[i][j].getInitial()) 
				{
					addToAdj(i, j, a, b);
				}
				
			}if(gridBoard[a][b].getDoorDirection() == DoorDirection.RIGHT)
			{																		//for right
				if(gridBoard[a][b+1].getInitial() == gridBoard[i][j].getInitial()) 
				{
					addToAdj(i, j, a, b);
				}
				
			}if(gridBoard[a][b].getDoorDirection() == DoorDirection.LEFT)
			{																		//for left
				if(gridBoard[a][b-1].getInitial() == gridBoard[i][j].getInitial()) 
				{
					addToAdj(i, j, a, b);
				}
			}
		}
	}


	private void addToAdj(int i, int j, int a, int b) {
		gridBoard[i][j].addAdjacency(gridBoard[a][b]);
	}
	
	
	private void isDoorwayRefactored(int i, int j) 
	{
		if(gridBoard[i][j].isDoorway()) 
		{
			DoorDirection dir = DoorDirection.NONE;
					
			dir = gridBoard[i][j].getDoorDirection();
			
			if(dir==DoorDirection.UP) 
			{		//finds door direction if up
				BoardCell one = roomMap.get(gridBoard[i-1][j].getInitial()).getCenterCell();
				
				gridBoard[i][j].addAdj(one);
				
			
			}else if(dir==DoorDirection.DOWN) 
			{		//finds door direction if down
				BoardCell one = roomMap.get(gridBoard[i+1][j].getInitial()).getCenterCell();
				
				gridBoard[i][j].addAdj(one);
				
			
			}else if(dir==DoorDirection.LEFT) 
			{		//finds door direction if left
				BoardCell one = roomMap.get(gridBoard[i][j-1].getInitial()).getCenterCell();
				
				gridBoard[i][j].addAdj(one);
				
			
			}else if(dir==DoorDirection.RIGHT) 
			{		//finds door direction if right
				BoardCell one = roomMap.get(gridBoard[i][j+1].getInitial()).getCenterCell();
				
				gridBoard[i][j].addAdj(one);
				
			}
		}
	}
	
	
	private void getInitialAdding(int i, int j) 
	{
		if ((i-1) >= 0) 
		{
			if(gridBoard[i][j].getInitial() == gridBoard[i-1][j].getInitial()) 
			{			//makes sure the user is inside the grid
				gridBoard[i][j].addAdjacency(gridBoard[i-1][j]);
			}
			
		}if ((i+1) <= numRows-1) 
		{			//makes sure the user is inside the grid
			if(gridBoard[i][j].getInitial() == gridBoard[i+1][j].getInitial()) 
			{
				gridBoard[i][j].addAdjacency(gridBoard[i+1][j]);
			}

		}if ((j-1) >= 0) 
		{					//makes sure the user is inside the grid
			if(gridBoard[i][j].getInitial() == gridBoard[i][j-1].getInitial())
			{
				gridBoard[i][j].addAdjacency(gridBoard[i][j-1]);
			}

		}if ((j+1) <= numColumns-1) 
		{				//makes sure the user is inside the grid
			if(gridBoard[i][j].getInitial() == gridBoard[i][j+1].getInitial()) 
			{
				gridBoard[i][j].addAdjacency(gridBoard[i][j+1]);
			}

		}
	}
	
	
	public void setConfigFiles(String layoutConfiFile, String setupConfigFile) {
		
	       this.layoutConfiFile = layoutConfiFile;
	       this.setupConfigFile = setupConfigFile;
	    }
	
	
	public void calcTargets( BoardCell startCell, int pathlength) 
	{
		visited = new HashSet<BoardCell>();
		pathTargets = new HashSet<BoardCell>();
		
		visited.add(startCell);
		
		findAllTargets(startCell, pathlength);
	}
	
	
	public Set<BoardCell> getTargets() 
	{
		return pathTargets;
	}
	
	
	
	public Room getRoom(char symbol) 
	{
        return roomMap.get(symbol);
    }
	
	
    public Room getRoom(BoardCell cell) 
    {
        return roomMap.get(cell.getInitial());
    }
    
    
    public BoardCell getCell(int row, int col) 
    {
        return gridBoard[row][col];//returns the value of the cell
    }
    
    
	public int getNumRows() {
		
		return numRows;
	}
	
	
	public int getNumColumns() {
		
		return numColumns;
	}
	
	
	public Set<BoardCell> getAdjList(int i, int j) 
	{
		return gridBoard[i][j].getAdjList();								//return adjlist
	}

	
	public void deal() {
		
	
	}



	public Solution getSolution() {
		// TODO Auto-generated method stub
		return theAnswer;
	}


	public ArrayList<Card> getDeck() {
		// TODO Auto-generated method stub
		return deck;
	}
	
	public ArrayList<Player> getPlayerList() {
		return players;
		
	}
	public Player getPlayer(String player) {
		/*
		for(int i=0;i<6;i++) {
			if(players.get(i).equals(player)) {
				return players.get(i);
			}
			
		}
		*/
		return new ComputerPlayer("h", "h", 0, 0);
		
	}


	public ArrayList<Card> getUsedCards() {
		
		return usedCards;
		
		
		
	}





	public Object getCard(String string, CardType person) {
		// TODO Auto-generated method stub
		Card card=new Card(string);
		return card;
	}
	
	
}
