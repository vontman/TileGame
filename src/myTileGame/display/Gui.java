package myTileGame.display;

import java.awt.Canvas;
import java.awt.Dimension;

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
		this.height = handler.getGameHeight();
		this.width = handler.getGameWidth();
		init();
	}	
	private void init(){
		frame = new JFrame(Game.GAME_NAME);
		frame.addKeyListener(handler.getGame().getKeyManager());
		
		canvas = new Canvas();
		canvas.setFocusable(false);
		frame.setResizable(false);
		frame.add(canvas);

		setSize(width,height);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	public Canvas getCanvas(){
		return canvas;
	}
	private void setSize(int width,int height){
		canvas.setPreferredSize(new Dimension(width,height));
		canvas.setMinimumSize(new Dimension(width,height));
		canvas.setMaximumSize(new Dimension(width,height));


		frame.pack();
		frame.setLocationRelativeTo(null);
	}
}
