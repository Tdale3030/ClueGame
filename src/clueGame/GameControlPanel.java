package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class GameControlPanel extends JPanel {
	
	private JTextField name;
	private JTextField roll2;
	private JTextField whoseTurn2;
	private JLabel guess;
	private JLabel guessResult;
	private JButton buttonNext = new JButton("Next");
	private int roll;
	
	
	

	public GameControlPanel(Board board) {
		
		setLayout(new GridLayout(2,0));							//created main grid
		JPanel panel = new JPanel(); 
		JPanel panel2=new JPanel();
		panel = layout1();										//adds to each panel
		add(panel);
		panel2 = layout2();
		add(panel2);
		roll=board.roll();
		board.setPathlength(roll);
		setTurn(board.getPlayerList().get(board.getPlayerTurn()),roll);
		board.playing();
		
		
		buttonNext.addActionListener(new ActionListener() 
		{
	        public void actionPerformed(ActionEvent e) 
	        {
	        	board.setPlayerTurn(board.getPlayerTurn()+1);
	        	
	        	if(board.getPlayerTurn()==6) 
	        	{
	        		board.setPlayerTurn(0);
	        	}
	        	
	        	roll=board.roll();
	        	board.setPathlength(roll);
	        	board.playing();
	        	setTurn(board.getPlayerList().get(board.getPlayerTurn()),roll);
	        	
	        }
	    });
	}

	private JPanel layout1() {
		
		JPanel mainPanel = new JPanel();						//creates top grid
		mainPanel.setLayout(new GridLayout(1,4));
		
		JPanel whoseTurnMain = new JPanel();					//creates the next panel
		JLabel whoseTurn = new JLabel("Whose Turn?");			//creates the label
		whoseTurn2 = new JTextField(15);				//allows for text to be entered
		whoseTurn2.setEditable(false);
		whoseTurnMain.add(whoseTurn);
		whoseTurnMain.add(whoseTurn2);		
		mainPanel.add(whoseTurnMain);							//adds to main panel
		
		
		JPanel rollMain = new JPanel();							//creates next panel
		JLabel roll = new JLabel("Roll:");						//creates the label
		roll2 = new JTextField(5);					//allows for text to be entered
		roll2.setEditable(false);
		rollMain.add(roll);
		rollMain.add(roll2);
		mainPanel.add(rollMain);								//adds to the panels
		
		JButton buttonAccousation = new JButton("Make Accousation");		//creates first button
		mainPanel.add(buttonAccousation);
		
		buttonNext = new JButton("Next");							//creates second button
		mainPanel.add(buttonNext);
		
		return mainPanel;				
	}
	
	private JPanel layout2() {									//creates bottom gird
		
		JPanel mainPanel2 = new JPanel();
		mainPanel2.setLayout(new GridLayout(0,2));
		
		JPanel guessMain = new JPanel();						//creates the guess panel
		this.guess = new JLabel();					//allows for text to be entered
		guessMain.add(this.guess);
		guessMain.setBorder(new TitledBorder(new EtchedBorder(), "Guess"));			//creates border
		mainPanel2.add(guessMain);													//adds to main
		
		JPanel guessResultMain = new JPanel();						//creates next panel
		this.guessResult = new JLabel();				//allows for text to be entered
		guessResultMain.add(this.guessResult);
		guessResultMain.setBorder(new TitledBorder(new EtchedBorder(), "Guess Result"));			//creates border
		mainPanel2.add(guessResultMain);
		
		return mainPanel2;											//returns whole panel
	}
	
	

	private void setTurn(Player player, int rollNumber) {
		this.roll2.setText(Integer.toString(rollNumber));
		this.whoseTurn2.setText(player.getName());
		this.whoseTurn2.setBackground(player.getColor());
		
	}

	public void setGuess(String guess) {
	    this.guess.setText(guess);
	}
	
	public void setGuessResult(String guess) {
	    this.guessResult.setText(guess);
	}
}
