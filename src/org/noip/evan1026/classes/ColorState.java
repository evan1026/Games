package org.noip.evan1026.classes;

import java.awt.Color;

public class ColorState {
	
	private boolean _occupied;
	private Color   _color;
	
	public ColorState(Color color, boolean occupied){
		_occupied = occupied;
		_color = color;
	}
	
	public Color getColor(){
		return _color;
	}
	
	public boolean getOccupied(){
		return _occupied;
	}
	
	public void setColor(Color color){
		_color = color;
	}
	
	public void setOccupied(boolean occupied){
		_occupied = occupied;
	}
}
