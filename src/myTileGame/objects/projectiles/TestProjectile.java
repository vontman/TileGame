package myTileGame.objects.projectiles;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import myTileGame.Handler;
import myTileGame.entites.objects.creatures.Creature;

public class TestProjectile extends Projectile{

	public TestProjectile(Handler handler, float x, float y, int moveX,
			int moveY,Creature caster) {
		super(handler, x, y, 8, 14, 10, 50,5, moveX, moveY, new BufferedImage[]{new BufferedImage(14,8,BufferedImage.TYPE_INT_RGB)},caster);
		handler.addLight(this, 80f, .5f);
	}

	@Override
	public void render(Graphics2D g, float xOffset, float yOffset) {
		if(isDead)
			return;
		float tx = x-xOffset;
		float ty = y + height/2F-yOffset;
		AffineTransform af = g.getTransform();
		if( moveX > 0 ){	  				//right
			g.rotate(Math.toRadians(90),tx,ty);
		}
		else if( moveX < 0  ){	  				//left
			g.rotate(Math.toRadians(-90),tx,ty);
		}
		if( moveX > 0 && moveY > 0 ){  			//diagonal right-down
			g.rotate(Math.toRadians(45),tx,ty);
		}
		else if( moveX > 0 && moveY < 0 ){  			//diagonal right-up
			g.rotate(Math.toRadians(-45),tx,ty);
		}
		else if( moveX < 0 && moveY > 0 ){  			//diagonal left-down
			g.rotate(Math.toRadians(-45),tx,ty);
		}
		else if( moveX < 0 && moveY < 0 ){  			//diagonal left-up
			g.rotate(Math.toRadians(45),tx,ty);
		}
		g.fillRect((int)(x-xOffset), (int)(y-yOffset),width,height);
		g.setTransform(af);
	}

}
