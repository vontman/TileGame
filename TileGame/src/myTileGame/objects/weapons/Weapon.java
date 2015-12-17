package myTileGame.objects.weapons;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import myTileGame.entites.objects.creatures.Creature;
import myTileGame.gfx.ImageLoader;
import myTileGame.objects.GameObject;

public class Weapon extends GameObject{
	protected int dmg;
	protected int range;
	protected long delay;
	protected int throwback;
	protected boolean left,right,up,down;
	protected int anchorX;
	protected int anchorY;
	protected BufferedImage img[];
	public Weapon(int width, int height,int dmg,int range,int throwback,int delay,int anchorX,int anchorY,BufferedImage img) {
		super(width, height);
		this.dmg = dmg;
		this.range = range;
		this.delay = delay;
		this.throwback = throwback;
		this.anchorX = anchorX;
		this.anchorY = anchorY;
		

		this.img = new BufferedImage[4];
		this.img[Creature.RIGHT] = img;
		this.img[Creature.UP] = ImageLoader.getRotatedImage(img, false, true);
		this.img[Creature.LEFT] = this.img[Creature.UP];
		this.img[Creature.DOWN] = ImageLoader.getRotatedImage(img, true, false);
	}
	public void render(Graphics g, float xOffset,float yOffset,Creature owner,long last){
		if(!owner.isAttacking())
			return;
		long timer = System.currentTimeMillis() - last;
		int state = owner.getState();
		Point pos = owner.getWepAnchor();
		BufferedImage img = this.img[state];
		pos.x -= xOffset;
		pos.y -= yOffset;
		Graphics2D g2 = (Graphics2D)g;
		AffineTransform af = g2.getTransform();
		if(state == Creature.UP){
			g2.rotate(Math.toRadians(90 * (1D * (delay-timer)/delay)),pos.x+anchorX,pos.y+height-anchorY);
			g2.drawImage(img, pos.x-width+anchorX,pos.y-anchorY,width,height, null);
		}
		else if(state == Creature.DOWN){
			g2.rotate(Math.toRadians(90 * (1D * (delay-timer)/delay)),pos.x+anchorX,pos.y+height-anchorY);
			g2.drawImage(img, pos.x-anchorX,pos.y+height-anchorY,width,height, null);
		}
		else if(state == Creature.LEFT){
			g2.rotate(Math.toRadians(- 90 * (1D * (delay-timer)/delay)),pos.x+anchorX,pos.y+height-anchorY);
			g2.drawImage(img, pos.x-width+anchorX,pos.y-anchorY,width,height, null);
		}
		else{
			g2.rotate(Math.toRadians(90 * (1D * (delay-timer)/delay)),pos.x+anchorX,pos.y+height-anchorY);
			g2.drawImage(img, pos.x-anchorX,pos.y-anchorY,width,height, null);
		}

		g2.setTransform(af);
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public int getDmg() {
		return dmg;
	}
	public long getDelay() {
		return delay;
	}
	public int getThrowback() {
		return throwback;
	}

}
