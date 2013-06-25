package org.noip.evan1026;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public abstract class GamePanel extends JPanel {

	private static final long serialVersionUID = -2317129671409885705L;
	
	private ActionListener timerListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			update();
		}
    };
   
   	private Timer _t = new Timer(1000 / getFPS(), timerListener);
	
	public abstract void start();
	public abstract void stop();
	public abstract void update();
	public abstract int getFPS();
	
	public void startTimer(){
		_t.start();
	}
	public void stopTimer(){
		_t.stop();
	}
}
