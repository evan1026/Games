package org.noip.evan1026;

import java.awt.BorderLayout;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;

import org.noip.evan1026.BlockBreaker.BlockBreakerPanel;
import org.noip.evan1026.Breakout.BreakoutPanel;

public class GameForm extends JFrame implements WindowListener {

	private static final long serialVersionUID = 962271559678577481L;
	
	public static final int GAME_BLOCK_BREAKER = 0;
	public static final int GAME_BREAKOUT      = 1;

	private GamePanel mainPanel;

	public GameForm(){
		this(GAME_BLOCK_BREAKER);
	}
	
	public GameForm(int game){
		addWindowListener(this);
		setResizable(false);
		setVisible(true);

		if (game == GAME_BLOCK_BREAKER){
			mainPanel = new BlockBreakerPanel();
		}
		else if (game == GAME_BREAKOUT){
		    mainPanel = new BreakoutPanel();
		}
		
		add(mainPanel, BorderLayout.CENTER);
		
		pack();
		
		if (mainPanel instanceof KeyListener){
		    addKeyListener((KeyListener) mainPanel);
		}
	}

	public void start(){
		mainPanel.start();
	}

	
	@Override
	public void windowClosing(WindowEvent arg0) {
		mainPanel.stop();
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
