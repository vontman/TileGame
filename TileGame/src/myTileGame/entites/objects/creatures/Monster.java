package myTileGame.entites.objects.creatures;

import myTileGame.Handler;
import myTileGame.gfx.Assets;

public class Monster extends Creature{
	public static int WIDTH = 50;
	public static int HEIGHT = 50;
	public static int BOUNDS_X = 18;
	public static int BOUNDS_Y = 26;
	public static int BOUNDS_WIDTH = 15;
	public static int BOUNDS_HEIGHT = 22;
	public static int START_HP = 20;
	public Monster(Handler handler, float x, float y, int speed, int hp) {
		super(handler, x, y, WIDTH, HEIGHT, speed, hp,BOUNDS_X,BOUNDS_Y,BOUNDS_WIDTH,BOUNDS_HEIGHT, Assets.monsterUp, Assets.monsterDown, Assets.monsterLeft,
				Assets.monsterRight);
		
		moving = true;
		state = (int) (Math.random()*5 + 1);
	}

	@Override
	public void tick() {
		
	}

}
