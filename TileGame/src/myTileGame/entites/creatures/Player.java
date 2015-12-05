package myTileGame.entites.creatures;

import myTileGame.Handler;
import myTileGame.KeyManager;
import myTileGame.gfx.Assets;

public class Player extends Creature {

	public Player(Handler handler, int x, int y, int speed) {
		super(handler, x, y, speed,Assets.playerUp,Assets.playerDown,Assets.playerLeft,Assets.playerRight);
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
		x += moveX * speed;
		y += moveY * speed;
		if (x < 0)
			x = 0;
		if (y < 0)
			y = 0;
		if (x >= (handler.getGame().getWorld().getWidth()-1)*Assets.CELL_WIDTH)
			x = (handler.getGame().getWorld().getWidth()-1)*Assets.CELL_WIDTH;
		if (y >= (handler.getGame().getWorld().getHeight()-1)*Assets.CELL_HEIGHT)
			y = (handler.getGame().getWorld().getHeight()-1) *Assets.CELL_HEIGHT;
	}

}
