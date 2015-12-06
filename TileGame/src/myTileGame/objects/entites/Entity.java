package myTileGame.objects.entites;

import java.awt.Graphics;
import java.awt.Rectangle;

import myTileGame.Handler;
import myTileGame.objects.GameObject;

public abstract class Entity extends GameObject{

	protected Handler handler ;
	protected Rectangle bounds;
	public Entity(Handler handler, int id, float x, float y, int width, int height,int boundX ,int boundY , int boundWidth,int boundHeight) {
		super(id, x, y, width, height);
		this.handler = handler;
		bounds = new Rectangle(boundX,boundY,boundWidth,boundHeight);
		if(this.isSolid())
			handler.getGame().getCollisionSensor().addEntity(this);
	}
	public abstract void tick();
	public abstract void render(Graphics g,float xOffset,float yOffset);
	
	public int getWidth() {
		return width;
	}
	
	@Override
	public boolean isSolid(){
		return true;
	}
	public Rectangle getBounds(){
		return this.bounds;
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
