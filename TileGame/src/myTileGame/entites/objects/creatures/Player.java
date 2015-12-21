package myTileGame.entites.objects.creatures;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Iterator;

import myTileGame.Handler;
import myTileGame.KeyManager;
import myTileGame.gfx.Assets;
import myTileGame.objects.entites.Entity;
import myTileGame.objects.projectiles.TestProjectile;
import myTileGame.objects.weapons.ArmedSword;
import myTileGame.objects.weapons.FlameSword;
import myTileGame.objects.weapons.LongSword;
import myTileGame.objects.weapons.ShortSword;
import myTileGame.objects.weapons.Weapon;

public class Player extends Creature {
	public static int WIDTH = 48;
	public static int HEIGHT = 48;
	public static int BOUNDS_X = 18;
	public static int BOUNDS_Y = 26;
	public static int BOUNDS_WIDTH = 15;
	public static int BOUNDS_HEIGHT = 22;
	public static int START_HP = 20;
	private int superSpeed;
	//directions
	private boolean up,down,left,right;
	
	private Point wepAnchorUp;//to be set 
	private Point wepAnchorDown;
	private Point wepAnchorRight;
	private Point wepAnchorLeft;
	private Weapon weapon;
	
	Weapon[] weps;
	int currWep = 0;
	public Player(Handler handler, float x, float y, int speed,int superSpeed) {
		super(handler,x, y,WIDTH,HEIGHT, speed,START_HP,BOUNDS_X,BOUNDS_Y,BOUNDS_WIDTH,BOUNDS_HEIGHT,Assets.player);
		this.superSpeed = superSpeed;

		this.leftAnimation.setDelay(80);
		this.rightAnimation.setDelay(80);
		this.upAnimation.setDelay(80);
		this.downAnimation.setDelay(80);
		
		this.wepAnchorUp = new Point(width/2,10);
		this.wepAnchorDown = new Point(width/2,height-10);
		this.wepAnchorLeft = new Point(bounds.x,24);
		this.wepAnchorRight = new Point(bounds.x+bounds.width-5,28);
		equip(new FlameSword());
		weps = new Weapon[]{new FlameSword(),new ShortSword(),new LongSword(),new ArmedSword()};
	}

	@Override
	public void tick() {
		KeyManager km = handler.getKeyManager();
		
		up = down = left = right = false;
		int moveX = 0;
		int moveY = 0;
		if (km.left) {
			moveX--;
			state = LEFT;
			left = true;
		}
		if (km.right) {
			moveX++;
			state = RIGHT;
			right = true;
		}
		if (km.up) {
			moveY--;
			state = UP;
			up = true;
		}
		if (km.down) {
			moveY++;
			state = DOWN;
			down = true;
		}
		if(km.shift){
			currWep++;
			currWep %= weps.length;
			equip(weps[currWep]);
			
			moveX *= superSpeed;
			moveY *= superSpeed;
		}else{
			moveX *= speed;
			moveY *= speed;
		}
		move(moveX, moveY);
		
		//jumping
		if(km.space && !isJumping){
			isJumping = true;
			jumpHeight = 10;
		}

		//update attacking
		if(weapon != null && System.currentTimeMillis()-lastAttack >= weapon.getDelay())
			isAttacking = false;
		
		//attacking
		if(km.attack)
			attack();
		if(km.getMessile()){
			moveX = 0;
			moveY = 0;
			if(up)
				moveY --;
			if(down)
				moveY ++;
			if(left)
				moveX --;
			if(right)
				moveX ++;
			if(getState() == UP)
				moveY --;
			if(getState() == DOWN)
				moveY ++;
			if(getState() == LEFT)
				moveX --;
			if(getState() == RIGHT)
				moveX ++;
			new TestProjectile(handler, x+bounds.x+10, y+bounds.y, moveX, moveY,this);
		}
		
		
		super.tick();
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
			for( Iterator<Entity> it = handler.getEntityManager().getCurrentEntities() ; it.hasNext();){
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
	@Override
	public void render(Graphics g, float xOffset, float yOffset){
		if(weapon != null && state != DOWN){
			weapon.render(g, xOffset, yOffset,this,lastAttack);
		}
		super.render(g, xOffset, yOffset);
		if(weapon != null && state == DOWN){
			weapon.render(g, xOffset, yOffset,this,lastAttack);
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
	public Point getWepAnchor() {
		Point temp = new Point();
		if(state == UP)
			temp =  new Point(wepAnchorUp);
		else if(state == DOWN)
			temp = new Point(wepAnchorDown);
		else if(state == LEFT)
			temp =  new Point(wepAnchorLeft);
		else
			temp = new Point(wepAnchorRight);
		temp.x += getX();
		temp.y += getY()+getAboveGround();
		return temp;
	}
}
