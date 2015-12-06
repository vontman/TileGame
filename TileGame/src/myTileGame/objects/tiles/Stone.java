package myTileGame.objects.tiles;

import myTileGame.gfx.Assets;

public class Stone extends Tile{

	public Stone(int id) {
		super(id);
		img = Assets.stone;
	}
	@Override
	public boolean isSolid(){
		return true;
	}


}
