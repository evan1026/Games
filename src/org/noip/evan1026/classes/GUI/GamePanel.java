package org.noip.evan1026.classes.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;

import org.noip.evan1026.classes.Block;

public class GamePanel extends JPanel {

	private Block[][] blocks = new Block[9][9];

	private int frameRate = 30;
	
	Timer t = new Timer(1000/frameRate, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			drawRecs();
		}
	});

	public GamePanel(){
		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 9; j++){
				blocks[i][j] = new Block();
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

		BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();

		double xStep = getWidth() / 9,
			   yStep = getHeight() / 9;

		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 9; j++){
				if (blocks[i][j].getState()){
					g.setColor(Color.RED);
				}
				else{
					g.setColor(Color.GREEN);
				}
				g.fillRect((int)(i * xStep), (int)(j * yStep), (int)xStep, (int)yStep);
				
			}
			
		}

		g = this.getGraphics();
		g.drawImage(image, 0, 0, null);
	}
	
	public Block[][] getBlocks(){
		return blocks;
	}
}
