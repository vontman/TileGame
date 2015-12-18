package myTileGame.entites.objects.creatures;

import java.awt.image.BufferedImage;

import myTileGame.Handler;
import myTileGame.Utils;

public class Monster extends Creature{
	private long lastUpdated;
	private long moveTimer , stopTimer;
	public static final int MOVE_TIME_MIN = 5000;
	public static final int MOVE_TIME_MAX = 7000;
	public static final int MOVE_DELAY_MIN = 1000;
	public static final int MOVE_DELAY_MAX = 2000;
	private int currMoveDelay,currMoveTime;
	public Monster(Handler handler, float x, float y, int width, int height, int speed, int hp, int boundX, int boundY,
			int boundWidth, int boundHeight, BufferedImage[][] img) {
		super(handler, x, y, width, height, speed, hp, boundX, boundY, boundWidth, boundHeight, img);
		
		moving = true;
		state = getRandomState();
		team = 2;
		currMoveTime = Utils.getRandomNum(MOVE_TIME_MIN,MOVE_TIME_MAX);
		currMoveDelay = Utils.getRandomNum(MOVE_DELAY_MIN,MOVE_DELAY_MAX);
	}
	public void tick(){
		if(!isDead()){
			if(moveTimer <= currMoveTime){
				moveTimer += System.currentTimeMillis()-lastUpdated;
				moveRandomly();
				stopTimer = 0;
				currMoveDelay = Utils.getRandomNum(MOVE_DELAY_MIN,MOVE_DELAY_MAX);
			}else{
				stopTimer += System.currentTimeMillis()-lastUpdated;
				if( stopTimer >= currMoveDelay){
					state = getRandomState();
					moveTimer = 0;
					currMoveTime = Utils.getRandomNum(MOVE_TIME_MIN,MOVE_TIME_MAX);
				}
			}
			lastUpdated = System.currentTimeMillis();
		}
		super.tick();
	}


}
