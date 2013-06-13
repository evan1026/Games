package org.noip.evan1026.Breakout;

import java.awt.Rectangle;


public class Paddle {
    
    public static final int DIRECTION_LEFT  = -40;
    public static final int DIRECTION_RIGHT =  40;
    
    private Rectangle _rec = new Rectangle();
    
    public Paddle(){
        _rec.setBounds(100, 100, 100, 20);
    }
    
    public void move(int direction){
        _rec.setLocation((int)(_rec.getLocation().getX() + direction), (int)_rec.getLocation().getY());
    }
    
    public Rectangle getRectangle(){
        return _rec;
    }
}
