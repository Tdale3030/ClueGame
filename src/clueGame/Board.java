package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.sun.tools.javac.Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import experiment.TestBoardCell;
//AUTHORS:Brody Clark and Tyner Dale
public class Board extends JPanel implements MouseListener{

	private static Board theInstance = new Board();
	private int numRows, numColumns;
	private String layoutConfiFile, setupConfigFile;
	private Map<Character, Room> roomMap;
	private BoardCell[][] gridBoard;
	Set<BoardCell> adjList;
	private Set<BoardCell> pathTargets;
	private Set<BoardCell> visited;
	private static ArrayList<Player> players;
	private static ArrayList<Card> deck;
	private Solution theAnswer;
	private ArrayList<Card> usedCards;
	private int playerTurn=0;
	private int pathlength;
	private BoardCell startCell;
	private String filePath;
	private static boolean moved;
	private JDialog suggestion;
	private String guessWeapon;
	private String guessPerson;
	private String guessRoom;
	private boolean submitPressed=false;
	private GameControlPanel gameControlPanel;
	private clueCardsGUI ClueCardsGUI;



	public Board() {
		super() ;

		roomMap = new HashMap<Character, Room>();
		this.adjList = new HashSet<BoardCell>();
		this.gridBoard=new BoardCell[numRows][numColumns];

		boardCreation();

		setBackground(Color.BLACK);																			//sets background to black twice

	}

	public int roll() {
																											//finds a random number between 1 and 6 for the roll
		Random rand = new Random();
		int randomNum = rand.nextInt( (6 - 1) + 1 ) + 1;

		return randomNum;																					//returns the random number
	}

	public void paintComponent(Graphics graphics) {

		super.paintComponent(graphics);																		//sets background to black
		setBackground(Color.BLACK);

		for(int i = 0; i < numRows; i++) 																	//nested for loops for the grid
		{
			for(int j = 0; j < numColumns;j++) 
			{
				gridBoard[i][j].setWidth(getWidth() / numRows);												//drawing the grid onto the black
				gridBoard[i][j].setHeight(getHeight() / numColumns);
				gridBoard[i][j].draw(graphics);
			}

		}
		for(int i = 0; i < numRows; i++) 																	//nested for loops for the grid
		{
			for(int j = 0; j < numColumns;j++) 
			{
				gridBoard[i][j].setWidth(getWidth() / numRows);												//drawing the grid onto the black
				gridBoard[i][j].setHeight(getHeight() / numColumns);
				gridBoard[i][j].drawDoorway(graphics);
			}

		}

		for(int i = 0; i < numRows; i++) 
		{
			for(int j = 0; j < numColumns;j++) 
			{
				if (gridBoard[i][j].isLabel()) {
					roomMap.get(gridBoard[i][j].getInitial()).setWidth(getWidth() / numRows);					//drawing the room names
					roomMap.get(gridBoard[i][j].getInitial()).setHeight(getHeight() / numColumns);;
					roomMap.get(gridBoard[i][j].getInitial()).drawRoom(graphics);
				}
			}

		}
		int z = 0;
		for (int i = 0; i < players.size(); i++) {
			players.get(i).setPos(z);
			z++;
		}

		for(int i = 0; i < players.size(); i++) 
		{
			players.get(i).setWidth(getWidth() / numRows);															//drawing the players as circles
			players.get(i).setHeight(getHeight() / numColumns);
			players.get(i).draw(graphics);
		}
		repaint();																									//repaints
	}

	private void boardCreation() {

		for(int i=0;i<numRows;i++) 																					//nested for loop to find rows and cols
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

		initializeTryCatch();																						//refactored
		allAdj();
		deal();
		addMouseListener(this);
		/*
		try
		{
			filePath = "data/starwars.wav";																			//adding music in
			SimpleAudioPlayer audioPlayer = 
					new SimpleAudioPlayer(filePath);

			audioPlayer.play();

		} 

		catch (Exception ex) 
		{
			System.out.println("Error with playing sound.");														//exits if music failed
			ex.printStackTrace();

		}*/
		//refactoring
	}

	private void initializeTryCatch() {

		try {

			loadSetupConfig();																						//try and catch for refactored
			loadLayoutConfig();

		}catch(BadConfigFormatException exp){

			System.out.println("Bad exception");
		}
	}


	public void loadSetupConfig() throws BadConfigFormatException {

		this.usedCards= new ArrayList<Card>();
		this.deck = new ArrayList<Card>();
		this.players = new ArrayList<Player>();
		this.adjList = new HashSet<BoardCell>();
		this.pathTargets = new HashSet<BoardCell>();
		this.visited = new HashSet<BoardCell>();
		this.players = new ArrayList<Player>();
		this.theAnswer = new Solution();

		loadSetUpConfigTryCatch();																					//refactoring

	}

	private void loadSetUpConfigTryCatch() throws BadConfigFormatException {

		try {

																													//reads in Layout.txt file//
			FileReader read = new FileReader(setupConfigFile);														//opening file
			Scanner input = new Scanner(read);

			while(input.hasNextLine()) 
			{
				String components = input.nextLine();
				if(components.charAt(0) == '/') {																	//if statements for comments in file

					continue;
				}

				String[] list = components.split(",");

				for (int i = 0; i < list.length; i++) 
				{																									//trimming the file
					list[i] = list[i].trim();	
				}

				if(list[0].equals("Character")) 
				{
					if(list[3].equals("Human")) 
					{
						players.add(new HumanPlayer(list[1],list[2],Integer.parseInt(list[4]),Integer.parseInt(list[5])));		//adds to human player arrayList
						deck.add(new Card(list[1], list[0]));	
																																//adds human player to deck
					}else if(list[3].equals("Computer"))
					{
						players.add(new ComputerPlayer(list[1],list[2],Integer.parseInt(list[4]),Integer.parseInt(list[5])));	//adds to computer plan arrayList
						deck.add(new Card(list[1], list[0]));																	//adds computer player to deck
					}

					continue;

				}else if (list[0].equals("Weapon")) 
				{
					deck.add(new Card(list[1], list[0]));
					continue;																									//adds weapons to deck

				}if(list[0].equals("Room") || list[0].equals("Space")) 
				{																												//making the map correctly
					roomMap.put(list[2].charAt(0), new Room(list[1]));
					if ( list[0].equals("Room") ) {
						deck.add(new Card(list[1], list[0]));
					}
				}else {
					throw new BadConfigFormatException("Bad Exception");
				}

			}

			input.close();																							//close file

		}catch(FileNotFoundException exp)
		{
			System.out.println("File cannot open ");																//file cannot open thrown exception

		}
	}


	public void loadLayoutConfig() throws BadConfigFormatException {

		loadLayoutConfigTryCatch();
																													//refactoring
	}

	private void loadLayoutConfigTryCatch() throws BadConfigFormatException {

		try {

			ArrayList<String> list = new ArrayList<String>();														//creates new array

			File file = new File(layoutConfiFile);																	//reads file
			Scanner input = new Scanner(file);

			nextLine(list, input);

			input.close();																							//closes file


			numRows=list.size();																					//rows size
			String[] list1 = list.get(0).split(",");
			numColumns = list1.length;																				//finding column size

			for (String i: list) 
			{
				String[] temp = i.split(",");

				if (temp.length != numColumns) 
				{																									//for loop for throw exception
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

					gridBoard[i][j] = BoardCell2;																	//Sets all values of temp equal to the location on the grid

				}

			}


		}catch(FileNotFoundException exp) 
		{
			System.out.println("File cannot open ");																//File was unable to open
		}
	}


	private void nextLine(ArrayList<String> list, Scanner input) throws BadConfigFormatException {

		while (input.hasNext()) 
		{																											//while loop
			String next = input.nextLine();
			list.add(next);
			String[] badRoom = next.split(", ");

			for(int i = 0; i<badRoom.length; i++) 
			{
				if(!roomMap.containsKey(badRoom[i].charAt(0))) 														//finds bad room
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
				BoardCell2.setRoomCenter(true);																		//setting room location to center of the room
				roomMap.get(list2[j].charAt(0)).setCenterCell(BoardCell2);

			}else if(list2[j].charAt(1) == '#') 
			{
				BoardCell2.setRoomLabel(true);																		//Sets the room label
				roomMap.get(list2[j].charAt(0)).setLabelCell(BoardCell2);
			}else if(list2[j].charAt(1) == '^') 
			{
				BoardCell2.setDoorDirection(DoorDirection.UP);														//sets door direction
				setDoorwayTrue(BoardCell2);																			//says that theres a doorway at this location

			}else if(list2[j].charAt(1) == 'v') 
			{
				BoardCell2.setDoorDirection(DoorDirection.DOWN);													//sets door direction
				setDoorwayTrue(BoardCell2);

			}else if(list2[j].charAt(1) == '<') 
			{
				BoardCell2.setDoorDirection(DoorDirection.LEFT);													//sets door direction
				setDoorwayTrue(BoardCell2);

			}else if(list2[j].charAt(1) == '>') 
			{
				BoardCell2.setDoorDirection(DoorDirection.RIGHT);													//sets door direction
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
		{																											//for loop for adjlist
			if(visited.contains(i)) 
			{																										//see if visited
				continue;
			}
			if((i.getOccupied())) 
			{																										//if it is occupied set room center to false
				if(i.isRoomCenter()) 
				{
					i.setOccupied(false);

				} else {
					continue;
				}

			}if(i.isRoomCenter()) 
			{																										//add if room center
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
		{																											//for loop for size
			for(int j=0;j<numColumns;j++) 
			{
				if(gridBoard[i][j].isRoomCenter()) 
				{																									//for loop for if room center is true
					secretPassageForLoops();	

					findingDoorDirection(i, j);																		//refactored
					continue;
				}

				isDoorwayRefactored(i, j);																			//refactored

				getInitialAdding(i, j);																				//refactored

			}
		}
	}


	private void secretPassageForLoops() {

		for(int a = 0; a<numRows; a++) 
		{
			for (int b = 0; b<numColumns; b++) 
			{
				if(gridBoard[a][b].getSecretPassage() != ' ') 
				{																									//adding to grid for secret passage
					BoardCell one = roomMap.get(gridBoard[a][b].getSecretPassage()).getCenterCell();
					BoardCell two = roomMap.get(gridBoard[a][b].getInitial()).getCenterCell();

					one.addAdj(two);

				}
			}
		}
	}


	private void findingDoorDirection(int i, int j) {

		for(int a = 0; a<numRows; a++) 
		{
			for (int b = 0; b<numColumns; b++)
			{																										//see the door direction

				ifStatementsForDoorDir(i, j, a, b);
			}
		}
	}


	private void ifStatementsForDoorDir(int i, int j, int a, int b) {

		if(gridBoard[a][b].isDoorway()) 
		{
			if(gridBoard[a][b].getDoorDirection() == DoorDirection.UP)
			{																										//for up
				if(gridBoard[a-1][b].getInitial() == gridBoard[i][j].getInitial()) 
				{
					addToAdj(i, j, a, b);																			//adds to list 
				}

			}if(gridBoard[a][b].getDoorDirection() == DoorDirection.DOWN)
			{																										//for down
				if(gridBoard[a+1][b].getInitial() == gridBoard[i][j].getInitial()) 
				{
					addToAdj(i, j, a, b);
				}			

			}if(gridBoard[a][b].getDoorDirection() == DoorDirection.RIGHT)
			{																										//for right
				if(gridBoard[a][b+1].getInitial() == gridBoard[i][j].getInitial()) 
				{
					addToAdj(i, j, a, b);
				}

			}if(gridBoard[a][b].getDoorDirection() == DoorDirection.LEFT)
			{																										//for left
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


	private void isDoorwayRefactored(int i, int j) {

		if(gridBoard[i][j].isDoorway()) 				
		{
			DoorDirection dir = DoorDirection.NONE;

			dir = gridBoard[i][j].getDoorDirection();

			if(dir==DoorDirection.UP) 
			{																										//finds door direction if up
				BoardCell one = roomMap.get(gridBoard[i-1][j].getInitial()).getCenterCell();

				gridBoard[i][j].addAdj(one);


			}else if(dir==DoorDirection.DOWN) 
			{																										//finds door direction if down
				BoardCell one = roomMap.get(gridBoard[i+1][j].getInitial()).getCenterCell();

				gridBoard[i][j].addAdj(one);


			}else if(dir==DoorDirection.LEFT) 
			{																										//finds door direction if left
				BoardCell one = roomMap.get(gridBoard[i][j-1].getInitial()).getCenterCell();

				gridBoard[i][j].addAdj(one);


			}else if(dir==DoorDirection.RIGHT) 
			{																										//finds door direction if right
				BoardCell one = roomMap.get(gridBoard[i][j+1].getInitial()).getCenterCell();

				gridBoard[i][j].addAdj(one);

			}
		}
	}


	private void getInitialAdding(int i, int j) {

		if ((i-1) >= 0) 
		{
			if(gridBoard[i][j].getInitial() == gridBoard[i-1][j].getInitial()) 
			{																										//makes sure the user is inside the grid
				gridBoard[i][j].addAdjacency(gridBoard[i-1][j]);
			}

		}if ((i+1) <= numRows-1) 
		{																											//makes sure the user is inside the grid
			if(gridBoard[i][j].getInitial() == gridBoard[i+1][j].getInitial()) 
			{
				gridBoard[i][j].addAdjacency(gridBoard[i+1][j]);
			}

		}if ((j-1) >= 0) 
		{																											//makes sure the user is inside the grid
			if(gridBoard[i][j].getInitial() == gridBoard[i][j-1].getInitial())
			{
				gridBoard[i][j].addAdjacency(gridBoard[i][j-1]);
			}

		}if ((j+1) <= numColumns-1) 
		{																											//makes sure the user is inside the grid
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


	public void calcTargets( BoardCell startCell, int pathlength) {

		visited = new HashSet<BoardCell>();
		pathTargets = new HashSet<BoardCell>();

		visited.add(startCell);

		findAllTargets(startCell, pathlength);
	}


	public Set<BoardCell> getTargets() {

		return pathTargets;
	}



	public Room getRoom(char symbol) {

		return roomMap.get(symbol);
	}


	public Room getRoom(BoardCell cell) {

		return roomMap.get(cell.getInitial());
	}


	public BoardCell getCell(int row, int col) {

		return gridBoard[row][col];																					//returns the value of the cell
	}


	public int getNumRows() {

		return numRows;
	}


	public int getNumColumns() {

		return numColumns;
	}


	public Set<BoardCell> getAdjList(int i, int j) {

		return gridBoard[i][j].getAdjList();																		//return adjlist
	}


	public void deal() {

		Collections.shuffle(deck);																					//Shuffles deck

		for(int i = 0; i < deck.size(); i++) 																		//iterates through deck
		{
			if (usedCards.contains(deck.get(i))) 																	//if the card is used continue
			{
				continue;
			}
			switch (deck.get(i).getType())
			{

			case WEAPON:
				if(theAnswer.getWeapon() == null)
				{
					theAnswer.setWeapon(deck.get(i));																// sets the solutions weapon
					usedCards.add(deck.get(i));
				}
				break;
			case ROOM:
				if(theAnswer.getRoom() == null)
				{
					theAnswer.setRoom(deck.get(i));																	// sets the solutions weapon
					usedCards.add(deck.get(i));
				}
				break;
			case PERSON:
				if(theAnswer.getPerson() == null)
				{
					theAnswer.setPerson(deck.get(i));																// sets the solutions weapon
					usedCards.add(deck.get(i));
				}
				break;

			}


		}
		for(int i=0;i<players.size();i++)																			//iterates through lists of players
		{
			int count = 0;

			for(int j=0;j<deck.size();j++)																			//iterates through deck
			{

				if(usedCards.contains(deck.get(j))) 																// continue if is a used card
				{
					continue;

				}

				players.get(i).updateHand(deck.get(j)); 															//give card to player
				usedCards.add(deck.get(j));
				count=count+1;

				if(count==3) 																						//break loop when each player has three cards
				{
					break;
				}
			}
		}
	}
	
	public boolean checkAccusation(Solution solution) {																//checking to see if answer and solution match

		if ((theAnswer.getPerson() == solution.getPerson()) && (theAnswer.getWeapon() == solution.getWeapon()) && (theAnswer.getRoom() == solution.getRoom()))
		{
			return true;

		}else {																										//if yes, return true, if not, return false
			return false;
		}
	}

	public Card handleSuggestions(Solution solution) {

		for (int i = 0; i < players.size(); i++) 																	//if it does not equal a null, then return it
		{
			if(playerTurn==i) {
				i=i+1;
			}
			if(players.get(i).disproveSuggestion(solution) != null) 
			{
				return players.get(i).disproveSuggestion(solution);
			}
			
		}

		return null;

	}

	public Solution getSolution() {
		// TODO Auto-generated method stub
		return theAnswer;
	}

	public void setSolution(Card cardOne, Card cardTwo, Card cardThree) {

		theAnswer.setRoom(cardOne);
		theAnswer.setWeapon(cardTwo);
		theAnswer.setPerson(cardThree);
	}


	public static ArrayList<Card> getDeck() {

		return deck;
	}

	public static ArrayList<Player> getPlayerList() {
		return players;

	}

	public void playerList(ArrayList<Player> list) {
		this.players = list;
	}
	public Player getPlayer(String player) {

		for(int i=0;i<players.size();i++) 																				//loops through players list and finds the player 
		{
			if(players.get(i).getName().equals(player)) 
			{
				return players.get(i);
			}
		}	
		return null;
	}

	public ArrayList<Card> getUsedCards() {

		return usedCards;
	}

	public Card getCard(String string, CardType type) {

		for(Card i : deck) 																								//iterates through cards until it finds the card that wanted
		{
			if(i.getCardName().equals(string) && i.getType() == type) 
			{
				return i;
			}
		}

		return null;
	}
	public int getPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(int playerTurn) {
		this.playerTurn = playerTurn;
	}

	public void setPathlength(int roll) {
		// TODO Auto-generated method stub
		this.pathlength=roll;

	}

	public void playing() {

		if(playerTurn == 0) 																							//if human player
		{
			pathTargets=null;
			startCell = getCell((players.get(0).getRow()),(players.get(0).getCol()));									//gets all information to find player and locations
			calcTargets(startCell,pathlength);

			for(int i=0;i<numRows;i++) 																					//nested loop to see where at in grid
			{
				for(int j=0;j<numColumns;j++) 
				{
					if(pathTargets.contains(gridBoard[i][j])) 															//finds if the grid location is a valid space
					{
						gridBoard[i][j].setFlag(true);																	//valid move spaces for player

					}else {
						gridBoard[i][j].setFlag(false);																	//not a valid place for user to click
					}
				}
			}


			moved = false;
			
		}else {																											//if not a human player
			for(int i=0;i<numRows;i++) 												
			{																											//nested loop to see where on grid
				for(int j=0;j<numColumns;j++) 
				{
					gridBoard[i][j].setFlag(false);																		//if not a flag, then repaint so user can't click
					gridBoard[i][j].setOccupied(false);
					
					for(Player k: players) 
					{
						if(k.getRow() == i && k.getCol() == j) 
						{
							gridBoard[i][j].setOccupied(true);															//sets spots to occupied
						}
					}
					repaint();
				}
			}

			calcTargets(gridBoard[players.get(playerTurn).getRow()][players.get(playerTurn).getCol()], roll());					//creates the computer player to move
			BoardCell Location = players.get(playerTurn).selectTargets(pathTargets);
			players.get(playerTurn).setInRoom(false);

			if (Location.isRoomCenter()) 																						//finds player location
			{
				players.get(playerTurn).setInRoom(true);																		//sets in room true
			}
			
			
			if(Location.getRow() == players.get(playerTurn).getRow() && Location.getCol() == players.get(playerTurn).getCol()) 
			{
				moved = false;																									//finds if player has moved or not
			}	
			
			
			players.get(playerTurn).setRow(Location.getRow());																	
			players.get(playerTurn).setCol(Location.getCol());
			
			
			if(players.get(playerTurn).isInRoom()) 
			{
				players.get(playerTurn).setRoom(roomMap.get(Location.getInitial()));
				players.get(playerTurn).createSuggestion(deck,(players.get(playerTurn)).getRoom());
				
			}
			
		}

	}

	public Set<BoardCell> getPathTargets() {
		return pathTargets;
	}

	public void mouseClicked(MouseEvent e) {

	}
	public void mouseEntered(MouseEvent e) {

	}
	public void mouseExited(MouseEvent e) {

	}
	public void mouseReleased(MouseEvent e) {

	}
	public void mousePressed(MouseEvent e) {
		int size = 0;
																											//allows for user to move the window							
		if(getHeight() > getWidth()) 
		{
			size = getWidth();
		}

		else if(getWidth() > getHeight()) 
		{
			size = getHeight();
		}	


		BoardCell target=null;



		for(int i=0;i<numRows;i++)  																		//goes through each cell on board until it finds where was clicked
		{
			for(int j=0;j<numColumns;j++) 
			{
				if(gridBoard[i][j].containsClick(e.getX(),e.getY()))
				{
					target=gridBoard[i][j];
					players.get(playerTurn).setInRoom(false);

					if (gridBoard[i][j].getInitial()!='W')  												//if cell is a walkway
					{
						target=(roomMap.get(gridBoard[i][j].getInitial())).getCenterCell();					//finds center of room 
						players.get(playerTurn).setInRoom(true);											//sets that there is someone in the room
						players.get(playerTurn).setRoom(roomMap.get(gridBoard[i][j].getInitial()));
						
					}

					if(target!=null)
					{
						break;
					}
				}
			}
		}
		if(!submitPressed) {
			if(pathTargets.contains(target)) 				
			{
				players.get(playerTurn).setRow(target.getRow());
				players.get(playerTurn).setCol(target.getCol());
				moved=true;
				
				if(players.get(playerTurn).isInRoom()) {																			//sees if person already made a move
					suggestion = new Suggestion(this);															//if not they can make suggestion if in room
					suggestion.setVisible(true);
				}

				setSubmitPressed(true);
					
			}else {

				JOptionPane.showMessageDialog(null, "Not a valid move", "Error Message", JOptionPane.ERROR_MESSAGE);
			}
		}

		
	}

	public static boolean isMoved() {
		return moved;
	}
	public String getGuessWeapon() {
		return guessWeapon;
	}

	public void setGuessWeapon(String guessWeapon) {
		this.guessWeapon = guessWeapon;
	}

	public String getGuessPerson() {
		return guessPerson;
	}

	public void setGuessPerson(String guessPerson) {
		this.guessPerson = guessPerson;
	}

	public String getGuessRoom() {
		return guessRoom;
	}

	public void setGuessRoom(String guessRoom) {
		
		this.guessRoom = guessRoom;
		
		
	}

	public boolean isSubmitPressed() {
		return submitPressed;
	}

	public void setSubmitPressed(boolean submitPressed) {
		this.submitPressed = submitPressed;
	}
	
	public void setGameControlPanel(GameControlPanel gameControlPanel) {
		this.gameControlPanel = gameControlPanel;
	}
	
	public GameControlPanel getGameControlPanel() {
		
		return gameControlPanel;
	}
	
	public void setClueCardsGUI(clueCardsGUI ClueCardsGUI) {
		this.ClueCardsGUI = ClueCardsGUI;
	}
	
	public clueCardsGUI getClueCardGUI() {
		
		return ClueCardsGUI;
	}
}

	
	
