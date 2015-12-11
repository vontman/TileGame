package myTileGame.objects.tiles;

import java.awt.Color;

import myTileGame.gfx.Assets;

public class Dirt extends Tile{
	
	private static final int COLOR_RED = 100;
	private static final int COLOR_BLUE = 50;
	private static final int COLOR_GREEN = 50;
	public Dirt(int id) {
		super(id,new Color(COLOR_RED, COLOR_GREEN, COLOR_BLUE));
		img = Assets.dirt;
	}

	

}
