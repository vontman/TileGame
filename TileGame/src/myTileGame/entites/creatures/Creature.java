package myTileGame.entites.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import myTileGame.Handler;
import myTileGame.entites.Entity;
import myTileGame.gfx.Animation;

public abstract class Creature extends Entity{
	protected Animation upAnimation;
	protected Animation downAnimation;
	protected Animation leftAnimation;
	protected Animation rightAnimation;
	protected int state;
	protected int counterState;
	protected int speed;
	protected boolean moving;
	public Creature(Handler handler, float x, float y,int width,int height,int speed,BufferedImage[] upImg , BufferedImage[] downImg , BufferedImage[] leftImg , BufferedImage[] rightImg ) {
		super(handler, x, y,width,height);
		this.speed = speed;
		upAnimation = new Animation(upImg);
		downAnimation = new Animation(downImg);
		leftAnimation = new Animation(leftImg);
		rightAnimation = new Animation(rightImg);
	}
	public Animation getCurrAnimation(){
		if( state == 1 )
			return upAnimation;
		if( state == 2 )
			return downAnimation;
		if( state == 3 )
			return leftAnimation;
		if( state == 4 )
			return rightAnimation;
		return downAnimation;
	}
	public void render(Graphics g,float xOffset,float yOffset){
		g.fillOval((int)(x+width/4-xOffset), (int)(y+height-10-yOffset), width/2, 10);
		g.drawImage(getCurrAnimation().getCurrentImage(moving), (int)(x-xOffset), (int)(y-yOffset),width,height, null);
	}
	
}
