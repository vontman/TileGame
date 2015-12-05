package myTileGame.entites.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import myTileGame.Handler;
import myTileGame.entites.Entity;

public abstract class Creature extends Entity{
	protected BufferedImage upImg;
	protected BufferedImage downImg;
	protected BufferedImage leftImg;
	protected BufferedImage rightImg;
	protected int state;
	protected int counterState;
	protected int speed;
	public Creature(Handler handler, float x, float y,int speed) {
		super(handler, x, y);
		this.speed = speed;
	}
	public BufferedImage getCurrImage(){
		if( state == 1 )
			return upImg;
		if( state == 2 )
			return downImg;
		if( state == 3 )
			return leftImg;
		if( state == 4 )
			return rightImg;
		return downImg;
	}
	public void render(Graphics g){
		g.drawImage(getCurrImage(), (int)x, (int)y, null);
	}
	
}
