package org.noip.evan1026.classes.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import org.noip.evan1026.classes.Block;

public class BlockBreakerPanel extends JPanel implements MouseListener {
	
	public static final int NUM_ROWS       = 30;
	public static final int NUM_COLUMNS    = 45;
	public static final int BLOCK_WIDTH    = 20;
	public static final int BLOCK_HEIGHT   = 20;
	public static final int ROWS_OF_BLOCKS = 30;
	public static final int FPS            = 30;
	
	private static final long serialVersionUID = 9202791160605884542L;

	private Block[][] _blocks = new Block[NUM_COLUMNS][NUM_ROWS];
	
	ActionListener timerListener = new ActionListener() {
										@Override
										public void actionPerformed(ActionEvent arg0) {
											drawRecs();
										}
								   };
								   
	Timer t = new Timer(1000 / FPS, timerListener);

	public BlockBreakerPanel(){
		
		addMouseListener(this);
		
		for (int i = 0; i < NUM_COLUMNS; i++){
			for (int j = 0; j < NUM_ROWS; j++){
				
				if (j < NUM_ROWS - ROWS_OF_BLOCKS){
					_blocks[i][j] = new Block(Color.BLACK,  false);
				}
				else{
					Random rand = new Random();
					Color newColor = null;
					
					switch(rand.nextInt(4)){
					case 0:
						newColor = Color.RED;
						break;
					case 1:
						newColor = Color.GREEN;
						break;
					case 2:
						newColor = Color.BLUE;
						break;
					case 3:
						newColor = Color.YELLOW;
						break;
					}
					
					_blocks[i][j] = new Block(newColor, true);
				}
			}
		}
	}

	public void start(){
		t.start();
	}
	
	private void drawRecs(){

		if(getGraphics() == null || getWidth() == 0 || getHeight() == 0){
			return;
		}
		
		BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();

		for (int i = 0; i < NUM_COLUMNS; i++){
			for (int j = 0; j < NUM_ROWS; j++){
				
				if (_blocks[i][j].getOccupied()){
					g.setColor(_blocks[i][j].getColor());
					g.fill3DRect(i * BLOCK_WIDTH, j * BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT, true);
				}
			}
		}

		g = this.getGraphics();
		g.drawImage(image, 0, 0, null);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getComponent().equals(this)){
			int x = arg0.getX() / BLOCK_WIDTH;
			int y = arg0.getY() / BLOCK_HEIGHT;

			ArrayList<Block> blocksToRemove = new ArrayList<Block>();
			if (_blocks[x][y].getOccupied()){
				markToRemove(x, y, blocksToRemove);
			}
			
			if (blocksToRemove.size() > 2){
				removeBlocks(blocksToRemove);
				makeBlocksFall();
				collapseToCenter();
			}

		}

	}
	
	private void markToRemove(int x, int y, ArrayList<Block> removeList){
		removeList.add(_blocks[x][y]);

		if(x + 1 < NUM_COLUMNS && _blocks[x + 1][y].getColor().equals(_blocks[x][y].getColor()) && !removeList.contains(_blocks[x + 1][y]) && _blocks[x + 1][y].getOccupied()){
			markToRemove(x + 1, y, removeList);
		}
		
		if(x - 1 >= 0 && _blocks[x - 1][y].getColor().equals(_blocks[x][y].getColor()) && !removeList.contains(_blocks[x - 1][y]) && _blocks[x - 1][y].getOccupied()){
			markToRemove(x - 1, y, removeList);
		}
		
		if(y + 1 < NUM_ROWS && _blocks[x][y + 1].getColor().equals(_blocks[x][y].getColor()) && !removeList.contains(_blocks[x][y + 1]) && _blocks[x][y + 1].getOccupied()){
			markToRemove(x, y + 1, removeList);
		}
		
		if(y - 1 >= 0 && _blocks[x][y - 1].getColor().equals(_blocks[x][y].getColor()) && !removeList.contains(_blocks[x][y - 1]) && _blocks[x][y - 1].getOccupied()){
			markToRemove(x, y - 1, removeList);
		}
	}
	
	private void removeBlocks(ArrayList<Block> blocks){
		for (Block b : blocks){
			b.setOccupied(false);
			b.setColor(Color.BLACK);
		}
	}
	
	private void makeBlocksFall(){
		for (int i = NUM_ROWS - 1; i >= 0; i--){
			for (int j = 0; j < NUM_COLUMNS; j++){
				if (_blocks[j][i].getOccupied()){
					for (int k = NUM_ROWS - 1; k > i; k--){
						if (!_blocks[j][k].getOccupied()){
							_blocks[j][k] = _blocks[j][i];
							_blocks[j][i] = new Block(Color.BLACK, false);
							break;
						}
					}
				}
			}
		}
	}
	
	private void collapseToCenter(){

		while (findEmptyColumn() != -1){
			int i = findEmptyColumn();

			if (i < NUM_COLUMNS / 2){
				for (int j = i; j >= 0; j--){
					for (int k = 0; k < NUM_ROWS; k++){
						if (j - 1 >= 0){
							_blocks[j][k] = _blocks[j - 1][k];
						}
						else{
							_blocks[j][k] = new Block(Color.BLACK, false);
						}
					}
				}
			}
			else{
				for (int j = i; j < NUM_COLUMNS; j++){
					for (int k = 0; k < NUM_ROWS; k++){
						if (j + 1 < NUM_COLUMNS) {
							_blocks[j][k] = _blocks[j + 1][k];
						}
						else {
							_blocks[j][k] = new Block(Color.BLACK, false);
						}
					}
				}
			}
		}
	}

	private boolean emptyColumn(int index){
		for (int j = 0; j < NUM_ROWS; j++){
			if (_blocks[index][j].getOccupied()){
				return false;
			}
		}
		
		return true;
	}
	
	private int findEmptyColumn(){
		
		for (int i = 0; i < NUM_COLUMNS; i++){
			if (emptyColumn(i) && columnHasOccupiedNeighbors(i)) {
				return i;
			}
		}
		
		return -1;
	}
	
	private boolean columnHasOccupiedNeighbors(int index){
		
		boolean leftSide  = false, 
				rightSide = false;
		for (int i = 0; i < index; i++){
			if (!emptyColumn(i)){
				leftSide = true;
				break;
			}
		}
		for (int i = index + 1; i < NUM_COLUMNS; i++){
			if (!emptyColumn(i)){
				rightSide = true;
				break;
			}
		}
		
		return (rightSide && leftSide);
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
}
