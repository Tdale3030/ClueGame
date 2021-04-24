package clueGame;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Suggestion extends JDialog {
		
	protected ArrayList<Card> seen;																					//bringing in the lists
	private JComboBox<String> weapons = new JComboBox<String>();
	private JComboBox<String> people = new JComboBox<String>();


	public Suggestion(Board board){

		setSize(400,200);																							//setting size and titles
		setTitle("Make A Suggestion");
		setLayout(new GridLayout(4,1));
		setAlwaysOnTop(true);
		setModal(true);

		JPanel panel=new JPanel();
		
		for(Card card:Board.getDeck()) 																				//for loop for deck of cards
		{
			if(card.getType()==CardType.WEAPON) 																	//if it is a weapon card
			{
				weapons.addItem(card.getCardName());																//add to items
			}
			else if(card.getType()==CardType.PERSON) 																//if it is a person card
			{
				people.addItem(card.getCardName());																	//add to items
			}

		}
																													//room
		JPanel roomPanel = new JPanel();																			//creates top grid
		roomPanel.setLayout(new GridLayout(1,2));
		JTextField roomName = new JTextField(board.getPlayerList().get(board.getPlayerTurn()).getRoom().getName());
		JLabel room = new JLabel("Current Room");						//creates the label
		roomPanel.add(room, BorderLayout.SOUTH);
		roomName.setEditable(false);																				//adding
		roomPanel.add(roomName);
		add(roomPanel);
																													//Person
		JPanel personPanel = new JPanel();																			//creates top grid
		personPanel.setLayout(new GridLayout(1,2));
		JLabel person = new JLabel("Person");																		//creates the label
		personPanel.add(person);
		personPanel.add(people);
		add(personPanel);																							//adding
																													//weapon
		JPanel weaponPanel = new JPanel();																			//creates top grid
		weaponPanel.setLayout(new GridLayout(1,2));
		JLabel weapon = new JLabel("Weapon");																		//creates the label
		weaponPanel.add(weapon);
		weaponPanel.add(weapons);
		add(weaponPanel);
																													//button
		JPanel buttonPanel = new JPanel();																			//creates top grid
		buttonPanel.setLayout(new GridLayout(1,2));
		JButton button = new JButton("Submit");																		//creates the label
		JButton button2 = new JButton("Cancel");
		buttonPanel.add(button);																					//adding
		buttonPanel.add(button2);
		add(buttonPanel);
		
		
		button.addActionListener(new ActionListener() 
		{
	        public void actionPerformed(ActionEvent e) 																//button is pressed
	        {
	        	board.setGuessWeapon((String)weapons.getSelectedItem());											//setting items
	        	board.setGuessPerson((String)people.getSelectedItem());
	        	board.setGuessRoom(board.getPlayerList().get(board.getPlayerTurn()).getRoom().getName());
	        	
	        	String room = board.getPlayerList().get(board.getPlayerTurn()).getRoom().getName();					//setting items to strings
	        	String weapon = (String)weapons.getSelectedItem();
	        	String person = (String)people.getSelectedItem();
	        	String guess = room + ", " + person + ", " + weapon;												//created string to print in the panel
	        	
	        	board.getGameControlPanel().setGuess(guess);
	        	board.getGameControlPanel().revalidate();															//added and wrote
	        	
	        	Card roomCard = board.getCard(board.getPlayerList().get(board.getPlayerTurn()).getRoom().getName(), CardType.ROOM);			//setting cards equal
	        	Card personCard = board.getCard(person, CardType.PERSON);
	        	Card weaponCard = board.getCard(weapon, CardType.WEAPON);
	        	
	        	Solution solution=new Solution();																	//setting cards equal to solution
	        	solution.setPerson(personCard);     
	        	solution.setRoom(roomCard);
	        	solution.setWeapon(weaponCard);
	        	
	        	Card solutionCard = board.handleSuggestions(solution);												//calling handle suggestion with the solution
	        	if (solutionCard!=null) 
	        	{																									//if nothing there
	        		board.getGameControlPanel().setGuessResult(solutionCard.getCardName());
	        		board.getGameControlPanel().revalidate();														//updating
		        	
		        	board.getPlayerList().get(board.getPlayerTurn()).updateSeen(solutionCard);			
		        
		        	board.getClueCardGUI().update(board.getPlayerList().get(board.getPlayerTurn()));
		        	
	        	}else 																								//if solution card is not null
	        	{
	        		board.getGameControlPanel().setGuessResult("No one can disprove");
	        	}
	      
	        	setVisible(false);																					//leaves pop up
	        	
	        }
		});
		
		button2.addActionListener(new ActionListener() {
			
	        public void actionPerformed(ActionEvent e) 																//button is pressed
	        {
	        	setVisible(false);																					//leaves pop up
	        }
		});

	}



}
