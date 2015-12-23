package myTileGame.gfx;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

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
	public void focusOnEntity(Entity e){
		entity = e;
	}
	public void tick(){
		if(entity == null)
			return;
		x = (float) entity.getBounds().getCenterX();
		y = (float) entity.getBounds().getCenterY();
	}
	public void render(Graphics2D g, float xOffset, float yOffset) {
	    RadialGradientPaint gp = new RadialGradientPaint(new Point(
	    		(int)(x-xOffset),(int)(y-yOffset))
	    		,radius,new float[]{.0F,power},
	    		new Color[]{new Color(0,0,0,255),new Color(0,0,0,3)});
		g.setPaint(gp);
		g.fillRect((int)(x-xOffset-radius),(int)(y-yOffset-radius), (int)radius*2, (int)radius*2);
	    		
	}
}
