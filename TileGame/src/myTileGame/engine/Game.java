package myTileGame.engine;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

import myTileGame.Handler;
import myTileGame.KeyManager;
import myTileGame.display.Camera;
import myTileGame.display.Gui;
import myTileGame.gfx.Assets;
import myTileGame.gfx.DrawEngine;
import myTileGame.objects.tiles.Tile;
import myTileGame.states.GameState;
import myTileGame.states.State;

public class Game implements Runnable{
	public final static String GAME_NAME = "MyTileGame";
	private final double FPS = 60;
	private final double UPS = 90;
	private Handler handler ;
	private Camera camera;
	private KeyManager keyManager;
	private Gui gui;
	private State gameState;
	private CollisionSensor collisionSensor;
	private DrawEngine drawEngine;
	private int height;
	private int width;
	private Thread thread;
	private boolean running;
	private long ticks;
	public Game(int width,int height){
		this.width = width;
		this.height = height;
		start();
	}
	public void tick(){
		ticks++;
		keyManager.tick();

		if(State.getCurrentState() != null)
			State.getCurrentState().tick();
		
		drawEngine.tick();
		camera.tick();
	}
	public void render(){
		BufferStrategy bs = gui.getCanvas().getBufferStrategy();
		if(bs == null){
			gui.getCanvas().createBufferStrategy(3);
			return;
		}
		Graphics2D g = (Graphics2D)bs.getDrawGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.clearRect(0, 0,width, height);
		
		if(State.getCurrentState() != null)
			State.getCurrentState().render(g,camera.getxOffset(),camera.getyOffset());
		drawEngine.render(g, camera.getxOffset(), camera.getyOffset());
		
		g.dispose();
		bs.show();
	}
	public synchronized void start(){
		if( running )
			return ;
		thread = new Thread(this);
		thread.start();
	}
	public synchronized void stop(){
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		init();
		double now = System.nanoTime();
		double last = System.nanoTime();
		double uTimer = 0;
		double fTimer = 0;
		double uTimePerFrame = 1e9/UPS;
		double fTimePerFrame = 1e9/FPS;
//		FPS count
		int ticksPerSecond = 0;
		int framesPerSecond = 0;
		double tempTimer = 0;
		
		while( running ){
			now = System.nanoTime();
			uTimer += now - last;
			fTimer += now - last;
			while(uTimer >= uTimePerFrame){
				uTimer -= uTimePerFrame;
				tick();
				
//				ticks count
				ticksPerSecond++;
			}
			if(fTimer >= fTimePerFrame){
				fTimer -= fTimePerFrame;
				render();
				
//				fps count
				framesPerSecond++;
			}
			
			
			/// fps count
			tempTimer += now-last;
			if( tempTimer >= 1e9 ){
				tempTimer -= 1e9;
				System.out.println( "UPS : " + ticksPerSecond + " , FPS : " + framesPerSecond);
				framesPerSecond = 0;
				ticksPerSecond = 0;
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
		collisionSensor = new CollisionSensor(handler);
		drawEngine = new DrawEngine(handler);
		
		gameState = new GameState(handler);
		State.setCurrentState(gameState);
		
		running = true;
		
	}
	public CollisionSensor getCollisionSensor(){
		return collisionSensor;
	}
	public long getTicks(){
		return ticks;
	}
	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}
	public DrawEngine getDrawEngine(){
		return drawEngine;
	}
//	public void setSize(int width,int height) {
//		this.width = width;
//		this.height = height;
//		gui.setSize(width, height);
//	}
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
