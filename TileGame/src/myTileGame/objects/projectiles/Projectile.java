package myTileGame.objects.projectiles;

import java.awt.image.BufferedImage;
import java.util.Iterator;

import myTileGame.Handler;
import myTileGame.entites.objects.creatures.Creature;
import myTileGame.gfx.Animation;
import myTileGame.gfx.Assets;
import myTileGame.objects.entites.Entity;

public abstract class Projectile extends Entity{
	private static final int ANIMATION_DELAY = 200;
	private Animation animation;
	protected int moveX;
	protected int moveY;
	private int dmg;
	private int throwBack;
	private Creature caster;
	public Projectile(Handler handler, float x, float y, int width, int height,int dmg,int throwBack,int speed,int moveX,int moveY,BufferedImage[] imgs,Creature caster) {
		super(handler, x, y, width, height, 0, 0, width, height);
		this.animation = new Animation(imgs,ANIMATION_DELAY);
		this.dmg = dmg;
		this.throwBack = throwBack;
		this.caster = caster;
		
		if(moveX > 0)moveX = speed;
		if(moveX < 0)moveX = -speed;
		if(moveY > 0)moveY = speed;
		if(moveY < 0)moveY = -speed;

		this.moveX = moveX;
		this.moveY = moveY;
		
	}
	public BufferedImage getCurrentImage(){
		return animation.getCurrentImage(true);
	}
	public void tick(){
		if(isDead){
			handler.getEntityManager().removeEntity(this);
			return;
		}
		if(x+width < 0 || y+height < 0 || x > handler.getWorldWidth() || y > handler.getWorldHeight()){
			isDead = true;
			return;
		}
		
		//entity collision
		for(Iterator<Entity>it = handler.getEntityManager().getCurrentEntities();it.hasNext();){
			Entity e = it.next();
			if(e.equals(this) || e.isDead() || e.equals(caster) || !e.isSolid() || e.isDead())
				continue;
			if(checkEntityCollision(e, moveX, moveY))
				attack(e);
		}
		
		//tile collision
		int tx = 0,ty = 0;
		if( moveX > 0 ){
			tx = (int)(x+moveX+bounds.x+bounds.width)/Assets.CELL_WIDTH;
			if(checkTileCollision(tx, (int)(y+bounds.y)/Assets.CELL_HEIGHT) || 
					checkTileCollision(tx, (int)(y+bounds.y+bounds.height)/Assets.CELL_HEIGHT)	){
				isDead = true;
				return;
			}else
				x += moveX;
		}else if( moveX < 0 ){
			tx = (int)(x+moveX+bounds.x)/Assets.CELL_WIDTH;
			if(checkTileCollision(tx, (int)(y+bounds.y)/Assets.CELL_HEIGHT) || 
					checkTileCollision(tx, (int)(y+bounds.y+bounds.height)/Assets.CELL_HEIGHT)	){
				isDead = true;
				return;
			}else
				x += moveX;
		}
		if( moveY > 0 ){
			ty = (int)(y+moveY+bounds.y+bounds.height)/Assets.CELL_HEIGHT;
			if(checkTileCollision((int)(x+bounds.x)/Assets.CELL_WIDTH, ty) || 
					checkTileCollision((int)(x+bounds.x+bounds.width)/Assets.CELL_WIDTH,ty)	){
				isDead = true;
				return;
			}else
				y += moveY;
		}else if( moveY < 0 ){
			ty = (int)(y+moveY+bounds.y)/Assets.CELL_HEIGHT;
			if(checkTileCollision((int)(x+bounds.x)/Assets.CELL_WIDTH, ty) || 
					checkTileCollision((int)(x+bounds.x+bounds.width)/Assets.CELL_WIDTH,ty)	){
				isDead = true;
				return;
			}else 
				y += moveY;
		}
	}
	public void attack(Entity e){
		isDead = true;
		if(e == null)
			return;
		if( e instanceof Creature ){
			((Creature)e).getAttacked(dmg, (moveX > 0?1:-1)*throwBack, (moveY > 0?1:-1)*throwBack, throwBack/2);
		}
	}
	@Override
	public boolean isSolid(){
		return false;
	}
	
}
