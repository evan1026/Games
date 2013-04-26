package org.noip.evan1026.classes.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import org.noip.evan1026.GlobalVars;
import org.noip.evan1026.classes.Block;

public class GamePanel extends JPanel {
	
	private Block[][] blocks = new Block[GlobalVars.NUM_COLUMNS][GlobalVars.NUM_ROWS];

	private int frameRate = 30;
	
	public int blockWidth;
	public int blockHeight;
	
	ActionListener timerListener = new ActionListener() {
										@Override
										public void actionPerformed(ActionEvent arg0) {
											drawRecs();
										}
								   };
								   
	Timer t = new Timer(1000/frameRate, timerListener);

	public GamePanel(){
		for (int i = 0; i < GlobalVars.NUM_COLUMNS; i++){
			for (int j = 0; j < GlobalVars.NUM_ROWS; j++){
				if (j < GlobalVars.NUM_ROWS - GlobalVars.ROWS_OF_BLOCKS){
					blocks[i][j] = new Block(Color.BLACK,  false);
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
						newColor = Color.ORANGE;
						break;
					}
					
					blocks[i][j] = new Block(newColor, true);
				}
			}
		}
	}

	public void start(){
		
		t.start();
	}
	public void drawRecs(){

		if(getGraphics() == null || getWidth() == 0 || getHeight() == 0){
			return;
		}
		if (blockWidth == 0 || blockHeight == 0){
			blockWidth = (int)Math.round((double)getWidth() / GlobalVars.NUM_COLUMNS);
			blockHeight = (int)Math.round((double)getHeight() / GlobalVars.NUM_ROWS);
		}
		
		BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();

		for (int i = 0; i < GlobalVars.NUM_COLUMNS; i++){
			for (int j = 0; j < GlobalVars.NUM_ROWS; j++){
				if (blocks[i][j].getOccupied()){
					g.setColor(blocks[i][j].getColor());
					g.fill3DRect((int)Math.round(i * blockWidth), (int)Math.round(j * blockHeight), (int)Math.round(blockWidth), (int)Math.round(blockHeight), true);
				}
				
			}
			
		}

		g = this.getGraphics();
		g.drawImage(image, 0, 0, null);
	}
	
	public Block[][] getBlocks(){
		return blocks;
	}
}
