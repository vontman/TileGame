package myTileGame.gfx;

import myTileGame.objects.entites.Entity;

public class Light {
	private float radius;
	private float power;
	private float x,y;
	private Entity entity;
	public Light(float x,float y,float radius,float power) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.power = Math.min(1F, power);
	}
	public float getRadius() {
		return radius;
	}
	public float getPower() {
		return power;
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public void focusOnEntity(Entity e){
		entity = e;
	}
	public void tick(){
		if(entity == null)
			return;
		x = (float) entity.getBounds().getCenterX();
		y = (float) entity.getBounds().getCenterY();
	}
	
}
