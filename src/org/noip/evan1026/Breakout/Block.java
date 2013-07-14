package org.noip.evan1026.Breakout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Block {
	
	public static final int WIDTH  = 25;
	public static final int HEIGHT = 10;
	
	private Color     _color;
	private Rectangle _rec;
	
	private BreakoutPanel _parent;
	
	public Block(Color color, Rectangle rec, BreakoutPanel parent){
		_color  = color;
		_rec    = rec;
		_parent = parent;
	}
	
	public Block(Rectangle rec, BreakoutPanel parent){
		this(Color.YELLOW, rec, parent);
	}
	
	public Block(Color color, Point position, BreakoutPanel parent){
		Rectangle rec = new Rectangle(WIDTH, HEIGHT);
		rec.setLocation(position);
		
		_rec    = rec;
		_color  = color;
		_parent = parent;
	}
	
	public Block(Point position, BreakoutPanel parent){
		this(Color.YELLOW, position, parent);
	}
	
	public void render(Graphics g){
		Color prevColor = g.getColor();
		g.setColor(_color);
		g.fill3DRect((int)_rec.getX(), (int)_rec.getY(), (int)_rec.getWidth(), (int)_rec.getHeight(), true);
		g.setColor(prevColor);
	}
	
	public Rectangle getRectangle(){
		return _rec;
	}
	
	public Color getColor(){
		return _color;
	}
	
	public boolean hit(){
		if (_color.equals(Color.YELLOW)){
			_color = Color.RED;
		}
		else {
			_parent.pleaseRemove(this);
			return true;
		}
		return false;
	}

}
