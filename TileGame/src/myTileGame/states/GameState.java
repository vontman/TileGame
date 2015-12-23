package myTileGame.states;

import java.awt.Graphics2D;

import myTileGame.Handler;
import myTileGame.entites.objects.creatures.Player;
import myTileGame.gfx.LightMap;
import myTileGame.world.TestWorld;
import myTileGame.world.World;

public class GameState extends State{
	protected Player player;
	protected World world;
	public GameState(Handler handler) {
		super(handler);
		this.world = new TestWorld(handler);
		handler.setWorld(world);
		this.world.init();
		player = new Player(handler,world.getSpawnX(),world.getSpawnY(),3,5);
		handler.getCamera().setCenterOfAttention(player);
		
	}
	public World getWorld(){
		return world;
	}
	public Player getPlayer(){
		return player;
	}
	@Override
	public void tick() {
		world.tick();
	}
	@Override
	public void render(Graphics2D g, float xOffset, float yOffset) {
		world.render(g,xOffset,yOffset);
	}

}
