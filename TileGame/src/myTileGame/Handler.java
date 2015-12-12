package myTileGame;

import myTileGame.display.Camera;
import myTileGame.engine.CollisionSensor;
import myTileGame.engine.Game;
import myTileGame.objects.entites.EntityManager;
import myTileGame.world.World;

public class Handler {
	private Game game;
	private World world;
	public Handler(Game game){
		this.game = game;
	}
	public Game getGame(){
		return this.game;
	}
	public World getWorld(){
		return this.world;
	}
	public void setWorld(World world){
		this.world = world;
	}
	public KeyManager getKeyManager(){
		return game.getKeyManager();
	}
	public Camera getCamera(){
		return game.getCamera();
	}
	public CollisionSensor getCollisionSensor(){
		return game.getCollisionSensor();
	}
	public int getGameWidth(){
		return game.getWidth();
	}
	public int getGameHeight(){
		return game.getHeight();
	}
	public int getWorldWidth(){
		return world.getFullWidth();
	}
	public int getWorldHeight(){
		return world.getFullHeight();
	}
	public EntityManager getEntityManager(){
		return getWorld().getEntityManager();
	}
}
