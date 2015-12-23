package myTileGame.objects.weapons;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;

import myTileGame.entites.objects.creatures.Creature;
import myTileGame.entites.objects.creatures.Player;
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
	public Area getCurrHitBox(Player player, float xOffset,float yOffset,long timer){
		float x = player.getWepAnchor().x-xOffset;
		float y = player.getWepAnchor().y-yOffset;
		Rectangle bounds = new Rectangle((int)x,(int)y-5,range,10);
		AffineTransform af = new AffineTransform();
		Area area = new Area(bounds);
		float angle = -45;
		if(player.getState() == Player.UP){
			angle = -45-timer*1F/delay*90;
		}
		else if(player.getState() == Player.DOWN){
			angle = 135-timer*1F/delay*90;
		}
		else if(player.getState() == Player.RIGHT){
			angle = 45-timer*1F/delay*90;
		}
		else if(player.getState() == Player.LEFT){
			angle = 135+timer*1F/delay*90;
		}
		af.rotate(Math.toRadians(angle),bounds.getX(),bounds.getCenterY());
		area.transform(af);
		return area;
	}
	public void render(Graphics2D g, float xOffset,float yOffset,Player owner,long timer){
		if(!owner.isAttacking())
			return;
		int state = owner.getState();
		Point pos = owner.getWepAnchor();
		BufferedImage img = this.img[state];
		pos.x -= xOffset;
		pos.y -= yOffset;
		AffineTransform af = g.getTransform();
		if(state == Creature.UP){
			g.rotate(Math.toRadians(90 * (1D * (delay-timer)/delay)),pos.x+anchorX,pos.y+height-anchorY);
			g.drawImage(img, pos.x-width+anchorX,pos.y-anchorY,width,height, null);
		}
		else if(state == Creature.DOWN){
			g.rotate(Math.toRadians(90 * (1D * (delay-timer)/delay)),pos.x+anchorX,pos.y+height-anchorY);
			g.drawImage(img, pos.x-anchorX,pos.y+height-anchorY,width,height, null);
		}
		else if(state == Creature.LEFT){
			g.rotate(Math.toRadians(- 90 * (1D * (delay-timer)/delay)),pos.x+anchorX,pos.y+height-anchorY);
			g.drawImage(img, pos.x-width+anchorX,pos.y-anchorY,width,height, null);
		}
		else{
			g.rotate(Math.toRadians(90 * (1D * (delay-timer)/delay)),pos.x+anchorX,pos.y+height-anchorY);
			g.drawImage(img, pos.x-anchorX,pos.y-anchorY,width,height, null);
		}

		g.setTransform(af);
		
//		g.fill(getCurrHitBox(owner,xOffset,yOffset,timer));
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
