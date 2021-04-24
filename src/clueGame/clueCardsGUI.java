package clueGame;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class clueCardsGUI extends JPanel {
	
	private JLabel people1;
	private JLabel people2;
	private JLabel room;
	private JLabel room2;
	private JLabel weapon;
	private JLabel weapon2;
	
	private ArrayList<Card> peopleHand= new ArrayList<Card>();
	private ArrayList<Card> peopleSeen= new ArrayList<Card>();
	private ArrayList<Card> roomHand= new ArrayList<Card>();
	private ArrayList<Card> roomSeen= new ArrayList<Card>();
	private ArrayList<Card> weaponHand= new ArrayList<Card>();
	private ArrayList<Card> weaponSeen= new ArrayList<Card>();
	private static ArrayList<Player> players= new ArrayList<Player>();
	private ArrayList<Card> seen=new ArrayList<Card>();;
	
	public clueCardsGUI(Player player) {
		
		
		seen=player.getSeenCards();
		for (Card i:seen)
		{	
			if(i.getType()==CardType.PERSON)											//if card is person then add to seen people
			{
				peopleSeen.add(i);
				
			}else if(i.getType()==CardType.ROOM) 
			{																			//if card is room then add to seen room
				roomSeen.add(i);
				
			}else if(i.getType()==CardType.WEAPON) 
			{																			//if card is weapon then add to seen weapon
				weaponSeen.add(i);
			}
		}
		ArrayList<Card> playerHand = new ArrayList<Card>();
		playerHand=player.getHand();
		
		for (Card i:playerHand)
		{
			if(i.getType()==CardType.PERSON)											//if card is person then add to hand people
			{
				peopleHand.add(i);
				
			}else if(i.getType()==CardType.ROOM) 										//if card is room then add to hand room
			{
				roomHand.add(i);
				
			}else if(i.getType()==CardType.WEAPON) 										//if card is weapon then add to hand weapon
			{
				weaponHand.add(i);
			}
		}
		
		setLayout(new GridLayout(3,0));													//created main grid
		setBorder(new TitledBorder(new EtchedBorder(), "Known Cards"));
		
		JPanel panel = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		
		panel = people();																//adds to each panel
		add(panel);	
		panel2 = room();
		add(panel2);
		panel3 = weapon();																//adds to each panel
		add(panel3);
	}
	
	public ArrayList<Card> getSeen() {
		return seen;
	}

	public void setSeen(ArrayList<Card> seen) {
		this.seen = seen;
	}

	private JPanel people() {
			
		JPanel mainPanel = new JPanel();												//creates top grid
		
		mainPanel.setLayout(new GridLayout(2,0));
		
		
		JPanel peopleMain = new JPanel();												//creates the next panel
		JPanel peopleMain2 = new JPanel();
		JPanel hand = new JPanel();
		JPanel seen = new JPanel();
		people1 = new JLabel("In Hand:");												//creates the label
		people2 = new JLabel("Seen:");

		mainPanel.setBorder(new TitledBorder(new EtchedBorder(), "People"));			//creates border
		hand.add(people1);
		
		if(peopleHand.size()==0) 														//if no people in hand add NONE to panel
		{
			JTextField cards=new JTextField(15);
			cards.setText("None");
			cards.setEditable(false);
			hand.add(cards);
			
		}else 
		{
			for(int i=0;i<peopleHand.size();i++) 										//goes through list of people in hand and adds it to the panel
			{
				JTextField cards=new JTextField(15);
				cards.setText(peopleHand.get(i).getCardName());
				cards.setEditable(false);
				hand.add(cards);		
			}	
		}
		seen.add(people2);
		if(peopleSeen.size()==0) 														//if no people seen add NONE to panel
		{
			JTextField cards=new JTextField(15);
			cards.setText("None");
			cards.setEditable(false);
			seen.add(cards);
				
		}else 
		{
			for(int i=0;i<peopleSeen.size();i++) 										//goes through list of people seen and adds it to the panel
			{	
				JTextField cards=new JTextField(15);
				cards.setText(peopleSeen.get(i).getCardName());
				cards.setEditable(false);
				seen.add(cards);
				
			}	
		}
		
		mainPanel.add(hand);															//adding to seen and hand
		mainPanel.add(seen);
		
		return mainPanel;
	}
	
	private JPanel room() {
		
		
			
		JPanel mainPanel = new JPanel();												//creates top grid
		
		mainPanel.setLayout(new GridLayout(2,0));
		
		JPanel roomMain = new JPanel();													//creates the next panel
		JPanel roomMain2 = new JPanel();
		JPanel hand = new JPanel();
		JPanel seen = new JPanel();	
		room = new JLabel("In Hand:");													//creates the label
		room2 = new JLabel("Seen:");

		mainPanel.setBorder(new TitledBorder(new EtchedBorder(), "Rooms"));				//creates border
		hand.add(room);
		
		if(roomHand.size()==0) 
		{
			JTextField cards=new JTextField(15);
			cards.setText("None");
			cards.setEditable(false);
			hand.add(cards);
			
		}else 
		{
			for(int i=0;i<roomHand.size();i++) 											//goes through list of rooms in hand and adds it to the panel
			{
				JTextField cards=new JTextField(15);
				cards.setText(roomHand.get(i).getCardName());
				cards.setEditable(false);
				hand.add(cards);		
			}	
		}
		seen.add(room2);
		if(roomSeen.size()==0) 
		{
			JTextField cards=new JTextField(15);
			cards.setText("None");
			cards.setEditable(false);
			seen.add(cards);
				
		}else 
		{
			for(int i=0;i<roomSeen.size();i++) 											//goes through list of rooms seen and adds it to the panel
			{	
				JTextField cards=new JTextField(15);
				cards.setText(roomSeen.get(i).getCardName());
				cards.setEditable(false);
				seen.add(cards);
				
			}	
		}
		
		mainPanel.add(hand);															//adding to seen and hand
		mainPanel.add(seen);
		
		return mainPanel;
	}
	
	private JPanel weapon() {
			
		JPanel mainPanel = new JPanel();												//creates top grid
		
		mainPanel.setLayout(new GridLayout(2,0));
		
		JPanel weaponMain = new JPanel();												//creates the next panel
		JPanel weaponmain2 = new JPanel();	
		JPanel hand = new JPanel();
		JPanel seen = new JPanel();
		weapon = new JLabel("In Hand:");												//creates the label
		weapon2 = new JLabel("Seen:");

		mainPanel.setBorder(new TitledBorder(new EtchedBorder(), "Weapon"));			//creates border
		hand.add(weapon);
		
		if(weaponHand.size()==0) 														//add none to panel if no weapons in hand
		{
			JTextField cards=new JTextField(15);
			cards.setText("None");
			cards.setEditable(false);
			hand.add(cards);
			
		}else 
		{
			for(int i=0;i<weaponHand.size();i++) 										//goes through weapons in hand and adds them to panel
			{
				JTextField cards=new JTextField(15);
				cards.setText(weaponHand.get(i).getCardName());
				cards.setEditable(false);
				hand.add(cards);		
			}	
		}
		seen.add(weapon2);
		if(weaponSeen.size()==0) 														// add none to panel if no weapons seen
		{
			JTextField cards=new JTextField(15);
			cards.setText("None");
			cards.setEditable(false);
			seen.add(cards);
				
		}else 
		{
			for(int i=0;i<weaponSeen.size();i++) 										//goes through weapons seen and adds them to panel
			{	
				JTextField cards=new JTextField(15);
				cards.setText(weaponSeen.get(i).getCardName());
				cards.setEditable(false);
				seen.add(cards);
			}	
		}
		
		mainPanel.add(hand);															//adding to seen and hand
		mainPanel.add(seen);	
		
		return mainPanel;
	}
	
	public void update(Player player) {
		
		removeAll();
		
		seen=player.getSeenCards();

		peopleSeen.clear();
		roomSeen.clear();
		weaponSeen.clear();
		
		for (Card i:seen)
		{
			if(i.getType()==CardType.PERSON)											//if card is person then add to seen people
			{
				peopleSeen.add(i);

			}else if(i.getType()==CardType.ROOM) 
			{																			//if card is room then add to seen room
				roomSeen.add(i);

			}else if(i.getType()==CardType.WEAPON) 
			{																			//if card is weapon then add to seen weapon
				weaponSeen.add(i);
			}
		}

		
		setLayout(new GridLayout(3,0));													//created main grid


		

		setBorder(new TitledBorder(new EtchedBorder(), "Known Cards"));

		JPanel panel = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();

		panel = people();																//adds to each panel
		add(panel);	
		panel2 = room();
		add(panel2);
		panel3 = weapon();																//adds to each panel
		add(panel3);
	}

	public static void main(String[] args) {
		
	    Player player = new ComputerPlayer("Boba","Green",5,0);							//computer players
	    Player player2 = new ComputerPlayer("Maul","Red",21,16);
	    Player player3 = new ComputerPlayer("Windu","Purple",0,14);
	    Player player4 = new ComputerPlayer("Gungi","Yellow",6,25);
	    		
	    Card blaster = new Card("Blaster","Weapon");									//cards
		Card boba = new Card("Boba","Room");
		Card bedroom = new Card("Bedroom","Room");
		Card windu = new Card("Windu","Room");
		Card lounge = new Card("Lounge","Room");
		Card blueSaber = new Card("Blue Saber","Weapon");
		Card pool = new Card("Pool","Room");
		
		player.updateHand(pool);														//updating the hand
		player.updateHand(blueSaber);
		player.updateSeen(lounge);
		player.updateSeen(blaster);
		player.updateSeen(windu);
		player.updateSeen(boba);
		player.updateSeen(bedroom);
		
		player2.updateHand(boba);
		player2.updateHand(pool);

		player3.updateHand(windu);
		player3.updateHand(blaster);
		
		player4.updateHand(lounge);
		
		players.add(player);
		players.add(player2);															//adding players
		players.add(player3);
		players.add(player4);
		
		clueCardsGUI panel = new clueCardsGUI(player);  								// create the panel
		JFrame frame = new JFrame();  													// create the frame 
		frame.setContentPane(panel); 													// put the panel in the frame
		frame.setSize(220, 750);  														// size the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 							// allow it to close
		frame.setVisible(true); 														// make it visible
	
	}
}
