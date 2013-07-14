package org.noip.evan1026.Breakout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Ball {
    
	public static final int DIAMETER = 10;
	public static final int RADIUS   = DIAMETER / 2;
	public static final int SPEED    = 10;
	
	//Just because there is no provided method to test collisions with lines.
	//With this, I can make a rectangle so thin, it's basically a line.
	private static final double SMALL_NUMBER = 0.001;
	
	private Dimension _gameBounds;
	
	private double _velX,
	               _velY,
	               _posX,
	               _posY;
	
    public Ball(double posX, double posY, double velX, double velY, Dimension gameBounds){
        _posX = posX;
        _posY = posY;
        
        double velMag = Math.abs(Point2D.distance(velX, velY, 0, 0));
        if (velMag == 0){
        	_velX = 0;
        	_velY = 0;
        }
        else{
        	_velX = velX / velMag * SPEED;
        	_velY = velY / velMag * SPEED;
        }
        
        _gameBounds = gameBounds;

    }
    
    public void render(Graphics g){
    	Color prevColor = g.getColor();
    	g.setColor(Color.RED);
    	g.fillOval((int)_posX, (int)_posY, DIAMETER, DIAMETER);
    	g.setColor(prevColor);
    }

    public void update(Rectangle paddleRec, ArrayList<Block> blocks){
    	
    	boolean posHandled = false;
    	
    	if (isColliding(paddleRec)){
    		//TODO sides for pos handling
    		Line2D.Double vec = getBounceVector(paddleRec);
    		double vecMag = Math.abs(Point2D.distance(vec.getX1(), vec.getY1(), vec.getX2(), vec.getY2()));
    		_velX = (vec.getX2() - vec.getX1()) / vecMag * SPEED;
    		_velY = (vec.getY2() - vec.getY1()) / vecMag * SPEED;
    		_posY = paddleRec.getY() - DIAMETER;
    		posHandled = true;
    	}
    	
    	for (int i = 0; i < blocks.size(); i++){
    		Rectangle blockRec = blocks.get(i).getRectangle();
    		if (isColliding(blockRec)){
    			if (blocks.get(i).hit()) i--;
    			
    			//Top side
    			if (isColliding(new Rectangle2D.Double(blockRec.getX(), blockRec.getY(), blockRec.getWidth(), SMALL_NUMBER))){
    				_velY = -_velY;
    				_posY = blockRec.getY() - DIAMETER;
    				posHandled = true;
    			}
    			//Blttom side
    			if (isColliding(new Rectangle2D.Double(blockRec.getX(), blockRec.getMaxY(), blockRec.getWidth(), SMALL_NUMBER))){
    				_velY = -_velY;
    				_posY = blockRec.getMaxY();
    				posHandled = true;
    			}
    			//Left side
    			if (isColliding(new Rectangle2D.Double(blockRec.getX(), blockRec.getY(), SMALL_NUMBER, blockRec.getHeight()))){
    				_velX = -_velX;
    				_posY = blockRec.getX() - DIAMETER;
    				posHandled = true;
    			}
    			//Right side
    			if (isColliding(new Rectangle2D.Double(blockRec.getMaxX(), blockRec.getY(), SMALL_NUMBER, blockRec.getHeight()))){
    				_velX = -_velX;
    				_posY = blockRec.getMaxX();
    				posHandled = true;
    			}
    		}
    	}
    	
    	if (_posX < 0 ){
    		_velX = -_velX;
    		_posX = 0;
    		posHandled = true;
    	}
    	else if (_posX + DIAMETER > _gameBounds.width){
    		_velX = -_velX;
    		_posX = _gameBounds.width - DIAMETER;
    		posHandled = true;
    	}
    	if (_posY < 0) {
    		_velY = -_velY;
    		_posY = 0;
    		posHandled = true;
    	}
    	
    	if (!posHandled){
    		_posX += _velX;
    		_posY += _velY;
    	}
        
    }
    
    public boolean isColliding(Rectangle rec){
    	Ellipse2D.Double thisBall = new Ellipse2D.Double(_posX, _posY, DIAMETER, DIAMETER);
    	return thisBall.intersects(rec);
    }
    
    public boolean isColliding(Rectangle2D rec){
    	Ellipse2D.Double thisBall = new Ellipse2D.Double(_posX, _posY, DIAMETER, DIAMETER);
    	return thisBall.intersects(rec);
    }
    
    public Line2D.Double getBounceVector(Rectangle rec){
    	return new Line2D.Double(new Point2D.Double(rec.getCenterX(), rec.getCenterY()), new Point2D.Double(_posX + RADIUS, _posY + RADIUS));
    }
}
