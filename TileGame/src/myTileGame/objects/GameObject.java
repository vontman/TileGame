package myTileGame.objects;

import myTileGame.Handler;

public abstract class GameObject {

	protected float x;
	protected float y;
	protected int width;
	protected int height;
	protected int id;
	public GameObject(int id,float x,float y,int width,int height) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.width = width;
		this.height = height;
	}
}
