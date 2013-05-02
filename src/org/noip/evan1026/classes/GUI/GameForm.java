package org.noip.evan1026.classes.GUI;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import org.noip.evan1026.classes.GUI.gamePanels.*;

public class GameForm extends JFrame implements WindowListener {

	private static final long serialVersionUID = 962271559678577481L;

	GamePanel game;

	public GameForm(){
		this("BlockBreaker");
	}
	
	public GameForm(String gameName){
		addWindowListener(this);
		setResizable(false);
		setVisible(true);

		if (gameName.equals("BlockBreaker")){
			game = new BlockBreakerPanel();
		}
		
		add(game, BorderLayout.SOUTH);
		pack();
	}

	public void start(){
		game.start();
	}

	
	@Override
	public void windowClosing(WindowEvent arg0) {
		game.stop();
		this.dispose();
	}
	
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

}
