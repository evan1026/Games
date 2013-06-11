package org.noip.evan1026.BlockBreaker;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePane extends JPanel {

	private static final long serialVersionUID = -5133409742605069625L;
	
	JLabel score = new JLabel();
	int points;
	
	public ScorePane(){
		add(score, BorderLayout.CENTER);
	}
	
	public void setScore(int newScore){
		score.setText("Score:   " + newScore);
		points = newScore;
	}
	
	public int getScore(){
		return points;
	}
}
