package org.noip.evan1026;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;

import org.noip.evan1026.BlockBreaker.BlockBreakerPanel;

public class GameForm extends JFrame implements WindowListener {

	private static final long serialVersionUID = 962271559678577481L;

	private GamePanel mainPanel;

	public GameForm(){
		this("BlockBreaker");
	}
	
	public GameForm(String gameName){
		addWindowListener(this);
		setResizable(false);
		setVisible(true);

		if (gameName.equals("BlockBreaker")){
			mainPanel = new BlockBreakerPanel();
		}
		
		add(mainPanel, BorderLayout.CENTER);
		pack();
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
