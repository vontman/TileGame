package myTileGame.objects.entites.statics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import myTileGame.Handler;
import myTileGame.objects.entites.Entity;

public abstract class StaticEntity extends Entity{
	protected BufferedImage img;
	public StaticEntity(Handler handler, float x, float y, int width, int height, int boundX, int boundY,
			int boundWidth, int boundHeight, BufferedImage img) {
		super(handler, x, y, width, height, boundX, boundY, boundWidth, boundHeight);
		this.img = img;
	}
	@Override
	public void tick() {
		super.tick();
	}

	@Override
	public void render(Graphics g, float xOffset, float yOffset) {
		
		//shadow
		g.setColor(Color.black);
		g.fillOval((int)(x-xOffset+5), (int)(y-yOffset+height-15), width-10,15);
		
		//img
		g.drawImage(img, (int)(x-xOffset), (int)(y-yOffset), width,height,null);
//		g.fillRect((int)(x-xOffset+bounds.x), (int)(y-yOffset+bounds.y), bounds.width,bounds.height);
		
	}
}
