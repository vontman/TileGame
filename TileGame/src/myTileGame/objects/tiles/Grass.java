package myTileGame.objects.tiles;

import java.awt.Color;

import myTileGame.gfx.Assets;

public class Grass extends Tile{

	private static final int COLOR_RED = 0;
	private static final int COLOR_BLUE = 0;
	private static final int COLOR_GREEN = 255;
	public Grass(int id) {
		super(id,new Color(COLOR_RED, COLOR_GREEN, COLOR_BLUE));
		this.img = Assets.grass;
	}
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}


}
