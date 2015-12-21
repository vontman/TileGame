package myTileGame.gfx;

import java.awt.image.BufferedImage;

public class Animation {	
	private BufferedImage imgs[];
	private long last;
	private long delay;
	int index;
	public Animation(BufferedImage imgs[],long delay) {
		this.imgs = imgs;
		this.delay = delay;
		last = 0;
		index = 0;
	}
	public BufferedImage getCurrentImage(boolean flag){
		if(!flag){
			reset();
			return imgs[0];
		}
		if(System.currentTimeMillis()-last>= delay){
			update();
			last = System.currentTimeMillis();
		}
		return imgs[index];
	}
	public void setDelay(long delay){
		this.delay = delay;
	}
	private void reset(){
		last = System.currentTimeMillis();
		index = 0;
	}
	private void update(){
		index ++;
		if(index >= imgs.length)
			index = 0;
	}
}
