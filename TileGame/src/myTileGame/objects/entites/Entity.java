package myTileGame.objects.entites;

import java.awt.Graphics;
import java.awt.Rectangle;

import myTileGame.Handler;
import myTileGame.gfx.Assets;
import myTileGame.objects.GameObject;

public abstract class Entity extends GameObject implements Comparable<Entity>{

	protected float x;
	protected float y;
	protected Handler handler ;
	protected Rectangle bounds;
	public Entity(Handler handler, float x, float y, int width, int height,int boundX ,int boundY , int boundWidth,int boundHeight) {
		super(width, height);
		this.handler = handler;
		this.x = x;
		this.y = y;
		bounds = new Rectangle(boundX,boundY,boundWidth,boundHeight);
		handler.getEntityManager().addEntity(this);
		if(this.isSolid()){
			handler.getGame().getCollisionSensor().addEntity(this);
		}
	}
	public abstract void tick();
	public abstract void render(Graphics g,float xOffset,float yOffset);
	@Override
	public boolean isSolid(){
		return true;
	}
	@Override
	public int compareTo(Entity compareEntity){
		float y = getY() + getHeight();
		float y2 = compareEntity.getY()+compareEntity.getHeight();
		if( y > y2 )
			return 1;
		return -1;
	}
	public boolean checkEntityCollision(Entity e,float xOffset,float yOffset){
		return e.getBounds().intersects(getBounds(xOffset,yOffset));
	}
	public boolean checkTileCollision(int  x , int y){
		if( handler.getWorld().getTileAt(x, y).isSolid() )
			return true;
		return false;
	}
	public int getWidth() {
		return width;
	}
	public Rectangle getBounds(){
		return getBounds(0F,0F);
	}
	public Rectangle getBounds(float xOffset,float yOffset){
		return new Rectangle((int)(x+bounds.x+xOffset),(int)(y+bounds.y+yOffset),bounds.width,bounds.height);
	}
	public int getHeight() {
		return height;
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
}
