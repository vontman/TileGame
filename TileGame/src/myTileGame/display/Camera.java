package myTileGame.display;

import myTileGame.Handler;
import myTileGame.gfx.Assets;
import myTileGame.objects.entites.Entity;

public class Camera {
	private Handler handler;
	private float xOffset;
	private float yOffset;
	private Entity centerOfAttention;
	public Camera(Handler handler,Entity centerOfAttention) {
		this.handler = handler;
		xOffset = 0;
		yOffset = 0;
		this.centerOfAttention = centerOfAttention;
	}
	public void tick(){
		float cX = centerOfAttention.getX() + centerOfAttention.getWidth()/2;
		float cY = centerOfAttention.getY() + centerOfAttention.getHeight()/2;
		float worldWidth = handler.getGame().getWorld().getFullWidth();
		float worldHeight= handler.getGame().getWorld().getFullHeight();
		float gameWidth = handler.getGame().getWidth();
		float gameHeight = handler.getGame().getHeight();
		xOffset = cX - gameWidth/2;
		yOffset = cY - gameHeight/2;
		if( xOffset >= worldWidth-gameWidth )
			xOffset = worldWidth-gameWidth;
		if( yOffset >= worldHeight-gameHeight)
			yOffset = worldHeight-gameHeight;
		if( xOffset < 0 )
			xOffset = 0;
		if( yOffset < 0 )
			yOffset = 0;
		
	}
	public float getxOffset() {
		return xOffset;
	}
	public float getyOffset() {
		return yOffset;
	}
	public Entity getCenterOfAttention() {
		return centerOfAttention;
	}
}
