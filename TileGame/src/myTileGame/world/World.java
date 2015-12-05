package myTileGame.world;

import java.awt.Graphics;

import myTileGame.Handler;
import myTileGame.entites.creatures.Player;
import myTileGame.tiles.Tile;

public abstract class World {
	protected WorldInfo worldInfo;
	protected Handler handler;
	protected Player player;
	public World(Handler handler,Player player){
		this.handler = handler;
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
	public int getWidth(){
		return worldInfo.getWidth();
	}
	public int getHeight(){
		return worldInfo.getHeight();
	}
}
