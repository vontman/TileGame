package myTileGame.objects.entites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import myTileGame.Handler;
import myTileGame.gfx.Assets;

public class Tree extends Entity{
	public static int BOUNDS_X = 12;
	public static int BOUNDS_Y = 48;
	public static int BOUNDS_WIDTH = 15;
	public static int BOUNDS_HEIGHT = 15;
	private BufferedImage img;
	public Tree(Handler handler, int id, float x, float y) {
		super(handler, id, x, y, Assets.CELL_WIDTH, Assets.CELL_HEIGHT*2, BOUNDS_X, BOUNDS_Y, BOUNDS_WIDTH, BOUNDS_HEIGHT);
		img = Assets.tree;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g, float xOffset, float yOffset) {
		g.drawImage(img, (int)(x-xOffset), (int)(y-yOffset), width,height,null);
//		g.fillRect((int)(x-xOffset+bounds.x), (int)(y-yOffset+bounds.y), bounds.width,bounds.height);
		
	}

}
