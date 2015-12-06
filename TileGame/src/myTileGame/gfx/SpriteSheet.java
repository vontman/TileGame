package myTileGame.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	private BufferedImage sheet;
	private int width;
	private int height;
	public SpriteSheet(String path){
		sheet = ImageLoader.loadImage(path);
		width = Assets.CELL_WIDTH;
		height = Assets.CELL_HEIGHT;
	}
	public SpriteSheet(String path,int width,int height){
		sheet = ImageLoader.loadImage(path);
		this.width = width;
		this.height = height;
	}
	public BufferedImage getImageAt(int x,int y){
		return sheet.getSubimage(x*width, y*height, width, height);
	}public BufferedImage getImageAt(int x,int y,int width,int height){
		return sheet.getSubimage(x*this.width, y*this.height, width, height);
	}
	public int getWidth(){
		return this.width;
	}
	public int getHeight(){
		return this.height;
	}
}
