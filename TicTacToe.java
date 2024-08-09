/**
 * 
 */

/**
 * 
 */
package packageBox;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class TicTacToe implements ActionListener
{
	Random random = new Random(); //determines who goes first
	JFrame frame = new JFrame(); //makes the JFrame
	JPanel title_panel = new JPanel(); //text slot
	JPanel button_panel = new JPanel(); //allows specific layout of buttons
	JLabel textfield = new JLabel(); //text at the top
	JButton[] buttons = new JButton[9]; //the actual buttons
	
	boolean player1_turn;
	boolean win = false;
	
	int counterDif;
	
	/**
	 * Constructor
	 */
	public TicTacToe(int counterDif)
	{
		this.counterDif = counterDif;
		makeFrame();
	}
	
	public void makeFrame()
	{
		
		//sets the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //program closes when top right X button is closed
		frame.setSize(1920,1080); //sets dimensions of panel
		frame.getContentPane().setBackground(new Color(33,41,48)); //sets background color using rgb values
		frame.setLayout(new BorderLayout());
		frame.setVisible(true); //allows frame to be seen
				
		//sets the text
		textfield.setBackground(new Color(25,25,25));
		textfield.setForeground(new Color(25,255,0));
		textfield.setFont(new Font("Ariel",Font.BOLD,85));
		textfield.setHorizontalAlignment(JLabel.CENTER);
		textfield.setText("TIC-TAC-TOE");
		textfield.setOpaque(true);
				
		//text layout at the top
		title_panel.setLayout(new BorderLayout());
		title_panel.setBounds(0,0,1920,300);
				
				
		//sets the buttons -> use setLayout setup for connect 4
		button_panel.setLayout(new GridLayout(3,3)); //makes the 3 by 3 grid for players
		button_panel.setBackground(new Color(40,40,40));
				
		//adds buttons array to the button panel
		for(int i = 0; i < 9; i++)
		{
			buttons[i] = new JButton(); //initializes that button
			button_panel.add(buttons[i]); //button is added to the button panel
			buttons[i].setFont(new Font("Serif",Font.BOLD,150)); //sets font
			buttons[i].setFocusable(false); //makes sure size cannot be changed
			buttons[i].addActionListener(this); //gets added to the ActionListener
					
		}

		//adds evrything to the JFrame
		title_panel.add(textfield);
		frame.add(title_panel,BorderLayout.NORTH);
		frame.add(button_panel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		int next = 0;
		
		//check each button
		for(int i = 0; i < buttons.length; i++)
		{
			//if button is the same as clicked button
			if(e.getSource()==buttons[i])
			{	
				//sees if it's player1's turn
				if(player1_turn)
				{
					//check if spot is already taken
					if(buttons[i].getText()== "")
					{	
						//sets the color for X -> Green
						textfield.setText("TIC-TAC-TOE");
						buttons[i].setForeground(new Color(96,185,45));
						buttons[i].setText("X");
						player1_turn = false;
						next++;
						checkWin();
						checkReset();
					}
					else
					{
						textfield.setText("Spot Is Already Taken");
					}
				}
			}
		}
				
		if(win == false && next == 1)
		{
			computerTurn();
			checkReset();
			checkTie();
		}
		
	}
	
	/**
	 * The AI makes it's turn
	 */
	public void computerTurn()
	{
		//first do check AI Win
		
			//computer sees if it do a winning move
			//if not, then it will see if it can counter the player from winning
			//if a counter is not available, it will choose a random spot
			int aiWin = aiWinMove();
			
			if(aiWin == -1)
			{
				int counter = counter();
				if(counter != -1)
				{				
					buttons[counter].setForeground(new Color(255,0,0));
					buttons[counter].setText("O");
					player1_turn = true;
					checkWin();
					checkTie();

				}
				else
				{
					int choice = (int)(Math.random() * 9) + 0;
					for(int i = 0; i <= buttons.length && win == false; i++)
					{
						if( i == buttons.length && checkSlotsAvailable() == true)
						{	
							i = -1;
							choice = (int)(Math.random() * 9) + 0;
						}
						if( i == choice && buttons[i].getText() == "")
						{
							buttons[i].setForeground(new Color(255,0,0));
							buttons[i].setText("O");
							player1_turn = true;
							i = buttons.length + 1;
							checkWin();
							checkTie();

						}
					}
				}
			}
			else
			{
				buttons[aiWin].setText("O");
				buttons[aiWin].setForeground(new Color(255,0,0));				
				checkWin();
				checkTie();
			}
	}
		
	
	/**
	 * Sees if the "O" player or AI
	 * can make a counter move
	 * 
	 * @return
	 */
	public int counter()
	{
		//determines the likelihood of the ai to counter/block 
		//the player from doing winning move
		int random = (int)(Math.random() * counterDif) + 0;
		
		//System.out.println("Random #:" + random);
		
		//counter was originally 4
		//random < counterDif
		
		if(random < counterDif)
		{
			//makes each time a counter possibilty is checked at random times
			
			//vertical win
			if( buttons[0].getText() == "X" && buttons[3].getText() == "X" && buttons[6].getText() == "")
			{
				return 6;
			}
			else if( buttons[3].getText() == "X" && buttons[6].getText() == "X" && buttons[0].getText() == "")
			{
				return 0;
			}
			else if( buttons[0].getText() == "X" && buttons[6].getText() == "X" && buttons[3].getText() == "" )
			{
				return 3;
			}
			
			
			
			else if( buttons[1].getText() == "X" && buttons[4].getText() == "X" && buttons[7].getText() == "" )
			{
				return 7;
			}
			else if( buttons[4].getText() == "X" && buttons[7].getText() == "X" && buttons[1].getText() == "")
			{
				return 1;
			}
			else if(buttons[7].getText() == "X" && buttons[1].getText() == "X" && buttons[4].getText() == "")
			{
				return 4;
			}
			
			
			
			
			else if( buttons[2].getText() == "X" && buttons[5].getText() == "X" && buttons[8].getText() == "")
			{
				return 8;
			}
			else if( buttons[5].getText() == "X" && buttons[8].getText() == "X" && buttons[2].getText() == "")
			{
				return 2;
			}
			else if( buttons[8].getText() == "X" && buttons[2].getText() == "X" && buttons[5].getText() == "")
			{
				return 5;
			}

			
			
	
			//horizontal win
			else if( buttons[0].getText() == "X" && buttons[1].getText() == "X" && buttons[2].getText() == "")
			{
				return 2;
			}
			
			else if( buttons[1].getText() == "X" && buttons[2].getText() == "X" && buttons[0].getText() == "")
			{
				return 0;
			}
			else if( buttons[0].getText() == "X" && buttons[2].getText() == "X" && buttons[1].getText() == "" )
			{
				return 1;
			}
			
			
			
			else if( buttons[3].getText() == "X" && buttons[4].getText() == "X" && buttons[5].getText() == "")
			{
				return 5;
			}
			
			else if( buttons[4].getText() == "X" && buttons[5].getText() == "X" && buttons[3].getText() == "")
			{
				return 3;
			}
			else if( buttons[5].getText() == "X" && buttons[3].getText() == "X" && buttons[4].getText() == "" )
			{
				return 4;
			}
			
			
			
			
			else if( buttons[6].getText() == "X" && buttons[7].getText() == "X" && buttons[8].getText() == "")
			{
				return 8;
			}
			
			else if( buttons[7].getText() == "X" && buttons[8].getText() == "X" && buttons[6].getText() == "")
			{
				return 6;
			}
			else if( buttons[8].getText() == "X" && buttons[6].getText() == "X" && buttons[7].getText() == "")
			{
				return 7;
			}
			
			
			
			//diagonal win
			else if( buttons[0].getText() == "X" && buttons[4].getText() == "X" && buttons[8].getText() == "")
			{
				return 8;
			}
			
			else if( buttons[4].getText() == "X" && buttons[8].getText() == "X" && buttons[0].getText() == "")
			{
				return 0;
			}
			else if( buttons[8].getText() == "X" && buttons[0].getText() == "X" && buttons[4].getText() == "" )
			{
				return 4;
			}
			
			
			
			
			else if( buttons[6].getText() == "X" && buttons[4].getText() == "X" && buttons[2].getText() == "")
			{
				return 2;
			}
			
			else if( buttons[2].getText() == "X" && buttons[4].getText() == "X" && buttons[6].getText() == "")
			{
				return 6;
			}
			else if( buttons[2].getText() == "X" && buttons[6].getText() == "X" && buttons[4].getText() == "" )
			{
				return 4;
			}

			
			else
			{
				return -1;
			}
		
		}
		return -1;
	}
	
	
	/**
	 * Sees if the "O" or AI
	 * has won the game
	 * 
	 * @return
	 */
	public int aiWinMove()
	{	
		//vertical win
		if( buttons[0].getText() == "O" && buttons[3].getText() == "O" && buttons[6].getText() == "")
		{
			return 6;
		}
		
		if( buttons[3].getText() == "O" && buttons[6].getText() == "O" && buttons[0].getText() == "")
		{
			return 0;
		}
		
		if( buttons[1].getText() == "O" && buttons[4].getText() == "O" && buttons[7].getText() == "" )
		{
			return 7;
		}
		
		if( buttons[4].getText() == "O" && buttons[7].getText() == "O" && buttons[1].getText() == "")
		{
			return 1;
		}
		
		if( buttons[2].getText() == "O" && buttons[5].getText() == "O" && buttons[8].getText() == "")
		{
			return 8;
		}
		
		if( buttons[5].getText() == "O" && buttons[8].getText() == "O" && buttons[2].getText() == "")
		{
			return 2;
		}

		
		//horizontal win
		if( buttons[0].getText() == "O" && buttons[1].getText() == "O" && buttons[2].getText() == "")
		{
			return 2;
		}
		
		if( buttons[1].getText() == "O" && buttons[2].getText() == "O" && buttons[0].getText() == "")
		{
			return 0;
		}
		
		if( buttons[3].getText() == "O" && buttons[4].getText() == "O" && buttons[5].getText() == "")
		{
			return 5;
		}
		
		if( buttons[4].getText() == "O" && buttons[5].getText() == "O" && buttons[3].getText() == "")
		{
			return 3;
		}
		
		if( buttons[6].getText() == "O" && buttons[7].getText() == "O" && buttons[8].getText() == "")
		{
			return 8;
		}
		
		if( buttons[7].getText() == "O" && buttons[8].getText() == "O" && buttons[6].getText() == "")
		{
			return 6;
		}
		
		
		
		//diagonal win
		if( buttons[0].getText() == "O" && buttons[4].getText() == "O" && buttons[8].getText() == "")
		{
			return 8;
		}
		
		if( buttons[4].getText() == "O" && buttons[8].getText() == "O" && buttons[0].getText() == "")
		{
			return 0;
		}
		
		if( buttons[6].getText() == "O" && buttons[4].getText() == "O" && buttons[2].getText() == "")
		{
			return 2;
		}
		
		if( buttons[2].getText() == "O" && buttons[4].getText() == "O" && buttons[6].getText() == "")
		{
			return 6;
		}
		
		return -1;
		
	}
	
	/**
	 * Changes the button display
	 * for the "O" player
	 * 
	 * @param index
	 */
	public void changeButton(int index)
	{
		buttons[index].setForeground(new Color(255,0,0));
		buttons[index].setText("O");
		player1_turn = true;
	}
	
	/**
	 * Determines who goes first
	 */
	public void firstMove()
	{
		try 
		{
			for(int i = 0; i < buttons.length; i++)
			{
				buttons[i].setEnabled(false);
			}
			
			Thread.sleep(2000);
			
			for(int i = 0; i < buttons.length; i++)
			{
				buttons[i].setEnabled(true);
			}
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		player1_turn = true;
		
		
		int random = (int)(Math.random() * 2) + 0;
		
		if(random == 1)
		{
			computerTurn();
		}
	}
	
	/**
	 * Sees if anyone has won
	 */
	public void checkWin()
	{
		//checkXWin
		
		if((buttons[0].getText()=="X") 
				&& (buttons[1].getText()=="X") 
				&& (buttons[2].getText()=="X") && win == false)
		{
			xWin(0,1,2);
			win = true;
		}
		if((buttons[3].getText()=="X") 
				&& (buttons[4].getText()=="X") 
				&& (buttons[5].getText()=="X") && win == false)
		{
			xWin(3,4,5);
			win = true;
		}
		if((buttons[6].getText()=="X") 
				&& (buttons[7].getText()=="X") 
				&& (buttons[8].getText()=="X") && win == false)
		{
			xWin(6,7,8);
			win = true;
		}
		if((buttons[0].getText()=="X") 
				&& (buttons[3].getText()=="X") 
				&& (buttons[6].getText()=="X") && win == false)
		{
			xWin(0,3,6);
			win = true;
		}
		if((buttons[1].getText()=="X") 
				&& (buttons[4].getText()=="X") 
				&& (buttons[7].getText()=="X") && win == false)
		{
			xWin(1,4,7);
			win = true;
			
		}
		if((buttons[2].getText()=="X") 
				&& (buttons[5].getText()=="X") 
				&& (buttons[8].getText()=="X") && win == false)
		{
			xWin(2,5,8);
			win = true;
		}
		if((buttons[0].getText()=="X") 
				&& (buttons[4].getText()=="X") 
				&& (buttons[8].getText()=="X") && win == false)
		{
			xWin(0,4,8);
			win = true;
		}
		if((buttons[2].getText()=="X") 
				&& (buttons[4].getText()=="X") 
				&& (buttons[6].getText()=="X") && win == false)
		{
			xWin(2,4,6);
			win = true;
		}
		
		
		
		//checkOWin
		if((buttons[0].getText()=="O") 
				&& (buttons[1].getText()=="O") 
				&& (buttons[2].getText()=="O") && win == false)
		{
			oWin(0,1,2);
			win = true;
		}
		if((buttons[3].getText()=="O") 
				&& (buttons[4].getText()=="O") 
				&& (buttons[5].getText()=="O") && win == false)
		{
			oWin(3,4,5);
			win = true;
		}
		if((buttons[6].getText()=="O") 
				&& (buttons[7].getText()=="O") 
				&& (buttons[8].getText()=="O") && win == false)
		{
			oWin(6,7,8);
			win = true;
		}
		if((buttons[0].getText()=="O") 
				&& (buttons[3].getText()=="O") 
				&& (buttons[6].getText()=="O") && win == false)
		{
			oWin(0,3,6);
			win = true;
		}
		if((buttons[1].getText()=="O") 
				&& (buttons[4].getText()=="O") 
				&& (buttons[7].getText()=="O") && win == false)
		{
			oWin(1,4,7);
			win = true;
		}
		if((buttons[2].getText()=="O") 
				&& (buttons[5].getText()=="O") 
				&& (buttons[8].getText()=="O") && win == false)
		{
			oWin(2,5,8);
			win = true;
		}
		if((buttons[0].getText()=="O") 
				&& (buttons[4].getText()=="O") 
				&& (buttons[8].getText()=="O") && win == false)
		{
			oWin(0,4,8);
			win = true;
		}
		if((buttons[2].getText()=="O") 
				&& (buttons[4].getText()=="O") 
				&& (buttons[6].getText()=="O") && win == false)
		{
			oWin(2,4,6);
			win = true;
		}
		
		
	}
	
	/**
	 * Sees if "X" player has won
	 * 
	 * @param positionA
	 * @param positionB
	 * @param positionC
	 */
	public void xWin(int positionA, int positionB, int positionC)
	{
		//changes background color of winning combination
		buttons[positionA].setBackground(Color.GRAY);
		buttons[positionB].setBackground(Color.GRAY);
		buttons[positionC].setBackground(Color.GRAY);
			
		for(int i = 0; i < buttons.length; i++)
		{
			buttons[i].setEnabled(false);
		}
		textfield.setText("X WINS!");
	}
	
	/**
	 * Sees if "O" player has won
	 * 
	 * @param positionA
	 * @param positionB
	 * @param positionC
	 */
	public void oWin(int positionA, int positionB, int positionC)
	{
		buttons[positionA].setBackground(Color.GRAY);
		buttons[positionB].setBackground(Color.GRAY);
		buttons[positionC].setBackground(Color.GRAY);
		
		for(int i = 0; i < buttons.length; i++)
		{
			buttons[i].setEnabled(false);
		}
		textfield.setText("O WINS!");
	}
	
	/**
	 * Checks if there is a tie
	 */
	public void checkTie()
	{
		//check if no slots are available
		boolean condition = checkSlotsAvailable();
		
		if(condition == false && win == false)
		{
			textfield.setText("TIE!");
			
			//set the background for all the buttons to gray
			for(int i = 0; i < buttons.length; i++)
			{
				buttons[i].setBackground(Color.GRAY);
				buttons[i].setEnabled(false);
			}
			textfield.setText("TIE!");
			
		}
	}
	
	
	/**
	 * sees if there are any 
	 * open slots on the board
	 * 
	 * @return boolean
	 */
	public boolean checkSlotsAvailable()
	{
		for(int i = 0; i < buttons.length; i++)
		{
			if(buttons[i].getText() == "")
			{
				return true;
			}
		}
		return false;
	}
	
	
	public void checkReset()
	{
		if(win == true || (checkSlotsAvailable() == false && win == false))
		{		
			Popup popup = new Popup(true,frame);	
		}
	}
	
}
