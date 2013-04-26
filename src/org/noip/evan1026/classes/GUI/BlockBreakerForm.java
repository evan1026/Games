package org.noip.evan1026.classes.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;

import org.noip.evan1026.GlobalVars;
import org.noip.evan1026.classes.Block;

public class BlockBreakerForm extends JFrame implements WindowListener, MouseListener {

	GamePanel game = new GamePanel();

	public BlockBreakerForm(){
		addWindowListener(this);
		game.addMouseListener(this);
		setResizable(false);

		int width = GlobalVars.BLOCK_WIDTH * GlobalVars.NUM_COLUMNS;
		int height = GlobalVars.BLOCK_HEIGHT * GlobalVars.NUM_ROWS;
		
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

	public void removeAdjacent(Block[][] blocks, int x, int y){
		blocks[x][y].setState(false);

		if(x + 1 < GlobalVars.NUM_COLUMNS && blocks[x + 1][y].getState()){
			removeAdjacent(blocks, x + 1, y);
		}
		
		if(x - 1 >= 0 && blocks[x - 1][y].getState()){
			removeAdjacent(blocks, x - 1, y);
		}
		
		if(y + 1 < GlobalVars.NUM_ROWS && blocks[x][y + 1].getState()){
			removeAdjacent(blocks, x, y + 1);
		}
		
		if(y - 1 >= 0 && blocks[x][y - 1].getState()){
			removeAdjacent(blocks, x, y - 1);
		}
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		game.t.stop();
		this.dispose();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getComponent().equals(game)){
			int x = arg0.getX() / game.blockWidth;
			int y = arg0.getY() / game.blockHeight;

			if (game.getBlocks()[x][y].getState()){
				removeAdjacent(game.getBlocks(), x, y);
			}

		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

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
