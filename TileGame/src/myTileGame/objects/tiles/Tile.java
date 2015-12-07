package myTileGame.objects.tiles;

import java.awt.image.BufferedImage;

import myTileGame.gfx.Assets;
import myTileGame.objects.GameObject;

public abstract class Tile extends GameObject{
	protected int id;
	public Tile(int id) {
		super(Assets.CELL_WIDTH, Assets.CELL_HEIGHT);
		Tile.children[id] = this;
		this.id = id;
	}
	protected BufferedImage img;
	public static Tile[] children ;
	public static void init(){
		children = new Tile[256];
		new Dirt(1);
		new Grass(2);
		new Stone(3);
	}
	public BufferedImage getImage(){
		return img;
	}
}
