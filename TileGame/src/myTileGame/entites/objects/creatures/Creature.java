package myTileGame.entites.objects.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import myTileGame.Handler;
import myTileGame.gfx.Animation;
import myTileGame.gfx.Assets;
import myTileGame.objects.entites.Entity;
import myTileGame.objects.tiles.Tile;
import myTileGame.objects.weapons.Weapon;

public abstract class Creature extends Entity{
	public static final int ANIMATION_DELAY = 800;
	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int RIGHT = 4;
	
	protected Animation upAnimation;
	protected Animation downAnimation;
	protected Animation leftAnimation;
	protected Animation rightAnimation;
	protected int state = 3;
	protected int counterState;
	protected int speed;
	protected int hp;
	protected int fullHp;
	protected int team;
	protected boolean moving;
	
	protected Weapon weapon;
	protected boolean isAttacking;
	protected boolean isAttacked;
	protected int yThrow;
	protected int xThrow;
	protected double lastAttack;
	
	protected boolean isSwimming;
	
	//jumping
	protected boolean isJumping;
	protected boolean isFalling;
	protected int jumpHeight;
	protected int aboveGround;
	
	public Creature(Handler handler,float x, float y,int width,int height,int speed,int hp,int boundX ,int boundY , int boundWidth,int boundHeight,BufferedImage[][] img) {
		super(handler,x, y,width,height, boundX, boundY, boundWidth, boundHeight);
		this.speed = speed;
		this.hp = hp;
		this.fullHp = hp;
		upAnimation = new Animation(img[Assets.UP_INDEX],ANIMATION_DELAY);
		downAnimation = new Animation(img[Assets.DOWN_INDEX],ANIMATION_DELAY);
		leftAnimation = new Animation(img[Assets.LEFT_INDEX],ANIMATION_DELAY);
		rightAnimation = new Animation(img[Assets.RIGHT_INDEX],ANIMATION_DELAY);
		isAttacking = false;
		state = 0;
	}
	public void attack(){
		if(weapon == null ||  isAttacking)
			return;
		Rectangle attckReg = null;
		int xMove = 0  , yMove = 0;
		if(state == UP){
			attckReg = new Rectangle((int)(x+bounds.x),(int)(y+bounds.y-weapon.getRange()),weapon.getRange(),weapon.getRange());
			xMove = 0;
			yMove = -weapon.getThrowback();
		}
		if(state == DOWN){
			attckReg = new Rectangle((int)(x+bounds.x),(int)(y+bounds.y+weapon.getRange()),weapon.getRange(),weapon.getRange());
			xMove = 0;
			yMove = weapon.getThrowback();
		}
		if(state == LEFT){
			attckReg = new Rectangle((int)(x+bounds.x-weapon.getRange()),(int)(y+bounds.y),weapon.getRange(),weapon.getRange());
			yMove = 0;
			xMove = -weapon.getThrowback();
		}
		if(state == RIGHT){
			attckReg = new Rectangle((int)(x+bounds.x+weapon.getRange()),(int)(y+bounds.y),weapon.getRange(),weapon.getRange());
			yMove = 0;
			xMove = weapon.getThrowback();
		}
		
		//bugged , can get attacked multiple times
//		for(int i=getTileX()-1;i<=getBounds().getMaxX()/Assets.CELL_WIDTH+1;i++)
//			for(int j=getTileY()-1;j<=getBounds().getMaxY()/Assets.CELL_HEIGHT+1;j++)
//				for( Iterator<Entity> it = handler.getWorld().getWorldChains().getChains(i, j) ; it.hasNext();){
//					Entity e = it.next();
//					if(e == null)
//						continue;
//					if(e.equals(this) || !Creature.class.isAssignableFrom(e.getClass()))continue;
//					if( attckReg.intersects(e.getBounds()) ){
//						((Creature)e).getAttacked(weapon.getDmg(),xMove,yMove,weapon.getThrowback());
//					}
//				}
			for( Iterator<Entity> it = handler.getEntityManager().getCurrentEntities().iterator() ; it.hasNext();){
				Entity e = it.next();
				if(e == null)
					continue;
				if(e.equals(this) || !Creature.class.isAssignableFrom(e.getClass()))continue;
				if( attckReg.intersects(e.getBounds()) ){
					((Creature)e).getAttacked(weapon.getDmg(),xMove,yMove,weapon.getThrowback());
				}
			}

		lastAttack = System.currentTimeMillis();
		isAttacking = true;
		
	}
	public void getAttacked(int dmg,int xMove,int yMove,int zMove){
		hp -= dmg;
		if(hp <= 0){
			hp = 0;
		}
		isJumping = false;
		isFalling = false;
		isAttacked = true;
		jumpHeight += zMove;
		xThrow += xMove;
		yThrow += yMove;

	}
	public void updatePos(){
		if( !isAttacked )
			return;
		if( xThrow == 0 && yThrow == 0 && aboveGround == 0 ){
			isAttacked = false;
			return;
		}
		if(!isFalling){
			jumpHeight--;
			aboveGround++;
			if(jumpHeight == 0){
				isFalling = true;
			}
		}else{
			aboveGround--;
			if(aboveGround == 0){
				isFalling = false;
				isJumping = false;
			}
		}
		if(xThrow < 0){
			move(-1,0);
			xThrow++;
		}		
		if(xThrow > 0){
			move(1,0);
			xThrow--;
		}		
		if(yThrow < 0){
			move(0,-1);
			yThrow++;
		}		
		if(yThrow > 0){
			move(0,1);
			yThrow--;
		}		
		
	}
	public void jump(){
		if(!isJumping)
			return;
		if(isFalling){
			aboveGround--;
			jumpHeight++;
			if(aboveGround == 0){
				isFalling = false;
				isJumping = false;
			}
		}else{
			jumpHeight--;
			aboveGround++;
			if(jumpHeight == 0)
				isFalling = true;
		}
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
		//remove oldChains
		for(int i=getTileX();i<=getBounds().getMaxX()/Assets.CELL_WIDTH;i++)for(int j=getTileY();j<=getBounds().getMaxY()/Assets.CELL_HEIGHT;j++)
			handler.getWorld().getWorldChains().removeChain(i,j,this);
		
		if(isSwimming){
			moveX/=2;
			moveY/=2;
		}
		//entity collision
//		for( Entity e : handler.getEntityManager().getCurrentEntities()){
//			if(e.equals(this) || e.isDead())
//				continue;
//			if(checkEntityCollision(e, moveX, 0))
//				moveX = 0;
//			if(checkEntityCollision(e, moveX, moveY))
//				moveY = 0;
//		}
		for(int i=getTileX()-1;i<=getBounds().getMaxX()/Assets.CELL_WIDTH+1;i++)
			for(int j=getTileY()-1;j<=getBounds().getMaxY()/Assets.CELL_HEIGHT+1;j++)
				for( Iterator<Entity> it = handler.getWorld().getWorldChains().getChains(i, j) ; it.hasNext();){
					Entity e = it.next();
					if(e.equals(this) || e.isDead())
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
		
		//world bound
		if( x+bounds.x < 0 )
			x = -bounds.x;
		if( y+bounds.y < 0 )
			y = -bounds.y ;
		if(x + bounds.x + bounds.width > handler.getWorldWidth())
			x = handler.getWorldWidth()-bounds.x-bounds.width;
		if(y + bounds.y + bounds.height > handler.getWorldHeight())
			y = handler.getWorldHeight()-bounds.y-bounds.height;
		if(moveX == 0 && moveY == 0)
			moving = false;
		else 
			moving = true;
		
		//add new chains
		for(int i=getTileX();i<=getBounds().getMaxX()/Assets.CELL_WIDTH;i++)for(int j=getTileY();j<=getBounds().getMaxY()/Assets.CELL_HEIGHT;j++)
			handler.getWorld().getWorldChains().addChain(i,j,this);
	}
	
	public void tick(){
		if( hp == 0 )
			isDead = true;
		jump();
		if(weapon != null){
			weapon.tick();
		}
		updatePos();
		//update attacking
		if(weapon != null && System.currentTimeMillis()-lastAttack >= weapon.getDelay())
			isAttacking = false;

		//check if swimming
		int x = (int)this.x + bounds.x;
		int y = (int)this.y + bounds.y;
		int width = (int)bounds.getWidth();
		int height = (int)bounds.getHeight();
		Tile t1 =handler.getWorld().getTileAt((int)Math.floor(x/Assets.CELL_WIDTH),(int)Math.floor(y/Assets.CELL_HEIGHT));
		Tile t2 =handler.getWorld().getTileAt((int)Math.ceil(x/Assets.CELL_WIDTH),(int)Math.ceil(y/Assets.CELL_HEIGHT));
		Tile t3 =handler.getWorld().getTileAt((int)Math.floor((x+width-1)/Assets.CELL_WIDTH),(int)Math.floor((y+height-1)/Assets.CELL_HEIGHT));
		Tile t4 =handler.getWorld().getTileAt((int)Math.ceil((x+width-1)/Assets.CELL_WIDTH),(int)Math.ceil((y+height-1)/Assets.CELL_HEIGHT));
		if( t1.isSwimmable() && t2.isSwimmable() && t3.isSwimmable() && t4.isSwimmable() ){
			isSwimming = true;
		}else
			isSwimming = false;
		super.tick();
	}
	public void render(Graphics g,float xOffset,float yOffset){
		if(isDead()){
			g.setColor(Color.red);
			g.fillOval((int)(x+width/4-xOffset), (int)(y+height-10-yOffset), width/2, 10);
			
			return;
		}
		if(weapon != null && state != DOWN){
			weapon.render(g, xOffset, yOffset);
		}
		
		//hp bar
		g.setColor(Color.green);
		g.drawRect((int)(x-xOffset), (int)(y-5-yOffset-aboveGround), width, 4);
		g.setColor(Color.red);
		g.fillRect((int)(x-xOffset), (int)(y-4-yOffset-aboveGround), width, 3);
		g.setColor(Color.green);
		g.fillRect((int)(x-xOffset), (int)(y-4-yOffset-aboveGround), (int)(1D*width/fullHp*hp), 3);

		//shadow
		if(!isSwimming){
			g.setColor(Color.BLACK);
			g.fillOval((int)(x+width/4-xOffset), (int)(y+height-10-yOffset), width/2, 10);
		}

//		//attack
//		if(isAttacking){
//			if(state == 4)
//				g.fillRect((int)(x+bounds.x+bounds.width/2-xOffset), (int)(y-aboveGround+bounds.y-yOffset), 30, 6);
//			if(state == 3)
//				g.fillRect((int)(x+bounds.x+bounds.width/2-xOffset-40), (int)(y-aboveGround+bounds.y-yOffset), 30, 6);
//			if(state == 1)
//				g.fillRect((int)(x+bounds.x+bounds.width/2-xOffset-4), (int)(y-aboveGround+bounds.y-yOffset-40), 6, 30);
//			if(state == 2)
//				g.fillRect((int)(x+bounds.x+bounds.width/2-xOffset-4), (int)(y-aboveGround+bounds.y-yOffset), 6, 30);
//		}
		
		
		BufferedImage img = getCurrAnimation().getCurrentImage(moving);
		if(!isSwimming)
			g.drawImage(img, (int)(x-xOffset), (int)(y-yOffset-aboveGround),width,height, null);
		else{
			if( handler.getGame().getTicks()%60 < 45 && !moving){
				yOffset++;
			}
			else if( handler.getGame().getTicks()%60 >= 45 && !moving){
				yOffset--;
			}

			g.setColor(Color.white);
			if( handler.getGame().getTicks()%90 < 30){
				g.drawOval((int)(x-xOffset+5), (int)(y-yOffset+10),width-10,height/2-5);
			}
			else if( handler.getGame().getTicks()%90 < 60 ){
				g.drawOval((int)(x-xOffset+5-2), (int)(y-yOffset+10-2),width-6,height/2-1);
			}else{
				g.drawOval((int)(x-xOffset+5-4), (int)(y-yOffset+10-4),width-2,height/2+3);
			}
			g.drawImage(img.getSubimage(0,0,img.getWidth(), img.getHeight()/2), (int)(x-xOffset), (int)(y-yOffset),width,height/2, null);
			
		}

		if(weapon != null && state == DOWN){
			weapon.render(g, xOffset, yOffset);
		}

		
		//bounds
//		g.fillRect((int)(x-xOffset+bounds.x), (int)(y-yOffset+bounds.y),bounds.width,bounds.height);
	}
	protected void moveRandomly(){
		int moveX = 0;
		int moveY = 0;
		moving = true;
		if(state == UP){
			moveX = 0 ; 
			moveY = -speed;
		}
		if(state == DOWN){
			moveX = 0 ; 
			moveY = speed;
		}
		if(state == LEFT){
			moveX = -speed ; 
			moveY = 0;
		}
		if(state == RIGHT){
			moveX = speed ; 
			moveY = 0;
		}
		int oldX = (int)(getX()+moveX);
		int oldY = (int)(getY()+moveY);
		move(moveX,moveY);
		if( (moveX == 0 && moveY == 0) || oldX != (int)getX() || oldY != (int)getY()){
			state = getRandomState();
		}
	}
	public void equip(Weapon weapon){
		if(isAttacking || isDead)
			return;
		this.weapon = weapon;
	}
	public void dequip(){
		this.weapon = null;
	}
	protected int getRandomState(){
		return (int) (Math.random()*5 + 1);
	}
	protected boolean isEnemy(Creature c){
		return c.getTeam() != getTeam();
	}
	
	//getters
	public int getState(){
		return state;
	}
	public int getSpeed() {
		return speed;
	}
	public int getHp() {
		return hp;
	}
	public boolean isMoving() {
		return moving;
	}
	public boolean isAttacking() {
		return isAttacking;
	}
	public boolean isSwimming() {
		return isSwimming;
	}

	public boolean isJumping() {
		return isJumping;
	}
	public boolean isFalling() {
		return isFalling;
	}
	public int getAboveGround() {
		return aboveGround;
	}
	public int getTeam(){
		return team;
	}
}
