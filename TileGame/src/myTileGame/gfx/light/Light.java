package myTileGame.gfx.light;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.image.BufferedImage;

import myTileGame.objects.entites.Entity;

public class Light {
	private float radius;
	private float power;
	private float x,y;
	private Entity entity;
	private BufferedImage img;
	public Light(float x,float y,float radius,float power) {
		this.x = x;
		this.y = y;
		this.radius = radius/LightMap.SCALE;
		this.power = Math.min(1F, power);
		init();
	}
	private void init() {
		img = new BufferedImage((int)radius*2,(int)radius*2,BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g = (Graphics2D) img.getGraphics();
		RadialGradientPaint gp = new RadialGradientPaint(new Point(
	    		(int)(radius),(int)(radius))
	    		,radius,new float[]{.0F,power},
	    		new Color[]{new Color(0,0,0,1F),new Color(0,0,0,0)});
		g.setPaint(gp);
		g.fillRect(0,0, (int)radius*2, (int)radius*2);
		g.dispose();
		img.flush();		
	}
	public BufferedImage getImage(){
		return img;
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
		x = (float) entity.getBounds().getCenterX()/LightMap.SCALE;
		y = (float) entity.getBounds().getCenterY()/LightMap.SCALE;
	}
}
