package org.noip.evan1026.classes.GUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;

import org.noip.evan1026.classes.Block;

public class BlockBreakerForm extends JFrame implements WindowListener, MouseListener {

	GamePanel game = new GamePanel();

	public BlockBreakerForm(int width, int height){
		addWindowListener(this);
		game.addMouseListener(this);
		setSize(width, height);
		setResizable(false);
		setLayout(null);

		add(game);
		int gameX = getWidth() - 50,
				gameY = getHeight() - 50;
		game.setSize(gameX - (gameX % 9), gameY - (gameY % 9));
		game.setLocation(0, 0);
	}

	public void start(){
		game.start();
	}

	public void removeAdjacent(Block[][] blocks, int x, int y){
		if(x + 1 < 9 && blocks[x + 1][y].getState()){
			blocks[x + 1][y].setState(false);
			removeAdjacent(blocks, x + 1, y);
		}
		if(x - 1 > 0 && blocks[x - 1][y].getState()){
			blocks[x - 1][y].setState(false);
			removeAdjacent(blocks, x - 1, y);
		}
		if(y + 1 < 9 && blocks[x][y + 1].getState()){
			blocks[x][y + 1].setState(false);
			removeAdjacent(blocks, x, y + 1);
		}
		if(y - 1 > 0 && blocks[x][y - 1].getState()){
			blocks[x][y - 1].setState(false);
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
			int x = arg0.getX() / (getWidth() / 9);
			int y = arg0.getY() / (getHeight() / 9);
			
			System.out.println(x + " " + y);

			if (game.getBlocks()[x][y].getState()){
				game.getBlocks()[x][y].setState(false);
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
