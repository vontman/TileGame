package myTileGame.objects.entites;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import myTileGame.Handler;
import myTileGame.gfx.Assets;
import myTileGame.objects.GameObject;

public abstract class Entity extends GameObject implements Comparable<Entity>{

	protected float x;
	protected float y;
	protected Handler handler ;
	protected Rectangle bounds;
	protected boolean isDead;
	public Entity(Handler handler, float x, float y, int width, int height,int boundX ,int boundY , int boundWidth,int boundHeight) {
		super(width, height);
		this.handler = handler;
		this.x = x;
		this.y = y;
		bounds = new Rectangle(boundX,boundY,boundWidth,boundHeight);
		handler.getEntityManager().addEntity(this);
		if(this.isSolid()){
			handler.getGame().getCollisionSensor().addEntity(this);
		}

//		for(int i=getLeftTileX(0);i<=getRightTileX(0);i++)
//			for(int j=getLowerTileY(0);j<=getUpperTileY(0);j++)
//				handler.getWorld().getWorldChains().addChain(i,j,this);
		
	}
	@Override
	public boolean isSolid(){
		return true;
	}
	@Override
	public int compareTo(Entity compareEntity){
		float y = getY() + getHeight();
		float y2 = compareEntity.getY()+compareEntity.getHeight();
		if( y > y2 )
			return 1;
		return -1;
	}
	public boolean checkEntityCollision(Entity e,float xOffset,float yOffset){
		return e.getBounds().intersects(getBounds(xOffset,yOffset)) && e.isSolid();
	}
	public boolean checkTileCollision(int  x , int y){
		if( handler.getWorld().getTileAt(x, y).isSolid() )
			return true;
		return false;
	}
	public int getLeftTileX(float xOffset){
		return (int)(x+bounds.x+xOffset)/Assets.CELL_WIDTH;
	}
	public int getRightTileX(float xOffset){
		return (int)(y+bounds.x+bounds.width+xOffset)/Assets.CELL_WIDTH;
	}
	public int getLowerTileY(float yOffset){
		return (int)(y+bounds.y+bounds.height+yOffset)/Assets.CELL_HEIGHT;
	}
	public int getUpperTileY(float yOffset){
		return (int)(y+bounds.y+yOffset)/Assets.CELL_HEIGHT;
	}
	public Point getCenterTile(float xOffset,float yOffset){
		return new Point(
				(int)(getBounds().getCenterX()+xOffset)/Assets.CELL_WIDTH,
				(int)(getBounds().getCenterY()+yOffset)/Assets.CELL_HEIGHT);
	}
	public Point getUpperLeftTile(float xOffset,float yOffset){
		return new Point(
				getLeftTileX(xOffset),
				getUpperTileY(yOffset));
	}
	public Point getUpperRightTile(float xOffset,float yOffset){
		return new Point(
				getRightTileX(xOffset),
				getUpperTileY(yOffset));
	}
	public Point getLowerLeftTile(float xOffset,float yOffset){
		return new Point(
				getLeftTileX(xOffset),
				getLowerTileY(yOffset));
	}
	public Point getLowerRightTile(float xOffset,float yOffset){
		return new Point(
				getRightTileX(xOffset),
				getLowerTileY(yOffset));
	}
	
	
	//class

	public abstract void tick();
	public abstract void render(Graphics2D g,float xOffset,float yOffset);
	
	
	//getters
	
	public int getWidth() {
		return width;
	}
	public Rectangle getBounds(){
		return getBounds(0F,0F);
	}
	public Rectangle getBounds(float xOffset,float yOffset){
		return new Rectangle((int)(x+bounds.x+xOffset),(int)(y+bounds.y+yOffset),bounds.width,bounds.height);
	}
	public int getHeight() {
		return height;
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public boolean isDead() {
		return isDead;
	}

}
