package myTileGame.engine;

import myTileGame.Handler;
import myTileGame.KeyManager;
import myTileGame.display.Camera;
import myTileGame.display.Gui;
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
	private boolean running;
	int counter = 0;
	double lol = 0;
	public Game(int width,int height){
		this.width = width;
		this.height = height;
		running = true;
		init();
		Thread thread = new Thread(this);
		thread.run();
	}
	public void tick(){
		keyManager.tick();
		world.tick();
//		counter++;
//		double fps = 30;
//		double timePerFrame = 1e9/fps;
//		lol += timePerFrame;
//		if(lol >= 1e9){
//			lol -= 1e9;
//			System.out.println(counter);
//			counter = 0;
//		}
	}
	public void render(){
		getGui().getGraphics().clearRect(0, 0,width, height);
		world.render(getGui().getGraphics());
	}
	public synchronized void start(){
	}
	public synchronized void stop(){
	}
	@Override
	public void run() {
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
		world = new TestWorld(handler);
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
