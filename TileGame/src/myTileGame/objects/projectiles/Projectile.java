package myTileGame.objects.projectiles;

import java.awt.image.BufferedImage;
import java.util.Iterator;

import myTileGame.Handler;
import myTileGame.entites.objects.creatures.Creature;
import myTileGame.gfx.Animation;
import myTileGame.gfx.Assets;
import myTileGame.objects.entites.Entity;

public abstract class Projectile extends Entity{
	private static final int ANIMATION_DELAY = 500;
	private Animation animation;
	private int speedX;
	private int speedY;
	private int dmg;
	private int throwBack;

	public Projectile(Handler handler, float x, float y, int width, int height,int dmg,int throwBack,int speedX,int speedY,BufferedImage[] imgs) {
		super(handler, x, y, width, height, 0, 0, width, height);
		this.animation = new Animation(imgs,ANIMATION_DELAY);
		this.speedX = speedX;
		this.speedY = speedY;
		this.dmg = dmg;
		this.throwBack = throwBack;
	}
	public BufferedImage getCurrentImage(){
		return animation.getCurrentImage(true);
	}
	public void tick(){
		if(isDead)
			return;
		if(x < 0 || y < 0 || x > handler.getWorldWidth() || y > handler.getWorldHeight()){
			isDead = true;
			return;
		}
		
		//entity collision
		for(Iterator<Entity>it = handler.getEntityManager().getCurrentEntities().iterator();it.hasNext();){
			Entity e = it.next();
			if(e.equals(this) || e.isDead())
				continue;
			if(checkEntityCollision(e, speedX, speedY))
				attack(e);
		}
		
		//tile collision
		int tx = 0,ty = 0;
		if( speedX > 0 ){
			tx = (int)(x+speedX+bounds.x+bounds.width)/Assets.CELL_WIDTH;
			if(checkTileCollision(tx, (int)(y+bounds.y)/Assets.CELL_HEIGHT) || 
					checkTileCollision(tx, (int)(y+bounds.y+bounds.height)/Assets.CELL_HEIGHT)	){
				isDead = true;
				return;
			}else
				x += speedX;
		}else if( speedX < 0 ){
			tx = (int)(x+speedX+bounds.x)/Assets.CELL_WIDTH;
			if(checkTileCollision(tx, (int)(y+bounds.y)/Assets.CELL_HEIGHT) || 
					checkTileCollision(tx, (int)(y+bounds.y+bounds.height)/Assets.CELL_HEIGHT)	){
				isDead = true;
				return;
			}else
				x += speedX;
		}
		if( speedY > 0 ){
			ty = (int)(y+speedY+bounds.y+bounds.height)/Assets.CELL_HEIGHT;
			if(checkTileCollision((int)(x+bounds.x)/Assets.CELL_WIDTH, ty) || 
					checkTileCollision((int)(x+bounds.x+bounds.width)/Assets.CELL_WIDTH,ty)	){
				isDead = true;
				return;
			}else
				y += speedY;
		}else if( speedY < 0 ){
			ty = (int)(y+speedY+bounds.y)/Assets.CELL_HEIGHT;
			if(checkTileCollision((int)(x+bounds.x)/Assets.CELL_WIDTH, ty) || 
					checkTileCollision((int)(x+bounds.x+bounds.width)/Assets.CELL_WIDTH,ty)	){
				isDead = true;
				return;
			}else 
				y += speedY;
		}
	}
	public void attack(Entity e){
		isDead = true;
		if(e == null)
			return;
		if( !(e instanceof Creature) ){
			((Creature)e).getAttacked(dmg, speedX/Math.abs(speedX)*throwBack, speedY/Math.abs(speedY)*throwBack, throwBack/2);
		}
	}

}
