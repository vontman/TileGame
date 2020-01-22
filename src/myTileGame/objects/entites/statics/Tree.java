package myTileGame.objects.entites.statics;

import myTileGame.Handler;
import myTileGame.gfx.Assets;

public class Tree extends StaticEntity{
	public static int BOUNDS_X = 12;
	public static int BOUNDS_Y = 48;
	public static int BOUNDS_WIDTH = 15;
	public static int BOUNDS_HEIGHT = 15;
	public Tree(Handler handler, float x, float y) {
		super(handler, x, y, Assets.CELL_WIDTH, Assets.CELL_HEIGHT*2, BOUNDS_X, BOUNDS_Y, BOUNDS_WIDTH, BOUNDS_HEIGHT,Assets.tree);
	}

	

}
