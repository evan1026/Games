package org.noip.evan1026.classes.GUI.gamePanels;

import javax.swing.JPanel;

public abstract class GamePanel extends JPanel {

	private static final long serialVersionUID = -2317129671409885705L;
	
	public abstract void start();
	public abstract void stop();
}
