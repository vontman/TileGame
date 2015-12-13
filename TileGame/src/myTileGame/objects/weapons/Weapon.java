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
	protected Creature owner;
	protected BufferedImage img;
	public Weapon(Creature owner,int width, int height,int dmg,int range,int throwback,int delay,BufferedImage img) {
		super(width, height);
		this.dmg = dmg;
		this.range = range;
		this.delay = delay;
		this.throwback = throwback;
		this.owner = owner;
		this.img = img;
	}
	public void tick(){
		if( !owner.isAttacking() || timer >= delay)
			timer = 0;
		else
			timer += System.currentTimeMillis()-last;
		last = System.currentTimeMillis();
	}
	public void render(Graphics g, float xOffset,float yOffset){
		if(!owner.isAttacking() || timer > delay/2D)
			return;
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform af = g2d.getTransform();
		if( owner.getState() == Creature.LEFT ){
			g2d.rotate(Math.toRadians(180),owner.getX()-xOffset+20,owner.getY()-yOffset+5+owner.getAboveGround()+height);
			
			if( timer < delay/6D )
				g2d.rotate(Math.toRadians(90),owner.getX()-xOffset+20,owner.getY()-yOffset+5+owner.getAboveGround()+height);
			else if( timer < 2D*delay/6D )
				g2d.rotate(Math.toRadians(90-20),owner.getX()-xOffset+20,owner.getY()-yOffset+5+owner.getAboveGround()+height);
			else
				g2d.rotate(Math.toRadians(90-40),owner.getX()-xOffset+20,owner.getY()-yOffset+5+owner.getAboveGround()+height);
			g2d.drawImage(img,(int)(owner.getX()-xOffset+20),(int)(owner.getY()-yOffset+5+owner.getAboveGround()),width,height, null);
		}
		if( owner.getState() == Creature.RIGHT ){
			if( timer < delay/6D )
				g2d.rotate(Math.toRadians(0),owner.getX()-xOffset+30,owner.getY()-yOffset+5+owner.getAboveGround()+height);
			else if( timer < 2D*delay/6D )
				g2d.rotate(Math.toRadians(20),owner.getX()-xOffset+30,owner.getY()-yOffset+5+owner.getAboveGround()+height);
			else
				g2d.rotate(Math.toRadians(40),owner.getX()-xOffset+30,owner.getY()-yOffset+5+owner.getAboveGround()+height);
			
			g2d.drawImage(img,(int)(owner.getX()-xOffset+30),(int)(owner.getY()-yOffset+5+owner.getAboveGround()),width,height, null);
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
