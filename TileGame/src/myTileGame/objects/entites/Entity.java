package myTileGame.objects.entites;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Collections;
import java.util.LinkedList;

import myTileGame.Handler;
import myTileGame.objects.GameObject;

public abstract class Entity extends GameObject implements Comparable<Entity>{

	protected float x;
	protected float y;
	protected Handler handler ;
	protected Rectangle bounds;
	public static LinkedList<Entity>currEntities;
	public Entity(Handler handler, float x, float y, int width, int height,int boundX ,int boundY , int boundWidth,int boundHeight) {
		super(width, height);
		this.handler = handler;
		this.x = x;
		this.y = y;
		bounds = new Rectangle(boundX,boundY,boundWidth,boundHeight);
		currEntities.add(this);
		if(this.isSolid()){
			handler.getGame().getCollisionSensor().addEntity(this);
		}
	}
	public abstract void tick();
	public abstract void render(Graphics g,float xOffset,float yOffset);
	public static void init(){
		currEntities = new LinkedList<Entity>();
	}
	@Override
	public int compareTo(Entity compareEntity){
		float y = getY() + getHeight();
		float y2 = compareEntity.getY()+compareEntity.getHeight();
		if( y > y2 )
			return 1;
		return -1;
	}
	public static void renderEntities(Graphics g,float xOffset,float yOffset){
		Collections.sort(currEntities);
		for( Entity e : currEntities ){
			e.render(g, xOffset, yOffset);
		}
	}
	public int getWidth() {
		return width;
	}
	
	@Override
	public boolean isSolid(){
		return true;
	}
	public Rectangle getBounds(){
		return new Rectangle(this.bounds);
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
