package myTileGame.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	public static final int CELL_WIDTH = 32;
	public static final int CELL_HEIGHT = 32;
	private static SpriteSheet textureSheet;
	private static SpriteSheet playerSheet;
	public static BufferedImage playerLeft,playerRight,playerUp,playerDown;
	public static BufferedImage dirt,stone,grass;
	public static void init(){
		initTextures();
		initPlayer();
	}
	private static void initTextures(){
		textureSheet = new SpriteSheet("/textures/blocks.png");
		dirt = textureSheet.getImageAt(0, 0);
		grass = textureSheet.getImageAt(1, 0);
		stone = textureSheet.getImageAt(2, 0);
	}
	private static void initPlayer(){
		playerSheet = new SpriteSheet("/textures/player.png");
		playerLeft = playerSheet.getImageAt(0, 0);
		playerRight = playerSheet.getImageAt(1, 0);
		playerUp = playerSheet.getImageAt(0, 1);
		playerDown = playerSheet.getImageAt(1, 1);
	}
	
	//Getters
	public static BufferedImage getPlayerLeft() {
		return playerLeft;
	}
	public static BufferedImage getPlayerRight() {
		return playerRight;
	}
	public static BufferedImage getPlayerUp() {
		return playerUp;
	}
	public static BufferedImage getDirt() {
		return dirt;
	}
	public static BufferedImage getStone() {
		return stone;
	}
	public static BufferedImage setPlayerDown() {
		return playerDown;
	}
}
