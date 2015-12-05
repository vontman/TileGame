package myTileGame.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import myTileGame.gfx.Assets;

public abstract class Tile {
	protected int id;
	protected BufferedImage img;
	public static Tile[] tiles ;
	public Tile(int id){
		this.id = id;
		Tile.tiles[id] = this;
	}
	public static void init(){
		tiles = new Tile[256];
		new Dirt(0);
		new Grass(1);
		new Stone(2);
	}
	public BufferedImage getImage(){
		return img;
	}
}
