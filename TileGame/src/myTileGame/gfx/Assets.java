package myTileGame.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	public static final int CELL_WIDTH = 32;
	public static final int CELL_HEIGHT = 32;
	public static final int PLAYER_WIDTH = 64;
	public static final int PLAYER_HEIGHT = 64;
	public static final int UP_INDEX = 0;
	public static final int LEFT_INDEX = 1;
	public static final int DOWN_INDEX = 2;
	public static final int RIGHT_INDEX = 3;	
	private static SpriteSheet textureSheet;
	private static SpriteSheet playerSheet;
	private static SpriteSheet eyeballSheet,ghostSheet,maneaterSheet,batSheet,bigwormSheet;
	private static SpriteSheet weaponsSheet;
	public static BufferedImage[][] player;
	public static BufferedImage longsword,shortsword,armedsword,flamesword;
	public static BufferedImage[][] eyeball;
	public static BufferedImage[][] ghost;
	public static BufferedImage[][] maneater;
	public static BufferedImage[][] bat;
	public static BufferedImage[][] bigworm;
	public static BufferedImage dirt,stone,grass,tree,water;
	public static void init(){
		initTextures();
		initPlayer();
		initMonster();
		initWeapons();		
	}
	private static void initTextures(){
		textureSheet = new SpriteSheet("/textures/blocks.png",16,16);
		dirt = textureSheet.getImageAt(2, 0);
		grass = textureSheet.getImageAt(1, 9);
		stone = textureSheet.getImageAt(0,1);
		tree = textureSheet.getImageAt(15, 0);
		water = textureSheet.getImageAt(14, 0);
	}
	private static void initWeapons(){
		weaponsSheet = new SpriteSheet("/textures/weapons.png",32,32);
		longsword = weaponsSheet.getImageAt(2, 4);
		armedsword = weaponsSheet.getImageAt(3, 4);
		shortsword = weaponsSheet.getImageAt(4, 4);
		flamesword = weaponsSheet.getImageAt(3, 5);
	}
	private static void initPlayer(){
		playerSheet = new SpriteSheet("/textures/player/char_sword_shield.png",PLAYER_WIDTH,PLAYER_HEIGHT);
		player = new BufferedImage[4][9];
		initObj(playerSheet,player,8,9);
	}
	private static void initMonster(){
		eyeballSheet = new SpriteSheet("/textures/monsters/eyeball.png",32,38);
		eyeball = new BufferedImage[4][3];
		initObj(eyeballSheet,eyeball,0,3);
		
		ghostSheet = new SpriteSheet("/textures/monsters/ghost.png",40,46);
		ghost = new BufferedImage[4][3];
		initObj(ghostSheet,ghost,0,3);
		
		maneaterSheet = new SpriteSheet("/textures/monsters/man_eater_flower.png",60,76);
		maneater = new BufferedImage[4][3];
		initObj(maneaterSheet,maneater,0,3);
		
		batSheet = new SpriteSheet("/textures/monsters/bat.png",32,32);
		bat = new BufferedImage[4][3];
		initObj(batSheet,bat,0,3);
		
		bigwormSheet = new SpriteSheet("/textures/monsters/big_worm.png",35,50);
		bigworm= new BufferedImage[4][3];
		initObj(bigwormSheet,bigworm,0,3);
		
	}
	private static void initObj(SpriteSheet sheet ,BufferedImage[][] arr, int startIndex,int length){
		for(int i=0;i<length;i++){
			for(int j=0;j<4;j++)
				arr[j][i] = sheet.getImageAt(i, startIndex+j);
		}
	}
	
	//Getters
	public static BufferedImage[] getPlayerLeft() {
		return player[LEFT_INDEX];
	}
	public static BufferedImage[] getPlayerRight() {
		return player[RIGHT_INDEX];
	}
	public static BufferedImage[] getPlayerUp() {
		return player[UP_INDEX];
	}
	public static BufferedImage[] setPlayerDown() {
		return player[DOWN_INDEX];
	}
	
}
