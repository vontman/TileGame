package myTileGame.world;

import java.awt.Graphics;

import myTileGame.Handler;
import myTileGame.tiles.Tile;

public abstract class World {
	protected WorldInfo worldInfo;
	protected int counter = 0;
	protected Handler handler;
	public World(Handler handler){
		worldInfo = WorldLoader.loadWorld("/worlds/"+this.getClass().getSimpleName()+".txt");
	}
	public void tick(){
		
	}
	public void render(Graphics g){
		for(int j =0 ; j < worldInfo.getHeight() ; j++){
			for(int i =0 ; i < worldInfo.getWidth() ; i++){
				Tile.tiles[worldInfo.getElementAt(i, j)].draw(g, i, j);
			}
		}
	}
}
