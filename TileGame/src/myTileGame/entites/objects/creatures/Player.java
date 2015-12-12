package myTileGame.entites.objects.creatures;

import java.awt.Point;

import myTileGame.Handler;
import myTileGame.KeyManager;
import myTileGame.gfx.Assets;
import myTileGame.objects.entites.Entity;
import myTileGame.objects.tiles.Tile;

public class Player extends Creature {
	public static int WIDTH = 48;
	public static int HEIGHT = 48;
	public static int BOUNDS_X = 18;
	public static int BOUNDS_Y = 26;
	public static int BOUNDS_WIDTH = 15;
	public static int BOUNDS_HEIGHT = 22;
	public static int START_HP = 20;
	public static int ATTACK_DIST = 25;
	private int superSpeed;
	public Player(Handler handler, float x, float y, int speed,int superSpeed) {
		super(handler,x, y,WIDTH,HEIGHT, speed,START_HP,BOUNDS_X,BOUNDS_Y,BOUNDS_WIDTH,BOUNDS_HEIGHT,Assets.playerUp,Assets.playerDown,Assets.playerLeft,Assets.playerRight);
		this.superSpeed = superSpeed;
		hp = 10;
	}

	@Override
	public void tick() {
		KeyManager km = handler.getKeyManager();
		int moveX = 0;
		int moveY = 0;
		if (km.left) {
			moveX--;
			state = 3;
		}
		if (km.right) {
			moveX++;
			state = 4;
		}
		if (km.up) {
			moveY--;
			state = 1;
		}
		if (km.down) {
			moveY++;
			state = 2;
		}
		if(km.shift){
			moveX *= superSpeed;
			moveY *= superSpeed;
		}else{
			moveX *= speed;
			moveY *= speed;
		}
		move(moveX, moveY);
		
		if( km.space ){
			for( Entity e : Entity.currEntities ){
				if(e.equals(this) || !Creature.class.isAssignableFrom(e.getClass()))continue;
				Point p1 = new Point((int)(getBounds().getCenterX()+getX()),(int)(getBounds().getCenterY()+getY()));
				Point p2 = new Point((int)(e.getBounds().getCenterX()+e.getX()),(int)(e.getBounds().getCenterY()+e.getY()));
				if( p1.distance(p2) <=  ATTACK_DIST)
					((Creature)e).getAttacked(10);
			}
		}
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
	}

	public void move(int moveX, int moveY) {
		float tX = x ;
		float tY = y;
		if(handler.getGame().getCollisionSensor().moveEntity(this, moveX, moveY)){
			x += moveX;
			y += moveY;
		}
//		if (x <= 0)
//			x = 1;
//		if (y <= 0)
//			y = 1;
//		if (x+width >= handler.getGame().getWorld().getWidth()*Assets.CELL_WIDTH)
//			x = handler.getGame().getWorld().getWidth()*Assets.CELL_WIDTH-width;
//		if (y+height >= handler.getGame().getWorld().getHeight()*Assets.CELL_HEIGHT)
//			y = handler.getGame().getWorld().getHeight()*Assets.CELL_HEIGHT-height;
		if(tX == x && tY == y)
			moving = false;
		else moving = true;
	}

}
