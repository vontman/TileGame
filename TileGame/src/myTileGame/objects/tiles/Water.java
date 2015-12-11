package myTileGame.objects.tiles;

import java.awt.Color;

import myTileGame.gfx.Assets;

public class Water extends Tile{

	private static final int COLOR_RED = 0;
	private static final int COLOR_BLUE = 255;
	private static final int COLOR_GREEN = 0;
	public Water(int id) {
		super(id,new Color(COLOR_RED, COLOR_GREEN, COLOR_BLUE));
		img = Assets.water;
	}


}
