package myTileGame.entites;

import java.awt.Graphics;

import myTileGame.Handler;

public abstract class Entity {
	protected Handler handler ;
	protected float x;
	protected float y;
	protected int width;
	protected int height;
	public Entity(Handler handler,float x,float y,int width,int height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	public abstract void tick();
	public abstract void render(Graphics g,float xOffset,float yOffset);
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
}
