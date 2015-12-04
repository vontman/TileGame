package myTileGame.world;

import java.awt.Graphics;

import myTileGame.tiles.Tile;

public abstract class World {
	protected WorldInfo worldInfo;
	public World(String path){
		worldInfo = WorldLoader.loadWorld(path);
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
