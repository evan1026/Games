package org.noip.evan1026.classes.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

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

	public void markToRemove(Block[][] blocks, int x, int y, ArrayList<Block> removeList){
		removeList.add(blocks[x][y]);

		if(x + 1 < GlobalVars.NUM_COLUMNS && blocks[x + 1][y].getColor().equals(blocks[x][y].getColor()) && !removeList.contains(blocks[x + 1][y])){
			markToRemove(blocks, x + 1, y, removeList);
		}
		
		if(x - 1 >= 0 && blocks[x - 1][y].getColor().equals(blocks[x][y].getColor()) && !removeList.contains(blocks[x - 1][y])){
			markToRemove(blocks, x - 1, y, removeList);
		}
		
		if(y + 1 < GlobalVars.NUM_ROWS && blocks[x][y + 1].getColor().equals(blocks[x][y].getColor()) && !removeList.contains(blocks[x][y + 1])){
			markToRemove(blocks, x, y + 1, removeList);
		}
		
		if(y - 1 >= 0 && blocks[x][y - 1].getColor().equals(blocks[x][y].getColor()) && !removeList.contains(blocks[x][y - 1])){
			markToRemove(blocks, x, y - 1, removeList);
		}
	}
	
	public void removeBlocks(ArrayList<Block> blocks){
		for (Block b : blocks){
			b.setOccupied(false);
		}
	}
	
	public void makeBlocksFall(Block[][] blocks){
		for (int i = GlobalVars.NUM_ROWS - 1; i >= 0; i--){
			for (int j = 0; j < GlobalVars.NUM_COLUMNS; j++){
				if (blocks[j][i].getOccupied()){
					for (int k = GlobalVars.NUM_ROWS - 1; k > i; k--){
						if (!blocks[j][k].getOccupied()){
							blocks[j][k] = blocks[j][i];
							blocks[j][i] = new Block(Color.BLACK, false);
							break;
						}
					}
				}
			}
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

			ArrayList<Block> blocksToRemove = new ArrayList<Block>();
			if (game.getBlocks()[x][y].getOccupied()){
				markToRemove(game.getBlocks(), x, y, blocksToRemove);
			}
			
			if (blocksToRemove.size() > 2){
				removeBlocks(blocksToRemove);
				makeBlocksFall(game.getBlocks());
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
