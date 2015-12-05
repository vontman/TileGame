package myTileGame.engine;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import myTileGame.Handler;
import myTileGame.KeyManager;
import myTileGame.display.Camera;
import myTileGame.display.Gui;
import myTileGame.entites.creatures.Player;
import myTileGame.gfx.Assets;
import myTileGame.states.State;
import myTileGame.tiles.Tile;
import myTileGame.world.TestWorld;
import myTileGame.world.World;

public class Game implements Runnable{
	public final static String GAME_NAME = "MyTileGame";
	private Handler handler ;
	private Camera camera;
	private KeyManager keyManager;
	private Gui gui;
	private World world;
	private State state;
	private Assets assets;
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
	}
	public void render(){
		BufferStrategy bs = gui.getCanvas().getBufferStrategy();
		Graphics g = gui.getCanvas().getGraphics();
		if(bs == null){
			gui.getCanvas().createBufferStrategy(3);
		}
		g.clearRect(0, 0,width, height);
		world.render(g);
		player.render(g);
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
		double fps = 60;
		double timePerFrame = 1e9/fps;
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
		handler = new Handler(this);
		keyManager = new KeyManager();
		gui = new Gui(handler);
		camera = new Camera(handler);
		player = new Player(handler,0,0,5);
		world = new TestWorld(handler,player);
		running = true;
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
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
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
