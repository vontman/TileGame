package myTileGame.objects.tiles;

import java.awt.Color;

import myTileGame.gfx.Assets;

public class Stone extends Tile{

	private static final int COLOR_RED = 50;
	private static final int COLOR_BLUE = 50;
	private static final int COLOR_GREEN = 50;
	private int ticker = 0 ;
	public Stone(int id) {
		super(id,new Color(COLOR_RED, COLOR_GREEN, COLOR_BLUE));
		img = Assets.stone;
	}
	@Override
	public boolean isSolid(){
		return true;
	}
	public void tick(){
		ticker++;
		if(ticker % 90 < 45){
			this.img = Assets.stone;
		}
		else {
			this.img = Assets.grass;
		}
	}

}
