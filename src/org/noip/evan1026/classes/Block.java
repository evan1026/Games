package org.noip.evan1026.classes;

import java.awt.Color;

public class Block {
	
	private ColorState _state;
	
	public Block(Color color, boolean occupied){
		_state = new ColorState(color, occupied);
	}
	
	public ColorState getState(){
		return _state;
	}
	
	public Color getColor(){
		return _state.getColor();
	}
	
	public boolean getOccupied(){
		return _state.getOccupied();
	}
	
	public void setState(ColorState state){
		_state = state;
	}
	
	public void setColor(Color color){
		_state.setColor(color);
	}
	
	public void setOccupied(boolean occupied){
		_state.setOccupied(occupied);
	}
	
	
}
