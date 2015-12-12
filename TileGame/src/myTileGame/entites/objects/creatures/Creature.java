package myTileGame.entites.objects.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import myTileGame.Handler;
import myTileGame.gfx.Animation;
import myTileGame.gfx.Assets;
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
	protected boolean isSwimming;
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
	public void move(float moveX, float moveY) {
//		if(handler.getGame().getCollisionSensor().moveEntity(this, moveX, moveY)){
//			x += moveX;
//			y += moveY;
//		}
		
		
		//entity collision
		for( Entity e : handler.getEntityManager().getCurrentEntities()){
			if(e.equals(this))
				continue;
			if(checkEntityCollision(e, moveX, 0))
				moveX = 0;
			if(checkEntityCollision(e, moveX, moveY))
				moveY = 0;
		}

		int tx ;
		int ty ;
		//tile collision
		if( moveX > 0 ){
			tx = (int)(x+moveX+bounds.x+bounds.width)/Assets.CELL_WIDTH;
			if(checkTileCollision(tx, (int)(y+bounds.y)/Assets.CELL_HEIGHT) || 
					checkTileCollision(tx, (int)(y+bounds.y+bounds.height)/Assets.CELL_HEIGHT)	){
				x = tx*Assets.CELL_WIDTH - bounds.x - bounds.width-1;
			}else
				x += moveX;
		}else if( moveX < 0 ){
			tx = (int)(x+moveX+bounds.x)/Assets.CELL_WIDTH;
			if(checkTileCollision(tx, (int)(y+bounds.y)/Assets.CELL_HEIGHT) || 
					checkTileCollision(tx, (int)(y+bounds.y+bounds.height)/Assets.CELL_HEIGHT)	){
				x = (tx+1)*Assets.CELL_WIDTH - bounds.x+1;
			}else
				x += moveX;
		}
		if( moveY > 0 ){
			ty = (int)(y+moveY+bounds.y+bounds.height)/Assets.CELL_HEIGHT;
			if(checkTileCollision((int)(x+bounds.x)/Assets.CELL_WIDTH, ty) || 
					checkTileCollision((int)(x+bounds.x+bounds.width)/Assets.CELL_WIDTH,ty)	){
				y = ty*Assets.CELL_HEIGHT - bounds.y - bounds.height-1;
			}else
				y += moveY;
		}else if( moveY < 0 ){
			ty = (int)(y+moveY+bounds.y)/Assets.CELL_HEIGHT;
			if(checkTileCollision((int)(x+bounds.x)/Assets.CELL_WIDTH, ty) || 
					checkTileCollision((int)(x+bounds.x+bounds.width)/Assets.CELL_WIDTH,ty)	){
				y = (ty+1)*Assets.CELL_HEIGHT - bounds.y+1;
			}else 
				y += moveY;
		}
		
		
		
		if(moveX == 0 && moveY == 0)
			moving = false;
		else 
			moving = true;
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
		if(!isSwimming){
			g.setColor(Color.BLACK);
			g.fillOval((int)(x+width/4-xOffset), (int)(y+height-10-yOffset), width/2, 10);
		}

		BufferedImage img = getCurrAnimation().getCurrentImage(moving);
		if(!isSwimming)
			g.drawImage(img, (int)(x-xOffset), (int)(y-yOffset),width,height, null);
		else
			g.drawImage(img.getSubimage(10,10,img.getWidth()-20, img.getHeight()/2), (int)(x-xOffset+bounds.x), (int)(y-yOffset+bounds.y),bounds.width,height/2, null);
		
		//bounds
		g.fillRect((int)(x-xOffset+bounds.x), (int)(y-yOffset+bounds.y),bounds.width,bounds.height);
	}
	
}
