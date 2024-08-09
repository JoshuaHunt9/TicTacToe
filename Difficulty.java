/**
 * 
 */
package packageBox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 */


public class Difficulty implements ActionListener
{
	Random random = new Random(); //determines who goes first
	JFrame frame = new JFrame(); //makes the JFrame
	JPanel title_panel = new JPanel(); //text slot
	JPanel button_panel = new JPanel(); //allows specific layout of buttons
	JLabel textfield = new JLabel(); //text at the top
	JButton[] buttons = new JButton[3]; //the actual buttons
	
	public Difficulty()
	{
		makeFrame();
	}
	
	public void makeFrame()
	{	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //program closes when top right X button is closed
		frame.setSize(960,540); //sets dimensions of panel
		frame.getContentPane().setBackground(new Color(33,41,48)); //sets background color using rgb values
		frame.setLayout(new BorderLayout());
		frame.setVisible(true); //allows frame to be seen
		
		frame.add(button_panel);
		frame.add(title_panel);
		
		//sets the text
		textfield.setBackground(new Color(25,25,25));
		textfield.setForeground(new Color(25,255,0));
		textfield.setFont(new Font("Ariel",Font.BOLD,70));
		textfield.setHorizontalAlignment(JLabel.CENTER);
		textfield.setText("CHOOSE YOUR DIFFICULTY");
		textfield.setOpaque(true);
		
		
		button_panel.setBounds(0,0,960,540);
		
		title_panel.setBounds(0,0,960,540);
		title_panel.add(textfield);
		
		
		for(int i = 0; i < buttons.length; i++)
		{
			buttons[i] = new JButton();			
			button_panel.add(buttons[i]);
			buttons[i].addActionListener(this);
		}
				
		buttons[0].setText("EASY");
		buttons[0].setBounds(150,100,200,200);
		buttons[0].setSize(350,100);
		buttons[0].setLocation(100,100);
		
		
		buttons[1].setText("MEDIUM");
		buttons[1].setBounds(150,100,200,200);
		buttons[1].setSize(350,100);
		buttons[1].setLocation(200,200);
		
		
		buttons[2].setText("HARD");
		buttons[2].setBounds(150,100,200,200);
		buttons[2].setSize(350,100);
		buttons[2].setLocation(300,300);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		TicTacToe game;
		
		for(int i = 0; i < buttons.length; i++)
		{
			if(e.getSource() == buttons[i])
			{
				if(buttons[i].getText() == "EASY")
				{
					game = new TicTacToe(0);
					game.firstMove(); 
				}
				if(buttons[i].getText() == "MEDIUM")
				{
					game = new TicTacToe(1);
					game.firstMove(); 
				}
				if(buttons[i].getText() == "HARD")
				{
					game = new TicTacToe(4);
					game.firstMove(); 
				}
				frame.dispose(); 
			}
		}
	}
	
	
}
