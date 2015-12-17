package myTileGame.entites.objects.creatures.monsters;

import myTileGame.Handler;
import myTileGame.entites.objects.creatures.Monster;
import myTileGame.gfx.Assets;

public class BigWorm extends Monster{

	public static float SCALE = 1.3F;
	public static int WIDTH = (int)(35*SCALE);
	public static int HEIGHT = (int) (50*SCALE);
	public static int BOUNDS_X = (int) (8*SCALE);
	public static int BOUNDS_Y = (int) (24*SCALE);
	public static int BOUNDS_WIDTH = WIDTH - BOUNDS_X;
	public static int BOUNDS_HEIGHT = HEIGHT-BOUNDS_Y;
	public static int SPEED = 1;
	public static int HP = 20;
	public BigWorm(Handler handler, float x, float y) {
		super(handler, x, y, WIDTH, HEIGHT, SPEED, HP ,BOUNDS_X,BOUNDS_Y,BOUNDS_WIDTH,BOUNDS_HEIGHT, Assets.bigworm);
	}

}
