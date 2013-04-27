package org.noip.evan1026.classes.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;

public class BlockBreakerForm extends JFrame implements WindowListener {

	private static final long serialVersionUID = 962271559678577481L;

	BlockBreakerPanel game = new BlockBreakerPanel();

	public BlockBreakerForm(){
		addWindowListener(this);
		setResizable(false);

		int width  = BlockBreakerPanel.BLOCK_WIDTH  * BlockBreakerPanel.NUM_COLUMNS;
		int height = BlockBreakerPanel.BLOCK_HEIGHT * BlockBreakerPanel.NUM_ROWS;
		
		Dimension dimension = new Dimension(width, height);
		
		game.setPreferredSize(dimension);
		game.setMinimumSize(dimension);
		game.setMaximumSize(dimension);
		
		
		add(game, BorderLayout.CENTER);
		pack();
		
	}

	public void start(){
		game.start();
	}

	

	@Override
	public void windowClosing(WindowEvent arg0) {
		game.t.stop();
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
