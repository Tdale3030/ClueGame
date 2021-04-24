package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Accousation extends JDialog{

	protected ArrayList<Card> seen;
	private JComboBox<String> weapons = new JComboBox<String>();
	private JComboBox<String> people = new JComboBox<String>();
	private JComboBox<String> room = new JComboBox<String>();
	
	public Accousation(ActionListener actionListener, Board board){

		setSize(400,200);																				//setting the dialog pop up
		setTitle("Make A Accousation");																	//title and sizing
		setLayout(new GridLayout(4,1));
		setAlwaysOnTop(true);
		setModal(true);

		JPanel panel=new JPanel();
		
		for(Card card:Board.getDeck()) 																	//for loop through deck
		{	
			if(card.getType()==CardType.WEAPON) 														//if equals a weapon
			{
				weapons.addItem(card.getCardName());													//add card to the item
			}
			else if(card.getType()==CardType.PERSON) 													//if equals a person
			{	
				people.addItem(card.getCardName());														//add card to the item
			}
			else if(card.getType()==CardType.ROOM) 														//if equals a room
			{
				room.addItem(card.getCardName());														//add card to the item
			}
			
		}
		
		JPanel roomPanel = new JPanel();																//creates top grid
		roomPanel.setLayout(new GridLayout(1,2));
		JLabel roomGuess = new JLabel("Room");															//creates the label
		roomPanel.add(roomGuess, BorderLayout.SOUTH);
		roomPanel.add(room);																			//adding
		add(roomPanel);	
		
		JPanel personPanel = new JPanel();																//creates top grid
		personPanel.setLayout(new GridLayout(1,2));	
		JLabel person = new JLabel("Person");															//creates the label
		personPanel.add(person);
		personPanel.add(people);
		add(personPanel);																				//adding
		
		JPanel weaponPanel = new JPanel();																//creates top grid
		weaponPanel.setLayout(new GridLayout(1,2));
		JLabel weapon = new JLabel("Weapon");															//creates the label
		weaponPanel.add(weapon);
		weaponPanel.add(weapons);																		//adding
		add(weaponPanel);
																										//button
		JPanel buttonPanel = new JPanel();																//creates top grid
		buttonPanel.setLayout(new GridLayout(1,2));
		JButton button = new JButton("Submit");															//creates the label
		JButton button2 = new JButton("Cancel");
		buttonPanel.add(button);
		buttonPanel.add(button2);
		add(buttonPanel);
		
		
		button.addActionListener(new ActionListener() 
		{
	        public void actionPerformed(ActionEvent e) 													//button is pressed
	        {
	        	board.setGuessWeapon((String)weapons.getSelectedItem());								//gets user drop down menu choice
	        	board.setGuessPerson((String)people.getSelectedItem());
	        	board.setGuessRoom((String)room.getSelectedItem());
	        	
	        	String rooms = (String)room.getSelectedItem();											//sets it to a string
	        	String weapon = (String)weapons.getSelectedItem();
	        	String person = (String)people.getSelectedItem();
	        	
	        	Card roomCard = board.getCard(rooms, CardType.ROOM);									//sets it to the card
	        	Card personCard = board.getCard(person, CardType.PERSON);
	        	Card weaponCard = board.getCard(weapon, CardType.WEAPON);
	        	
	        	Solution solution=new Solution();														//sets it to solution for parameter
	        	solution.setPerson(personCard);     
	        	solution.setRoom(roomCard);
	        	solution.setWeapon(weaponCard);
	        	
	        	if(board.checkAccusation(solution)) 													//if user check accousation is correct, you win
	        	{
	        		JOptionPane.showMessageDialog(null, "YOU WON\nGREAT JOB", "GAMEOVER", JOptionPane.INFORMATION_MESSAGE);
	        	}
	        	if(!board.checkAccusation(solution)) 													//if user check accousation is false, you lose
	        	{
		    		JOptionPane.showMessageDialog(null, "YOU LOST\nBETTER LUCK NEXT TIME", "GAMEOVER", JOptionPane.INFORMATION_MESSAGE);
	        	}
	    		System.exit(0);																			//exits game if user makes accousation
	        	setVisible(false);
	        	
	        	
	        }
		});
		
		button2.addActionListener(new ActionListener() 													//if user cancels the accousation
		{
	        public void actionPerformed(ActionEvent e) 	//button is pressed
	        {
	        	setVisible(false);
	        }
		});
	}
}
