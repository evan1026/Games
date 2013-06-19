package org.noip.evan1026.Breakout;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

//import org.lwjgl.util.Rectangle;
import org.noip.evan1026.GamePanel;

public class BreakoutPanel extends GamePanel implements KeyListener{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -7853923951523972981L;
	private Paddle _paddle = new Paddle();
    

//    private ActionListener _timerHandler = new ActionListener(){
//                                                @Override
//                                                public void actionPerformed(ActionEvent arg0){
//                                                    if (_canvas.isDisplayable() && !Display.isCreated()){
//                                                        try {
//                                                            Display.setParent(_canvas);
//                                                            Display.create();
//                                                            Keyboard.create();
//                                                        } catch (LWJGLException e) {
//                                                            // TODO Auto-generated catch block
//                                                            e.printStackTrace();
//                                                        }
//                                                    }
//                                                    
//                                                    if(Keyboard.isCreated()){
//                                                        Keyboard.poll();
//                                                        if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
//                                                            moveLeft();
//                                                        }
//                                                
//                                                        if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
//                                                            moveRight();
//                                                        }
//                                                        
//                                                        render();
//                                                    }
//                                                    
//                                                    
//                                                }
//                                           };
                               
//    private Timer _timer = new Timer(50, _timerHandler);
    
    public BreakoutPanel(){
        setPreferredSize(new Dimension(500, 500));
//        _canvas.setPreferredSize(new Dimension(500,500));
//        add(_canvas);
//        _canvas.setVisible(true);
//        _canvas.setEnabled(true);

    }
    
    public void moveLeft(){
        _paddle.move(Paddle.DIRECTION_LEFT);
    }
    
    public void moveRight(){
        _paddle.move(Paddle.DIRECTION_RIGHT);
    }
    
    @Override
    public void start() {
        //_timer.start();
    }

    @Override
    public void stop() {
        //_timer.stop();
    }
    
    @SuppressWarnings("unused")
	private void render(){
        
        if(getGraphics() == null || getWidth() == 0 || getHeight() == 0){
            return;
        }
        
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        
        _paddle.getRectangle();

        g = getGraphics();
        g.drawImage(image, 0, 0, null);
        
//        GL11.glMatrixMode(GL11.GL_PROJECTION);
//        GL11.glLoadIdentity();
//        GL11.glOrtho(0, Display.getDisplayMode().getWidth(), 0, Display.getDisplayMode().getHeight(), -1, 1);
//        GL11.glMatrixMode(GL11.GL_MODELVIEW);
//
//        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_STENCIL_BUFFER_BIT);
//
//        GL11.glPushMatrix();
//        //GL11.glTranslatef(Display.getDisplayMode().getWidth() / 2, Display.getDisplayMode().getHeight() / 2f, 0.0f);
//
//        GL11.glBegin(GL11.GL_QUADS);
//        
//        GL11.glColor3f(1f, 1f, 1f);
//        
//        GL11.glVertex2d(_paddle.getRectangle().getX(), _paddle.getRectangle().getY());
//        GL11.glVertex2d(_paddle.getRectangle().getMaxX(), _paddle.getRectangle().getY());
//        GL11.glVertex2d(_paddle.getRectangle().getMaxX(), _paddle.getRectangle().getMaxY());
//        GL11.glVertex2d(_paddle.getRectangle().getX(), _paddle.getRectangle().getMaxY());
//        GL11.glEnd();
//        GL11.glPopMatrix();
//        Display.sync(60);
//        Display.update();
        
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
        if (arg0.getKeyChar() == KeyEvent.VK_LEFT){
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        if (arg0.getKeyChar() == KeyEvent.VK_LEFT){
        }
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        
    }



}
