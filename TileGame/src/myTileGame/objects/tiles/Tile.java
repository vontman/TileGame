
package myTileGame.objects.tiles;

import java.awt.Color;
import java.awt.image.BufferedImage;

import myTileGame.gfx.Assets;
import myTileGame.objects.GameObject;

public abstract class Tile extends GameObject{
	private static Tile[] children ;
	public static Tile dirtTile;
	public static Tile grassTile;
	public static Tile stoneTile;
	public static Tile waterTile;
	public static void init(){
		children = new Tile[256];
		dirtTile = new Dirt(0);
		grassTile = new Grass(1);
		stoneTile = new Stone(2);
		waterTile = new Water(3);
	}
	public static Tile getTile(int id){
		if( id < 0 || id > 255 )
			return stoneTile;
		if( children[id] == null )
			return dirtTile;
		return children[id];
	}
	public static Tile getTileByColor(Color color){
		for( Tile t : children ){
			if(t == null)
				continue;
			if( t.getColorId().equals(color) ){
				return t;
			}
		}
		return dirtTile;
	}
	public static Tile getTileByColor(int color){
		for( Tile t : children ){
			if(t == null)
				continue;
			if( t.getColorId().getRGB() == color ){
				return t;
			}
		}
		return dirtTile;
	}
	public static void tickTiles(){
		for(int i=0;i<children.length;i++){
			if( children[i] == null )
				break;
			children[i].tick();
		}
	}
	
	//class
	protected int id;
	protected Color colorId;
	protected BufferedImage img;
	public Tile(int id,Color colorId) {
		super(Assets.CELL_WIDTH, Assets.CELL_HEIGHT);
		Tile.children[id] = this;
		this.id = id;
		this.colorId = colorId;
		
	}
	public abstract void tick();
	public BufferedImage getImage(){
		return img;
	}
	public Color getColorId(){
		return this.colorId;
	}
	public int getId(){
		return this.id;
	}
	public boolean isSwimmable(){
		return false;
	}
}
