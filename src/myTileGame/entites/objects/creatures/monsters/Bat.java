package myTileGame.entites.objects.creatures.monsters;

import myTileGame.Handler;
import myTileGame.entites.objects.creatures.Monster;
import myTileGame.gfx.Assets;

public class Bat extends Monster{

	public static float SCALE = 1.3F;
	public static int WIDTH = (int) (32*SCALE);
	public static int HEIGHT = (int) (32*SCALE);
	public static int BOUNDS_X = (int) (8*SCALE);
	public static int BOUNDS_Y = (int) (8*SCALE);
	public static int BOUNDS_WIDTH = (int) (16*SCALE);
	public static int BOUNDS_HEIGHT = (int) (16*SCALE);
	public static int SPEED = 2;
	public static int HP = 20;
	public Bat(Handler handler, float x, float y) {
		super(handler, x, y, WIDTH, HEIGHT, SPEED, HP ,BOUNDS_X,BOUNDS_Y,BOUNDS_WIDTH,BOUNDS_HEIGHT, Assets.bat);
	}

}
