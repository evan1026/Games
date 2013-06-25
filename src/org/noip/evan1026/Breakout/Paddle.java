package org.noip.evan1026.Breakout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Paddle {
    
    public static final int SPEED_LEFT  = -10;
    public static final int SPEED_RIGHT =  10;
    
    private Rectangle _rec = new Rectangle();
    
    private double _gameWidth;
    
    public Paddle(double gameWidth){
        _rec.setBounds(100, 450, 100, 10);
        _gameWidth = gameWidth;
    }
    
    public void move(int speed){
    	double newX = _rec.getLocation().getX() + speed;
        if(newX >= 0 && newX <= _gameWidth - _rec.getWidth()) _rec.setLocation((int)(_rec.getLocation().getX() + speed), (int)_rec.getLocation().getY());
    }
    
    public Rectangle getRectangle(){
        return _rec;
    }
    
    public void render (Graphics g){
    	Color prevColor = g.getColor();
    	g.setColor(Color.WHITE);
    	g.fillRect((int)_rec.getX(), (int)_rec.getY(), (int)_rec.getWidth(), (int)_rec.getHeight());
    	g.setColor(prevColor);
    }
    
    public void update(boolean leftDown, boolean rightDown){
    	move((leftDown ? SPEED_LEFT : 0) + (rightDown ? SPEED_RIGHT : 0));
    }
}
