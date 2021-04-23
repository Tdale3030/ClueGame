package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Accousation extends JDialog{

	protected ArrayList<Card> seen;
	private JComboBox<String> weapons = new JComboBox<String>();
	private JComboBox<String> people = new JComboBox<String>();
	private JComboBox<String> room = new JComboBox<String>();

	
	
	public Accousation(ActionListener actionListener){

		setSize(400,200);
		setTitle("Make A Accousation");
		setLayout(new GridLayout(4,1));
		setAlwaysOnTop(true);
		setModal(true);

		JPanel panel=new JPanel();
		
		for(Card card:Board.getDeck()) 
		{
			if(card.getType()==CardType.WEAPON) 
			{
				weapons.addItem(card.getCardName());
			}
			else if(card.getType()==CardType.PERSON) 
			{
				people.addItem(card.getCardName());
			}
			else if(card.getType()==CardType.ROOM) 
			{
				room.addItem(card.getCardName());
			}
			
		}
		
		JPanel roomPanel = new JPanel();							//creates top grid
		roomPanel.setLayout(new GridLayout(1,2));
		JLabel roomGuess = new JLabel("Room");						//creates the label
		roomPanel.add(roomGuess, BorderLayout.SOUTH);
		roomPanel.add(room);
		add(roomPanel);
		
		JPanel personPanel = new JPanel();							//creates top grid
		personPanel.setLayout(new GridLayout(1,2));
		JLabel person = new JLabel("Person");						//creates the label
		personPanel.add(person);
		personPanel.add(people);
		add(personPanel);
		
		JPanel weaponPanel = new JPanel();							//creates top grid
		weaponPanel.setLayout(new GridLayout(1,2));
		JLabel weapon = new JLabel("Weapon");						//creates the label
		weaponPanel.add(weapon);
		weaponPanel.add(weapons);
		add(weaponPanel);
																	//button
		JPanel buttonPanel = new JPanel();							//creates top grid
		buttonPanel.setLayout(new GridLayout(1,2));
		JButton button = new JButton("Submit");						//creates the label
		JButton button2 = new JButton("Cancel");
		buttonPanel.add(button);
		buttonPanel.add(button2);
		add(buttonPanel);
	}
}
