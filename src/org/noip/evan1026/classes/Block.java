package org.noip.evan1026.classes;

import java.util.Random;

public class Block {
	
	private boolean _state;
	
	public Block(){
		Random rand = new Random();
		_state = rand.nextBoolean();
	}
	
	public boolean getState(){
		return _state;
	}
	
	public void setState(boolean state){
		_state = state;
	}
}
