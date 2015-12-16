package myTileGame.entites.objects.creatures;

import java.awt.image.BufferedImage;

import myTileGame.Handler;

public class Monster extends Creature{
	private long lastUpdated;
	private long moveTimer , stopTimer;
	public static final long MOVE_TIME = 4000;
	public static final long MOVE_DELAY = 2000;
	public Monster(Handler handler, float x, float y, int width, int height, int speed, int hp, int boundX, int boundY,
			int boundWidth, int boundHeight, BufferedImage[][] img) {
		super(handler, x, y, width, height, speed, hp, boundX, boundY, boundWidth, boundHeight, img);
		
		moving = true;
		state = getRandomState();
		team = 2;
	}
	public void tick(){
		if(isDead())
			return;
		if(moveTimer <= MOVE_TIME ){
			moveTimer += System.currentTimeMillis()-lastUpdated;
			moveRandomly();
			stopTimer = 0;
		}else{
			stopTimer += System.currentTimeMillis()-lastUpdated;
			if( stopTimer >= MOVE_DELAY ){
				state = getRandomState();
				moveTimer = 0;
			}
		}
		lastUpdated = System.currentTimeMillis();
		
		super.tick();
	}


}
