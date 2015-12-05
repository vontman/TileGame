package myTileGame.entites;

import java.awt.Graphics;

import myTileGame.Handler;

public abstract class Entity {
	protected Handler handler ;
	protected float x;
	protected float y;
	public Entity(Handler handler,float x,float y) {
		this.handler = handler;
		this.x = x;
		this.y = y;
	}
	public abstract void tick();
	public abstract void render(Graphics g);
}
