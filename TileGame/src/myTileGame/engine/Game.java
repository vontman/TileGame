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
	public Game(int width,int height){
		this.width = width;
		this.height = height;
		running = true;
		init();
		render();
	}
	public void tick(){
		
	}
	public void render(){
		getGui().getGraphics().clearRect(0, 0,width, height);
		world.render(getGui().getGraphics());
	}
	public synchronized void start(){
		
	}
	public synchronized void stop(){
		
	}
	private void init(){
		Assets.init();
		Tile.init();
		handler = new Handler(this);
		keyManager = new KeyManager();
		gui = new Gui(handler);
		camera = new Camera(handler);
		world = new TestWorld("/worlds/testworld.txt");
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
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
