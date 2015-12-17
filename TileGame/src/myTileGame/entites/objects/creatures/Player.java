package myTileGame.entites.objects.creatures;

import java.awt.Point;

import myTileGame.Handler;
import myTileGame.KeyManager;
import myTileGame.gfx.Assets;
import myTileGame.objects.weapons.ArmedSword;
import myTileGame.objects.weapons.FlameSword;
import myTileGame.objects.weapons.LongSword;
import myTileGame.objects.weapons.ShortSword;
import myTileGame.objects.weapons.Weapon;

public class Player extends Creature {
	public static int WIDTH = 48;
	public static int HEIGHT = 48;
	public static int BOUNDS_X = 18;
	public static int BOUNDS_Y = 26;
	public static int BOUNDS_WIDTH = 15;
	public static int BOUNDS_HEIGHT = 22;
	public static int START_HP = 20;
	private int superSpeed;
	
	Weapon[] weps;
	int currWep = 0;
	public Player(Handler handler, float x, float y, int speed,int superSpeed) {
		super(handler,x, y,WIDTH,HEIGHT, speed,START_HP,BOUNDS_X,BOUNDS_Y,BOUNDS_WIDTH,BOUNDS_HEIGHT,Assets.player);
		this.superSpeed = superSpeed;
		this.wepAnchorUp = new Point(width/2,10);
		this.wepAnchorDown = new Point(width/2,height-10);
		this.wepAnchorLeft = new Point(bounds.x,24);
		this.wepAnchorRight = new Point(bounds.x+bounds.width,24);
		weapon = new FlameSword();
		weps = new Weapon[]{new FlameSword(),new ShortSword(),new LongSword(),new ArmedSword()};
	}

	@Override
	public void tick() {
		KeyManager km = handler.getKeyManager();
		
		int moveX = 0;
		int moveY = 0;
		if (km.left) {
			moveX--;
			state = LEFT;
		}
		else if (km.right) {
			moveX++;
			state = RIGHT;
		}
		if (km.up) {
			moveY--;
			state = UP;
		}
		else if (km.down) {
			moveY++;
			state = DOWN;
		}
		if(km.shift){
			currWep++;
			currWep %= weps.length;
			equip(weps[currWep]);
			
			moveX *= superSpeed;
			moveY *= superSpeed;
		}else{
			moveX *= speed;
			moveY *= speed;
		}
		move(moveX, moveY);
		
		//jumping
		if(km.space && !isJumping){
			isJumping = true;
			jumpHeight = 10;
		}
		
		//attacking
		if(km.attack)
			attack();
		
		super.tick();
	}

	

}
