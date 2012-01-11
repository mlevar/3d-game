package gamelogic;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;


public class Meni extends JFrame implements ActionListener {

	JFrame frame;
	JButton buttonExit;
	JButton buttonNewGame;
	JButton buttonHelp;
	JButton buttonResume;
	
	public Meni() {
        // Make the frame
        frame = new JFrame("Menu");
        frame.setSize(200, 400);
        
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        frame.setLayout(gb);
        
        //buttons:
        //new game
        buttonNewGame = new JButton();
        buttonNewGame.setText("New Game");
        c.fill = c.HORIZONTAL;
        c.ipady = 10;
        c.gridx = 0;
        c.gridy = 0;
        gb.setConstraints(buttonNewGame, c);
        
        //exit game
        buttonExit = new JButton();
        buttonExit.setText("Exit Game");
        c.fill = c.HORIZONTAL;
        c.ipady = 10;
        c.gridx = 0;
        c.gridy = 2;
        gb.setConstraints(buttonExit, c);
        buttonExit.addActionListener(this);
        
        //help
        buttonHelp = new JButton();
        buttonHelp.setText("Help");
        //buttonHelp.setSize(80, 30);
        c.fill = c.HORIZONTAL;
        c.ipady = 10;
        c.gridx = 0;
        c.gridy = 1;
        gb.setConstraints(buttonHelp, c);
        
        //resume
        buttonResume = new JButton();
        buttonResume.setText("Resume");
        c.fill = c.HORIZONTAL;
        c.ipady = 10;
        c.gridx = 0;
        c.gridy = 3;
        gb.setConstraints(buttonResume, c);
        buttonResume.addActionListener(this);
        
        frame.add(buttonNewGame);
        frame.add(buttonHelp);
        frame.add(buttonExit);
        frame.add(buttonResume);
        
        frame.setVisible(true);
        
        frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Meni f = new Meni();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == buttonExit) {
			System.out.println("Zapiranje...");
			System.exit(0);
		}
		
		if(e.getSource() == buttonResume) {
			frame.setVisible(false);
			
		}
	}
	
	public void zapriMeni() {
		System.exit(0);
	}

}
