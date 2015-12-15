package myTileGame.gfx;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import myTileGame.Handler;

public class DrawEngine {
	private BufferedImage board;
	private Handler handler;
	private int pixels[];
	public DrawEngine(Handler handler) {
		this.handler = handler;
		board = new BufferedImage(handler.getGameWidth(), handler.getGameHeight(), BufferedImage.TYPE_INT_ARGB);
		pixels = ((DataBufferInt)board.getRaster().getDataBuffer()).getData();
	}
	public void draw(BufferedImage img,int x,int y,boolean toIso,boolean xMirror,boolean yMirror){
		if(img == null)
			return;
		int tempPixels[] = img.getRGB(0, 0, img.getWidth(), img.getHeight(), null, 0, img.getWidth());
		int w = img.getWidth();
		int h = img.getHeight();
		for(int i=0;i<w;i++){
			for(int j=0;j<h;j++){
				int tx = i;
				int ty = j;
				if( yMirror )
					tx = w - tx;
				if( xMirror )
					ty = h - ty;
				tx += x - handler.getCamera().getxOffset();
				ty += y - handler.getCamera().getyOffset();
				
				Point temp = new Point(tx,ty);
				
				if(toIso)
					temp = twoDToIso(temp);
				
				int color = tempPixels[j*w+i];
				// ignore transparent pixels
				if( ( (color>>24) & 0x000000FF) == 0 )
					continue;
				
				if(temp.x >= 0 && temp.x < board.getWidth() && temp.y >= 0 && temp.y < board.getHeight()){
					pixels[temp.y*board.getWidth()+ temp.x] = color;					
				}
			}
		}
	}

	public void draw2(BufferedImage img,int x,int y,boolean toIso,boolean xMirror,boolean yMirror){
		if(img == null)
			return;
		int tempPixels[] = img.getRGB(0, 0, img.getWidth(), img.getHeight(), null, 0, img.getWidth());
		int w = img.getWidth();
		int h = img.getHeight();
		for(int i=0;i<w;i++){
			for(int j=0;j<h;j++){
				int tx = i;
				int ty = 0;
				if( yMirror )
					tx = w - tx;
				if( xMirror )
					ty = h - ty;
				tx += x - handler.getCamera().getxOffset();
				ty += y - handler.getCamera().getyOffset();
				
				Point temp = new Point(tx,ty);
				
				if(toIso)
					temp = twoDToIso(temp);
				
				int color = tempPixels[j*w+i];
				// ignore transparent pixels
				if( ( (color>>24) & 0x000000FF) == 0 )
					continue;
				
				if(temp.x >= 0 && temp.x < board.getWidth() && temp.y >= 0 && temp.y < board.getHeight()){
					pixels[(temp.y+j)*board.getWidth()+ temp.x] = color;					
				}
			}
		}
	}
	public void tick(){
		int l = pixels.length;
		for(int i=0;i<l;i++)
			pixels[i] = 0;
	}
	public void render(Graphics g,float xOffset,float yOffset){
		if(board != null)
			g.drawImage(board,0,0,null);
	}
	public static int getRGB(byte b,byte b2,byte b3){
		 int red=b & 0xFF;
		 int green=b2 & 0xFF;
		 int blue=b3 & 0xFF;
		 int rgb = (red << 16) | (green << 8) | blue;
		 return rgb;
	}
	public static Point isoTo2D(Point pt){
		  Point tempPt = new Point(0, 0);
		  tempPt.x = (2 * pt.y + pt.x) / 2;
		  tempPt.y = (2 * pt.y - pt.x) / 2;
		  return(tempPt);
	}
	public static Point twoDToIso(Point pt){
		  Point tempPt = new Point(0,0);
		  tempPt.x = pt.x - pt.y +400;
		  tempPt.y = (pt.x + pt.y) / 2;
		  return(tempPt);
	}
}
