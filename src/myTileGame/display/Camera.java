package myTileGame.display;

import myTileGame.Handler;
import myTileGame.objects.entites.Entity;

public class Camera {
	private Handler handler;
	private float xOffset;
	private float yOffset;
	private Entity centerOfAttention;
	public Camera(Handler handler) {
		this.handler = handler;
		xOffset = 0;
		yOffset = 0;
	}
	public void tick(){
		if(centerOfAttention == null)
			return;
		float cX = centerOfAttention.getX() + centerOfAttention.getWidth()/2;
		float cY = centerOfAttention.getY() + centerOfAttention.getHeight()/2;
		float worldWidth = handler.getWorldWidth();
		float worldHeight= handler.getWorldHeight();
		float gameWidth = handler.getGameWidth();
		float gameHeight = handler.getGameHeight();
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
	public void setCenterOfAttention(Entity e) {
		centerOfAttention = e;
	}
}
