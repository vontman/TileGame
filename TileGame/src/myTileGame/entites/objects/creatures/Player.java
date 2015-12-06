package myTileGame.entites.objects.creatures;

import myTileGame.Handler;
import myTileGame.KeyManager;
import myTileGame.gfx.Assets;

public class Player extends Creature {
	public static int WIDTH = 50;
	public static int HEIGHT = 50;
	public static int BOUNDS_X = 18;
	public static int BOUNDS_Y = 26;
	public static int BOUNDS_WIDTH = 15;
	public static int BOUNDS_HEIGHT = 22;
	private int superSpeed;
	public Player(Handler handler,int id, float x, float y, int speed,int superSpeed) {
		super(handler,id, x, y,WIDTH,HEIGHT, speed,BOUNDS_X,BOUNDS_Y,BOUNDS_WIDTH,BOUNDS_HEIGHT,Assets.playerUp,Assets.playerDown,Assets.playerLeft,Assets.playerRight);
		this.superSpeed = superSpeed;
	}

	@Override
	public void tick() {
		KeyManager km = handler.getGame().getKeyManager();
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
