package myTileGame.entites.objects.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import myTileGame.Handler;
import myTileGame.gfx.Animation;
import myTileGame.objects.entites.Entity;

public abstract class Creature extends Entity{
	public static final int ATTACK_INTERVAL = 300;
	protected Animation upAnimation;
	protected Animation downAnimation;
	protected Animation leftAnimation;
	protected Animation rightAnimation;
	protected int state = 3;
	protected int counterState;
	protected int speed;
	protected int hp;
	protected int fullHp;
	protected boolean moving;
	protected boolean attacking;
	protected boolean isAttacked;
	protected long lastAttacked;
	public Creature(Handler handler,float x, float y,int width,int height,int speed,int hp,int boundX ,int boundY , int boundWidth,int boundHeight,BufferedImage[] upImg , BufferedImage[] downImg , BufferedImage[] leftImg , BufferedImage[] rightImg ) {
		super(handler,x, y,width,height, boundX, boundY, boundWidth, boundHeight);
		this.speed = speed;
		this.hp = hp;
		this.fullHp = hp;
		upAnimation = new Animation(upImg);
		downAnimation = new Animation(downImg);
		leftAnimation = new Animation(leftImg);
		rightAnimation = new Animation(rightImg);
		attacking = false;
		isAttacked = false;
		state = 0;
	}
	public void getAttacked(int dmg){
		if( System.currentTimeMillis() - lastAttacked >= ATTACK_INTERVAL )
			isAttacked = false;
		if(isAttacked)
			return;
		hp -= dmg;
		isAttacked = true;
		lastAttacked = System.currentTimeMillis();
		if(hp < 0)
			hp = 0;
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
		//hp bar
		g.setColor(Color.green);
		g.drawRect((int)(x-xOffset), (int)(y-5-yOffset), width, 4);
		g.setColor(Color.red);
		g.fillRect((int)(x-xOffset), (int)(y-4-yOffset), width, 3);
		g.setColor(Color.green);
		g.fillRect((int)(x-xOffset), (int)(y-4-yOffset), (int)(1D*width/fullHp*hp), 3);
		
		//shadow
		g.setColor(Color.BLACK);
		g.fillOval((int)(x+width/4-xOffset), (int)(y+height-10-yOffset), width/2, 10);
		
		
		g.drawImage(getCurrAnimation().getCurrentImage(moving), (int)(x-xOffset), (int)(y-yOffset),width,height, null);
		
		//bounds
//		g.drawRect((int)(x-xOffset+bounds.x), (int)(y-yOffset+bounds.y),bounds.width,bounds.height);
	}
	
}
