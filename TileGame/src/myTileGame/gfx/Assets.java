package myTileGame.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	public static final int CELL_WIDTH = 32;
	public static final int CELL_HEIGHT = 32;
	public static final int PLAYER_WIDTH = 64;
	public static final int PLAYER_HEIGHT = 64;
	private static SpriteSheet textureSheet;
	private static SpriteSheet playerSheet;
	public static BufferedImage[] playerLeft,playerRight,playerUp,playerDown;
	public static BufferedImage dirt,stone,grass,tree;
	public static void init(){
		initTextures();
		initPlayer();
	}
	private static void initTextures(){
		textureSheet = new SpriteSheet("/textures/blocks3.png",16,16);
		dirt = textureSheet.getImageAt(2, 0);
		grass = textureSheet.getImageAt(1, 9);
		stone = textureSheet.getImageAt(0,1);
		tree = textureSheet.getImageAt(15, 0);
	}
	private static void initPlayer(){
		playerSheet = new SpriteSheet("/textures/player.png",PLAYER_WIDTH,PLAYER_HEIGHT);
		
		playerLeft = new BufferedImage[9];
		playerRight = new BufferedImage[9];
		playerUp = new BufferedImage[9];
		playerDown = new BufferedImage[9];
		for(int i=0;i<9;i++){
			playerUp[i] = playerSheet.getImageAt(i, 0);
			playerLeft[i] = playerSheet.getImageAt(i, 1);
			playerDown[i] = playerSheet.getImageAt(i, 2);
			playerRight[i] = playerSheet.getImageAt(i, 3);
		}
	}
	
	//Getters
	public static BufferedImage[] getPlayerLeft() {
		return playerLeft;
	}
	public static BufferedImage[] getPlayerRight() {
		return playerRight;
	}
	public static BufferedImage[] getPlayerUp() {
		return playerUp;
	}
	public static BufferedImage[] setPlayerDown() {
		return playerDown;
	}
	
}
