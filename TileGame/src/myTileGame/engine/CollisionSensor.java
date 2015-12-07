package myTileGame.engine;

import java.awt.Rectangle;
import java.util.LinkedList;

import myTileGame.Handler;
import myTileGame.gfx.Assets;
import myTileGame.objects.entites.Entity;

public class CollisionSensor {
	Handler handler;
	public static LinkedList<Entity> solidEntities;
	public CollisionSensor(Handler handler) {
		this.handler = handler;
		solidEntities = new LinkedList<Entity>();
	}
	public void addEntity(Entity entity){
		solidEntities.add(entity);
	}
	public boolean moveEntity(Entity entity,float moveX , float moveY){
		float x = entity.getX();
		float y = entity.getY();
		Rectangle temp = new Rectangle(entity.getBounds());
		temp.x += x+moveX;
		temp.y += y+moveY;
		return checkGameBounds(temp)&&checkEntityCollsion(entity, temp)&&checkTileCollision(temp);
	}
	private boolean checkGameBounds(Rectangle temp){
		if( temp.x < 0 || temp.y < 0)
			return false;
		if( temp.getMaxX() > handler.getWorldWidth() || temp.getMaxY() > handler.getWorldHeight() )
			return false;
		return true;
	}
	private boolean checkEntityCollsion(Entity entity , Rectangle temp){
		for( Entity e : solidEntities ){
			if( e.equals(entity) )
				continue;
			Rectangle currEntity = new Rectangle(e.getBounds());
			currEntity.x += e.getX();
			currEntity.y += e.getY();
			if(temp.intersects( currEntity)){
				return false;
			}
		}
		return true;
	}
	private boolean checkTileCollision(Rectangle temp){
		int minTileX = (int)Math.floor(temp.getMinX()/Assets.CELL_WIDTH);
		int minTileY = (int)Math.floor(temp.getMinY()/Assets.CELL_HEIGHT);
		int maxTileX = (int)Math.ceil(temp.getMaxX()/Assets.CELL_WIDTH);
		int maxTileY = (int)Math.ceil(temp.getMaxY()/Assets.CELL_HEIGHT);
		for(int i = minTileX ; i <= maxTileX ; i++){
			for(int j = minTileY ; j <= maxTileY ; j++){
				Rectangle tileBounds = new Rectangle(i*Assets.CELL_WIDTH, j*Assets.CELL_HEIGHT, Assets.CELL_WIDTH, Assets.CELL_HEIGHT);
				if( temp.intersects(tileBounds) &&  handler.getWorld().getTileAt(i, j).isSolid() ){
					return false;
				}
			}
		}
		return true;
	}
}
