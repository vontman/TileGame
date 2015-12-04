package myTileGame.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	private BufferedImage sheet;
	public SpriteSheet(String path){
		sheet = ImageLoader.loadImage(path);
	}
	public BufferedImage getImageAt(int x,int y){
		return sheet.getSubimage(x*Assets.CELL_WIDTH, y*Assets.CELL_HEIGHT, Assets.CELL_WIDTH, Assets.CELL_HEIGHT);
	}public BufferedImage getImageAt(int x,int y,int width,int height){
		return sheet.getSubimage(x*Assets.CELL_WIDTH, y*Assets.CELL_HEIGHT, width, height);
	}
}
