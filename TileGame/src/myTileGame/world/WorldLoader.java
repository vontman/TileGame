package myTileGame.world;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import myTileGame.gfx.ImageLoader;
import myTileGame.objects.tiles.Tile;

public class WorldLoader {
	public static WorldInfo loadWorldText(String path){
		try{
			int [][]board;
			int width;
			int height;
			int spawnX;
			int spawnY;
			BufferedReader br = new BufferedReader(new FileReader("res"+path));
			Scanner scanner = new Scanner(br);
			width = scanner.nextInt();
			height = scanner.nextInt();
			spawnX = scanner.nextInt();
			spawnY = scanner.nextInt();
			board = new int[width][height];
			for(int j = 0 ;  j < height ; j ++){
				for(int i = 0 ; i < width ; i++){
					board[i][j] = scanner.nextInt();
				}
			}
			scanner.close();
			return new WorldInfo(width,height,spawnX,spawnY,board);
		}catch(IOException e){
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}public static WorldInfo loadWorldImg(String path){
		try{
			int [][]board;
			int width;
			int height;
			int spawnX = 0;
			int spawnY = 0;
			BufferedImage img = ImageLoader.loadImage(path);
			width = img.getWidth();
			height = img.getHeight();
			int pixels[] = new int[width*height];
			board = new int[width][height];
			pixels = img.getRGB(0, 0, width, height, null, 0, width);
			for(int i=0;i<width;i++){
				for(int j=0;j<height;j++){
					int index = j*width + i;
					board[i][j] = Tile.getTileByColor(pixels[index]).getId();
				}
			}
			return new WorldInfo(width,height,spawnX,spawnY,board);
		}catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
}
