/**
 * 
 */
package packageBox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 
 */
public class Popup implements ActionListener
{
	JFrame gameFrame;
	
	JFrame popFrame = new JFrame();
	
	JButton rematch = new JButton();
	JButton quit = new JButton();
	
	JLabel textfield = new JLabel();
	JLabel title_panel = new JLabel();
	
	boolean win;	
	
	public Popup(boolean win, JFrame frame)
	{	
		gameFrame = frame;
		
		this.win = win;
		
		popFrame.add(rematch);
		popFrame.add(quit);
		
		popFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //program closes when top right X button is closed
		popFrame.setSize(960,540); //sets dimensions of panel
		popFrame.getContentPane().setBackground(new Color(33,41,48)); //sets background color using rgb values
		popFrame.setLayout(new BorderLayout());
		popFrame.setVisible(true); //allows frame to be seen
		
		textfield.setBackground(new Color(25,25,25));
		textfield.setForeground(new Color(25,255,0));
		textfield.setFont(new Font("Ariel",Font.BOLD,85));
		textfield.setHorizontalAlignment(JLabel.CENTER);
		textfield.setOpaque(true);
		
		title_panel.add(rematch);
		title_panel.add(quit);
		popFrame.add(title_panel);
		
		rematch.addActionListener(this);
		quit.addActionListener(this);
		
		popFrame.setBounds(200,200,960,540);
		
		rematch.setBounds(150,100,200,200);
		rematch.setText("REMATCH");
		rematch.setForeground(Color.BLACK);
		
		quit.setBounds(600,100,200,200);
		quit.setText("QUIT");
		quit.setForeground(Color.BLACK);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == rematch)
		{			
			gameFrame.dispose();
			popFrame.dispose();
			
			Difficulty setting = new Difficulty();
		}
		else
		{
			gameFrame.dispose();
			popFrame.dispose();
		}
	}
}
