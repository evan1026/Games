package org.noip.evan1026.Breakout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Ball {
    
	public static final int DIAMETER = 10;
	public static final int RADIUS   = DIAMETER / 2;
	public static final int SPEED    = 10;
	
	private Dimension _gameBounds;
	
	private double _velX,
	               _velY,
	               _posX,
	               _posY;
	
    public Ball(double posX, double posY, double velX, double velY, Dimension gameBounds){
        _posX = posX;
        _posY = posY;
        
        double velMag = Math.abs(Point2D.distance(velX, velY, 0, 0));
        _velX = velX / velMag * SPEED;
        _velY = velY / velMag * SPEED;
        
        _gameBounds = gameBounds;
    }
    
    public void render(Graphics g){
    	Color prevColor = g.getColor();
    	g.setColor(Color.RED);
    	g.fillOval((int)_posX, (int)_posY, DIAMETER, DIAMETER);
    	g.setColor(prevColor);
    }

    public void update(Rectangle paddleRec){
    	if (isColliding(paddleRec)){
    		Line2D.Double vec = getBounceVector(paddleRec);
    		double vecMag = Math.abs(Point2D.distance(vec.getX1(), vec.getY1(), vec.getX2(), vec.getY2()));
    		_velX = (vec.getX2() - vec.getX1()) / vecMag * SPEED;
    		_velY = (vec.getY2() - vec.getY1()) / vecMag * SPEED;
    	}
    	if (_posX < 0 || _posX + DIAMETER > _gameBounds.width)  _velX = -_velX;
    	if (_posY < 0)                                          _velY = -_velY;
    	
    	_posX += _velX;
    	_posY += _velY;
    }
    
    public boolean isColliding(Rectangle rec){
    	Ellipse2D.Double thisBall = new Ellipse2D.Double(_posX, _posY, DIAMETER, DIAMETER);
    	return thisBall.intersects(rec);
    }
    
    public Line2D.Double getBounceVector(Rectangle rec){
    	return new Line2D.Double(new Point2D.Double(rec.getCenterX(), rec.getCenterY()), new Point2D.Double(_posX + RADIUS, _posY + RADIUS));
    }
}
