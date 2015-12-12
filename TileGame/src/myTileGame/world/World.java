package myTileGame.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.LinkedList;

import myTileGame.Handler;
import myTileGame.gfx.Assets;
import myTileGame.objects.entites.Entity;
import myTileGame.objects.entites.EntityManager;
import myTileGame.objects.tiles.Tile;

public abstract class World {
	protected WorldInfo worldInfo;
	protected Handler handler;
	protected EntityManager entityManager;
	public World(Handler handler){
		this.entityManager = new EntityManager();
		this.handler = handler;
		worldInfo = WorldLoader.loadWorldImg("/worlds/"+this.getClass().getSimpleName()+".png");
		handler.getGame().setSize(Math.min(getFullWidth(),handler.getGameWidth()), Math.min(getFullHeight(),handler.getGameHeight()));
	}
	public abstract void init();
	public void tick(){
		LinkedList<Entity>currEntities = entityManager.getCurrentEntities();
		for( Entity e : currEntities ){
			e.tick();
		}
	}
	public void render(Graphics g,float xOffset , float yOffset){
		renderTiles(g, xOffset, yOffset);
		renderEntities(g, xOffset, yOffset);
	}
	private void renderTiles(Graphics g,float xOffset,float yOffset){
		for(int j = Math.max(0,(int)yOffset/Assets.CELL_HEIGHT) ; j < Math.min(worldInfo.getHeight(),Math.ceil((handler.getGameHeight()+yOffset)/Assets.CELL_HEIGHT)) ; j++){
			for(int i =Math.max(0,(int)xOffset/Assets.CELL_WIDTH) ; i < Math.min(worldInfo.getWidth(),Math.ceil((handler.getGameWidth()+xOffset)/Assets.CELL_WIDTH)) ; i++){
				BufferedImage tempImage = Tile.getTile(worldInfo.getElementAt(i, j)).getImage();
				g.drawImage(tempImage, (int)(i*Assets.CELL_WIDTH-xOffset), (int)(j*Assets.CELL_HEIGHT-yOffset),Assets.CELL_WIDTH,Assets.CELL_HEIGHT ,null);
				
			}
		}
	}
	private void renderEntities(Graphics g,float xOffset,float yOffset){
		LinkedList<Entity>currEntities = entityManager.getCurrentEntities();
		Collections.sort(currEntities);
		for( Entity e : currEntities ){
			e.render(g, xOffset, yOffset);
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
	public EntityManager getEntityManager(){
		return entityManager;
	}
}
