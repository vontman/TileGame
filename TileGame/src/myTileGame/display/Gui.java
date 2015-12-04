package myTileGame.display;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import myTileGame.Handler;
import myTileGame.engine.Game;

public class Gui {
	private int height;
	private int width;
	private JFrame frame;
	private Canvas canvas;
	private Handler handler;
	public Gui(Handler handler){
		this.handler = handler;
		this.height = handler.getGame().getHeight();
		this.width = handler.getGame().getWidth();
		init();
	}	
	private void init(){
		frame = new JFrame(Game.GAME_NAME);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(handler.getGame().getKeyManager());
		
		canvas = new Canvas();
		frame.setSize(width, height);
		canvas.setPreferredSize(new Dimension(width,height));
		canvas.setMinimumSize(new Dimension(width,height));
		canvas.setMaximumSize(new Dimension(width,height));
		canvas.setFocusable(false);
		frame.add(canvas);
		
		frame.pack();
		
		frame.setVisible(true);
	}
	public Graphics getGraphics(){
		return canvas.getGraphics();
	}
	public BufferStrategy getBufferStrategy(){
		return canvas.getBufferStrategy();
	}
}
