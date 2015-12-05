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
	public Creature(Handler handler, float x, float y,int speed,BufferedImage[] upImg , BufferedImage[] downImg , BufferedImage[] leftImg , BufferedImage[] rightImg ) {
		super(handler, x, y);
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
	public void render(Graphics g){
		g.drawImage(getCurrAnimation().getCurrentImage(), (int)x, (int)y, null);
	}
	
}
