package org.noip.evan1026.Breakout;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.noip.evan1026.GamePanel;

public class BreakoutPanel extends GamePanel implements KeyListener{
    
	private static final long serialVersionUID = 573060352596076724L;

	public static final int FPS = 30;
	
	private Paddle _paddle;
	private Ball   _ball;
	
	private ArrayList<Block> _blocks = new ArrayList<Block>();
    
	private boolean _leftDown  = false;
	private boolean _rightDown = false;

    public BreakoutPanel(){
        setPreferredSize(new Dimension(700, 500));
        _paddle = new Paddle(getPreferredSize().getWidth());
        _ball = new Ball(_paddle.getRectangle().getX() + _paddle.getRectangle().getWidth() / 2, 
        				 _paddle.getRectangle().getY() - Ball.RADIUS, 
        				 0, 
        				 0, 
        				 getPreferredSize());
        
        for (int i = 0; i < getPreferredSize().getWidth(); i += Block.WIDTH){
        	for (int j = 0; j < getPreferredSize().getHeight() - 300; j += Block.HEIGHT){
        		_blocks.add(new Block(new Point(i, j), this));
        	}
        }
    }
    
    @Override
    public void start() {
    	startTimer();
    }

    @Override
    public void stop() {
    	stopTimer();
    }

	private void render(){
        
        if(getGraphics() == null || getWidth() == 0 || getHeight() == 0){
            return;
        }
        
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        
        _paddle.render(g);
        _ball.render(g);
        
        for (Block b : _blocks){
        	b.render(g);
        }

        g = getGraphics();
        g.drawImage(image, 0, 0, null);
        
    }
	
	@Override
	public void update() {
		_paddle.update(_leftDown, _rightDown);
		_ball.update(_paddle.getRectangle(), _blocks);
		render();
	}

	@Override
	public int getFPS() {
		return FPS;
	}

    @Override
    public void keyPressed(KeyEvent arg0) {
        if (arg0.getKeyCode() == KeyEvent.VK_LEFT){
        	_leftDown = true;
        }
        if (arg0.getKeyCode() == KeyEvent.VK_RIGHT){
        	_rightDown = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        if (arg0.getKeyCode() == KeyEvent.VK_LEFT){
        	_leftDown = false;
        }
        if (arg0.getKeyCode() == KeyEvent.VK_RIGHT){
        	_rightDown = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        
    }
    
    public void pleaseRemove(Block block){
    	_blocks.remove(block);
    }

}
