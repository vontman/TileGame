package myTileGame.world;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class WorldLoader {
	public static WorldInfo loadWorld(String path){
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
			return new WorldInfo(width,height,spawnX,spawnY,board);
		}catch(IOException e){
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
}
