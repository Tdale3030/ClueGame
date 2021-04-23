package clueGame;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Suggestion extends JDialog {
	protected ArrayList<Card> seen;
	private JComboBox<String> weapons= new JComboBox<String>();
	private JComboBox<String> people=new JComboBox<String>();


	public Suggestion(Board board){


		setSize(400,200);
		setTitle("Make A Suggestion");
		setLayout(new GridLayout(4,1));
		setAlwaysOnTop(true);
		setModal(true);

		JPanel panel=new JPanel();
		for(Card card:Board.getDeck()) {
			if(card.getType()==CardType.WEAPON) {
				weapons.addItem(card.getCardName());
			}
			else if(card.getType()==CardType.PERSON) {
				people.addItem(card.getCardName());
			}

		}
		//room
		JPanel roomPanel = new JPanel();							//creates top grid
		roomPanel.setLayout(new GridLayout(1,2));
		JTextField roomName = new JTextField(board.getPlayerList().get(board.getPlayerTurn()).getRoom().getName());
		JLabel room = new JLabel("Current Room");						//creates the label
		roomPanel.add(room, BorderLayout.SOUTH);
		roomName.setEditable(false);
		roomPanel.add(roomName);
		add(roomPanel);

		
		//Person
		JPanel personPanel = new JPanel();							//creates top grid
		personPanel.setLayout(new GridLayout(1,2));
		JLabel person = new JLabel("Person");						//creates the label
		personPanel.add(person);
		personPanel.add(people);
		add(personPanel);
		
		
		
		//weapon
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
		//buttonPanel.add(buttonPanel);
		add(buttonPanel);





		//this.getContentPane().add(panel);
	}



}
