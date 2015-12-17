package myTileGame.objects.weapons;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import myTileGame.entites.objects.creatures.Creature;
import myTileGame.objects.GameObject;

public class Weapon extends GameObject{
	protected int dmg;
	protected int range;
	protected long delay;
	protected int throwback;
	protected boolean left,right,up,down;
	protected long timer;
	protected long last;
	protected int anchorX;
	protected int anchorY;
	protected Creature owner;
	protected BufferedImage img;
	public Weapon(Creature owner,int width, int height,int dmg,int range,int throwback,int delay,int anchorX,int anchorY,BufferedImage img) {
		super(width, height);
		this.dmg = dmg;
		this.range = range;
		this.delay = delay;
		this.throwback = throwback;
		this.owner = owner;
		this.img = img;
		this.anchorX = anchorX;
		this.anchorY = anchorY;
	}
	public void tick(){
		if( !owner.isAttacking() || timer >= delay)
			timer = 0;
		else
			timer += System.currentTimeMillis()-last;
		last = System.currentTimeMillis();
	}
	public void render(Graphics g, float xOffset,float yOffset){
		if(!owner.isAttacking())
			return;
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform af = g2d.getTransform();
		int x = (int)(owner.getBounds().getMaxX()-anchorX-xOffset);
		int y = (int)(owner.getBounds().getMinY()-owner.getAboveGround()-anchorY-yOffset);
		float cx = (float)owner.getBounds().getCenterX()-xOffset;
		float cy = (float)owner.getBounds().getMinY()-owner.getAboveGround()-yOffset;
		if( owner.getState() == Creature.LEFT ){
			
			g2d.rotate(Math.toRadians(180),cx,cy);
			
			g2d.rotate(Math.toRadians((delay*1D - timer)/delay*90 ),x,y+anchorY);
			
			g2d.drawImage(img,x,y,width,height, null);
		}
		if( owner.getState() == Creature.RIGHT ){

			g2d.rotate(Math.toRadians(90 - (delay/1D - timer)/(delay/1D)*90 ),x,y+anchorY);
			
			g2d.drawImage(img,x,y,width,height, null);
		}
		if( owner.getState() == Creature.DOWN ){
			
			g2d.rotate(Math.toRadians(90),cx,cy);
			
			g2d.rotate(Math.toRadians((delay/1D - timer)/(delay/1D)*90 ),x,y+anchorY);
			
			g2d.drawImage(img,x,y,width,height, null);
		}

		if( owner.getState() == Creature.UP ){
			
			g2d.rotate(Math.toRadians(270),cx,cy);
			
			g2d.rotate(Math.toRadians((delay/1D - timer)/(delay/1D)*90 ),x,y+anchorY);
			
			g2d.drawImage(img,x,y,width,height, null);
		}
		
		g2d.setTransform(af);
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
