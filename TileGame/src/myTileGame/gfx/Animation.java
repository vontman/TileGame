package myTileGame.gfx;

import java.awt.image.BufferedImage;

public class Animation {	
	private BufferedImage imgs[];
	private long last;
	private long timer;
	private long delay;
	int index;
	public Animation(BufferedImage imgs[],long delay) {
		this.imgs = imgs;
		this.delay = delay;
		last = System.currentTimeMillis();
		index = 0;
		timer = 0;
	}
	public BufferedImage getCurrentImage(boolean flag){
		long now = System.currentTimeMillis();
		if(!flag){
			index = 0;
			last = now;
			timer = 0;
			return imgs[0];
		}
		timer += now - last;
		if(timer >= delay/imgs.length ){
			timer = 0;
			index ++;
			if(index >= imgs.length)
				index = 0;
		}
		last = now;
		return imgs[index];
	}
	
}
