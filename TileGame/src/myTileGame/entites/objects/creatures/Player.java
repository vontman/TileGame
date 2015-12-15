package myTileGame.entites.objects.creatures;

import java.awt.Rectangle;

import myTileGame.Handler;
import myTileGame.KeyManager;
import myTileGame.gfx.Assets;
import myTileGame.objects.entites.Entity;
import myTileGame.objects.weapons.Sword;

public class Player extends Creature {
	public static int WIDTH = 48;
	public static int HEIGHT = 48;
	public static int BOUNDS_X = 18;
	public static int BOUNDS_Y = 26;
	public static int BOUNDS_WIDTH = 15;
	public static int BOUNDS_HEIGHT = 22;
	public static int START_HP = 20;
	private int superSpeed;
	public Player(Handler handler, float x, float y, int speed,int superSpeed) {
		super(handler,x, y,WIDTH,HEIGHT, speed,START_HP,BOUNDS_X,BOUNDS_Y,BOUNDS_WIDTH,BOUNDS_HEIGHT,Assets.player);
		this.superSpeed = superSpeed;
		lastAttack = 0;
		weapon = new Sword(this);
	}

	@Override
	public void tick() {
		KeyManager km = handler.getKeyManager();
		
		int moveX = 0;
		int moveY = 0;
		if (km.left) {
			moveX--;
			state = LEFT;
		}
		else if (km.right) {
			moveX++;
			state = RIGHT;
		}
		if (km.up) {
			moveY--;
			state = UP;
		}
		else if (km.down) {
			moveY++;
			state = DOWN;
		}
		if(km.shift){
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
		
		//attacking
		if(System.currentTimeMillis()-lastAttack >= weapon.getDelay())
			isAttacking = false;
		if( km.attack &&  !isAttacking){
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
			lastAttack = System.currentTimeMillis();
			isAttacking = true;
			for( Entity e : handler.getEntityManager().getCurrentEntities()){
				if(e == null)
					continue;
				if(e.equals(this) || !Creature.class.isAssignableFrom(e.getClass()))continue;
				if( attckReg.intersects(e.getBounds()) ){
					((Creature)e).getAttacked(weapon.getDmg(),xMove,yMove,weapon.getThrowback());
				}
			}
		}
		
		super.tick();
	}

	

}
