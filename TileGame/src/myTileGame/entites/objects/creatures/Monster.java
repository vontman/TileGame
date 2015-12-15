package myTileGame.entites.objects.creatures;

import java.awt.image.BufferedImage;

import myTileGame.Handler;

public class Monster extends Creature{

	public Monster(Handler handler, float x, float y, int width, int height, int speed, int hp, int boundX, int boundY,
			int boundWidth, int boundHeight, BufferedImage[][] img) {
		super(handler, x, y, width, height, speed, hp, boundX, boundY, boundWidth, boundHeight, img);
		
		moving = true;
		state = (int) (Math.random()*5 + 1);
	}
	


}
