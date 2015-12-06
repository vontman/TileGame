package myTileGame.engine;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import myTileGame.Handler;
import myTileGame.KeyManager;
import myTileGame.display.Camera;
import myTileGame.display.Gui;
import myTileGame.entites.objects.creatures.Player;
import myTileGame.gfx.Assets;
import myTileGame.objects.entites.Entity;
import myTileGame.objects.entites.Tree;
import myTileGame.objects.tiles.Tile;
import myTileGame.states.State;
import myTileGame.world.TestWorld;
import myTileGame.world.World;

public class Game implements Runnable{
	public final static String GAME_NAME = "MyTileGame";
	private final double FPS = 60;
	private Handler handler ;
	private Camera camera;
	private KeyManager keyManager;
	private Gui gui;
	private World world;
	private State state;
	private CollisionSensor collisionSensor;
	private int height;
	private int width;
	private Thread thread;
	private boolean running;
	private Player player;
	public Game(int width,int height){
		this.width = width;
		this.height = height;
		start();
	}
	public void tick(){
		keyManager.tick();
		world.tick();
		player.tick();
		camera.tick();
	}
	public void render(){
		BufferStrategy bs = gui.getCanvas().getBufferStrategy();
		if(bs == null){
			gui.getCanvas().createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.clearRect(0, 0,width, height);
		world.render(g,camera.getxOffset(),camera.getyOffset());
		Entity.renderEntities(g,camera.getxOffset(),camera.getyOffset());

		g.dispose();
		bs.show();
	}
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
	}
	public synchronized void stop(){
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	@Override
	public void run() {
		init();
		double now = System.nanoTime();
		double last = System.nanoTime();
		double timer = 0;
		double timePerFrame = 1e9/FPS;
		while( running ){
			now = System.nanoTime();
			timer += now - last;
			if(timer >= timePerFrame){
				timer -= timePerFrame;
				tick();
				render();
			}
			last = now;
		}
	}
	private void init(){
		Assets.init();
		Tile.init();
		Entity.init();
		handler = new Handler(this);
		keyManager = new KeyManager();
		collisionSensor = new CollisionSensor(handler);
		gui = new Gui(handler);
		player = new Player(handler,0,50,50,3,5);
		new Tree(handler,1,80,80);
		camera = new Camera(handler,player);
		world = new TestWorld(handler,player);
		running = true;
	}
	public CollisionSensor getCollisionSensor(){
		return collisionSensor;
	}
	public World getWorld() {
		return world;
	}
	public State getState() {
		return state;
	}
	public Player getPlayer() {
		return player;
	}
	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}
	public void setSize(int width,int height) {
		this.width = width;
		this.height = height;
		gui.setSize(width, height);
	}
	public Camera getCamera() {
		return camera;
	}
	public KeyManager getKeyManager() {
		return keyManager;
	}
	public Gui getGui() {
		return gui;
	}
	
}
