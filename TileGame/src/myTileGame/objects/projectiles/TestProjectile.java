package myTileGame.objects.projectiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import myTileGame.Handler;
import myTileGame.entites.objects.creatures.Creature;

public class TestProjectile extends Projectile{

	public TestProjectile(Handler handler, float x, float y, int moveX,
			int moveY,Creature caster) {
		super(handler, x, y, 32, 32, 10, 50,10, moveX, moveY, new BufferedImage[]{new BufferedImage(32,32,BufferedImage.TYPE_INT_RGB)},caster);
	}

	@Override
	public void render(Graphics g, float xOffset, float yOffset) {
		if(!isDead)
			g.fillRect((int)(x-xOffset), (int)(y-yOffset),32,32);
	}

}
