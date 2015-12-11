package myTileGame.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import myTileGame.Handler;
import myTileGame.gfx.Assets;
import myTileGame.objects.tiles.Tile;

public abstract class World {
	protected WorldInfo worldInfo;
	protected Handler handler;
	public World(Handler handler){
		this.handler = handler;
		worldInfo = WorldLoader.loadWorld("/worlds/"+this.getClass().getSimpleName()+".txt");
		handler.getGame().setSize(Math.min(getFullWidth(),handler.getGameWidth()), Math.min(getFullHeight(),handler.getGameHeight()));
	}
	public void tick(){
		
	}
	public void render(Graphics g,float xOffset , float yOffset){
		for(int j = Math.max(0,(int)yOffset/Assets.CELL_HEIGHT) ; j < Math.min(worldInfo.getHeight(),Math.ceil((handler.getGameHeight()+yOffset)/Assets.CELL_HEIGHT)) ; j++){
			for(int i =Math.max(0,(int)xOffset/Assets.CELL_WIDTH) ; i < Math.min(worldInfo.getWidth(),Math.ceil((handler.getGameWidth()+xOffset)/Assets.CELL_WIDTH)) ; i++){
				BufferedImage tempImage = Tile.getTile(worldInfo.getElementAt(i, j)).getImage();
				g.drawImage(tempImage, (int)(i*Assets.CELL_WIDTH-xOffset), (int)(j*Assets.CELL_HEIGHT-yOffset),Assets.CELL_WIDTH,Assets.CELL_HEIGHT ,null);
				
			}
		}
	}
	public Tile getTileAt(int x,int y){
		return Tile.getTile(worldInfo.getElementAt(x, y));
	}
	public int getSpawnX(){
		return worldInfo.getSpawnX()*Assets.CELL_WIDTH;
	}
	public int getSpawnY(){
		return worldInfo.getSpawnY()*Assets.CELL_HEIGHT;
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
