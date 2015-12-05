package myTileGame.entites.creatures;

import java.awt.Graphics;

import myTileGame.Handler;
import myTileGame.KeyManager;
import myTileGame.gfx.Assets;

public class Player extends Creature {

	public Player(Handler handler, int x, int y, int speed) {
		super(handler, x, y,50,50, speed,Assets.playerUp,Assets.playerDown,Assets.playerLeft,Assets.playerRight);
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
		move(moveX, moveY);
	}

	public void move(int moveX, int moveY) {
		float tX = x ;
		float tY = y;
		x += moveX * speed;
		y += moveY * speed;
		if (x <= 0)
			x = 1;
		if (y <= 0)
			y = 1;
		if (x+width >= handler.getGame().getWorld().getWidth()*Assets.CELL_WIDTH)
			x = handler.getGame().getWorld().getWidth()*Assets.CELL_WIDTH-width;
		if (y+height >= handler.getGame().getWorld().getHeight()*Assets.CELL_HEIGHT)
			y = handler.getGame().getWorld().getHeight()*Assets.CELL_HEIGHT-height;
		if(tX == x && tY == y)
			moving = false;
		else moving = true;
	}

}
