package myTileGame.gfx;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	public static BufferedImage loadImage(String path){
		try{
			return ImageIO.read(ImageLoader.class.getResource(path));
		}catch(IOException e){
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	public static BufferedImage getRotatedImage(BufferedImage img,boolean mirrorX , boolean mirrorY){
		BufferedImage newImg = new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_ARGB);
		int pixels[] = img.getRGB(0, 0, img.getWidth(), img.getHeight(), null, 0, img.getWidth());
		int newPixels[] = ((DataBufferInt)newImg.getRaster().getDataBuffer()).getData();
		for(int i=0;i<img.getWidth();i++){
			for(int j=0;j<img.getHeight();j++){
				int x = i;
				int y = j;
				if(mirrorX){
					y = img.getHeight() - j-1;
				}
				if(mirrorY){
					x = img.getWidth() - i-1;
				}
				newPixels[i+j*img.getWidth()] = pixels[x+y*img.getHeight()];
			}
		}
		return newImg;
	}
}
