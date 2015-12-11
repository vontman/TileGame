package myTileGame.objects.tiles;

import java.awt.Color;

import myTileGame.gfx.Assets;

public class Dirt extends Tile{
	
	private static final int COLOR_RED = 10;
	private static final int COLOR_BLUE = 10;
	private static final int COLOR_GREEN = 10;
	public Dirt(int id) {
		super(id,new Color(COLOR_RED, COLOR_GREEN, COLOR_BLUE).getRGB());
		img = Assets.dirt;
	}

	

}
