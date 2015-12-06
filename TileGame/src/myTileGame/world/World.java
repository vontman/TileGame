package myTileGame.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import myTileGame.Handler;
import myTileGame.entites.objects.creatures.Player;
import myTileGame.gfx.Assets;
import myTileGame.objects.tiles.Tile;

public abstract class World {
	protected WorldInfo worldInfo;
	protected Handler handler;
	protected Player player;
	public World(Handler handler,Player player){
		this.handler = handler;
		worldInfo = WorldLoader.loadWorld("/worlds/"+this.getClass().getSimpleName()+".txt");
		handler.getGame().setSize(Math.min(getFullWidth(),handler.getGame().getWidth()), Math.min(getFullHeight(),handler.getGame().getHeight()));
	}
	public void tick(){
		
	}
	public void render(Graphics g,float xOffset , float yOffset){
		for(int j =0 ; j < worldInfo.getHeight() ; j++){
			for(int i =0 ; i < worldInfo.getWidth() ; i++){
				BufferedImage tempImage = Tile.children[worldInfo.getElementAt(i, j)].getImage();
				g.drawImage(tempImage, (int)(i*Assets.CELL_WIDTH-xOffset), (int)(j*Assets.CELL_HEIGHT-yOffset),Assets.CELL_WIDTH,Assets.CELL_HEIGHT ,null);
				
			}
		}
	}
	public Tile getTileAt(int x,int y){
		return Tile.children[worldInfo.getElementAt(x, y)];
	}
	public int getSpawnX(){
		return worldInfo.getSpawnX();
	}
	public int getSpawnY(){
		return worldInfo.getSpawnY();
	}
	public int getWidth(){
		return worldInfo.getWidth();
	}
	public int getHeight(){
		return worldInfo.getHeight();
	}
	public int getFullWidth(){
		return worldInfo.getWidth()*Assets.CELL_WIDTH;
	}
	public int getFullHeight(){
		return worldInfo.getHeight()*Assets.CELL_HEIGHT;
	}
}
