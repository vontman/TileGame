package myTileGame.gfx;

import java.awt.image.BufferedImage;

public class Animation {	
	private BufferedImage imgs[];
	private long last;
	private long timer;
	private final long timePerFrame = 200;
	int index;
	public Animation(BufferedImage imgs[]) {
		this.imgs = imgs;
		last = System.currentTimeMillis();
		index = 0;
		timer = 0;
	}
	public BufferedImage getCurrentImage(){
		long now = System.currentTimeMillis();
		timer += now - last;
		if(timer >= timePerFrame ){
			timer = 0;
			index ++;
			if(index >= imgs.length)
				index = 0;
		}
		last = now;
		return imgs[index];
	}
	
}
