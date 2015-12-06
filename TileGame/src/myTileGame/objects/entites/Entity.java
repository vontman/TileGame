package myTileGame.objects.entites;

import java.awt.Graphics;

import myTileGame.Handler;
import myTileGame.objects.GameObject;

public abstract class Entity extends GameObject{

	protected Handler handler ;
	public Entity(Handler handler, int id, float x, float y, int width, int height) {
		super(id, x, y, width, height);
		this.handler = handler;
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
