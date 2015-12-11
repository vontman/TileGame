package myTileGame.objects.tiles;

import java.awt.image.BufferedImage;

import myTileGame.gfx.Assets;
import myTileGame.objects.GameObject;

public abstract class Tile extends GameObject{
	private static Tile[] children ;
	private static Tile dirtTile;
	public static void init(){
		children = new Tile[256];
		dirtTile = new Dirt(1);
		new Grass(2);
		new Stone(3);
	}
	public static Tile getTile(int id){
		if( id < 0 || id > 255 )
			return dirtTile;
		if( children[id] == null )
			return dirtTile;
		return children[id];
	}
	
	//class
	protected int id;
	protected int colorId;
	protected BufferedImage img;
	public Tile(int id,int colorId) {
		super(Assets.CELL_WIDTH, Assets.CELL_HEIGHT);
		Tile.children[id] = this;
		this.id = id;
		this.colorId = colorId;
	}
	public BufferedImage getImage(){
		return img;
	}
}
