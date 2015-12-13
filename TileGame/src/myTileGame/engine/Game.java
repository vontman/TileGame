package myTileGame.engine;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import myTileGame.Handler;
import myTileGame.KeyManager;
import myTileGame.display.Camera;
import myTileGame.display.Gui;
import myTileGame.gfx.Assets;
import myTileGame.objects.tiles.Tile;
import myTileGame.states.GameState;
import myTileGame.states.State;

public class Game implements Runnable{
	public final static String GAME_NAME = "MyTileGame";
	private Handler handler ;
	private Camera camera;
	private KeyManager keyManager;
	private Gui gui;
	private State gameState;
	private CollisionSensor collisionSensor;
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
		
		if(State.getCurrentState() != null)
			State.getCurrentState().render(g,camera.getxOffset(),camera.getyOffset());

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
		double FPS_RENDER = 90;
		double FPS_TICK = 60;
		double now = System.nanoTime();
		double last = System.nanoTime();
		double timerR = 0;
		double timerT = 0;
		double timePerFrameR = 1e9/FPS_RENDER;
		double timePerFrameT = 1e9/FPS_TICK;
//		FPS count
//		int framePerSecond = 0;
//		double tempTimer = 0;
		
		while( running ){
			now = System.nanoTime();
			timerR += now - last;
			timerT += now - last;
			if(timerT >= timePerFrameT){
				timerT -= timePerFrameT;
				tick();
			}
			if(timerR >= timePerFrameR){
				timerR -= timePerFrameR;
				render();
				//fps count
//				framePerSecond++;
			}
			
			
//			/// fps count
//			tempTimer += now-last;
//			if( tempTimer >= 1e9 ){
//				tempTimer -= 1e9;
//				System.out.println( String.valueOf(framePerSecond));
//				framePerSecond = 0;
//			}
			
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
