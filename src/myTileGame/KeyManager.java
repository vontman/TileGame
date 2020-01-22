package myTileGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Hashtable;

public class KeyManager implements KeyListener{
	private Handler handler;
	private Hashtable<Integer,Integer>keysPressed;
	private Hashtable<Integer,Boolean>keysType;
	private Hashtable<String,Integer>keyMap;
	public KeyManager(Handler handler) {
		this.handler = handler;
		keyMap = new Hashtable<String,Integer>();
		keysType = new Hashtable<Integer,Boolean>();
		keysPressed = new Hashtable<Integer,Integer>();
		initKeyMap();
	}
	private void initKeyMap() {
		addKey("up", KeyEvent.VK_W,false);
		addKey("left", KeyEvent.VK_A,false);
		addKey("down", KeyEvent.VK_S,false);
		addKey("right", KeyEvent.VK_D,false);
		
		addKey("btn1", KeyEvent.VK_G,true);
		addKey("btn2", KeyEvent.VK_H,true);
		
		addKey("switch", KeyEvent.VK_E,true);
		addKey("jump", KeyEvent.VK_SPACE,true);
		addKey("sprint", KeyEvent.VK_SHIFT,false);
		
		addKey("pause",KeyEvent.VK_ESCAPE,true);
		
		addKey("op1",KeyEvent.VK_1,true);
		addKey("op2",KeyEvent.VK_2,true);
		addKey("op3",KeyEvent.VK_3,true);
		addKey("op4",KeyEvent.VK_4,true);
		addKey("op5",KeyEvent.VK_5,true);
		addKey("op6",KeyEvent.VK_6,true);
	}
	// type = { 0 => allow continuous detection, 1 => one time per press};
	private void addKey(String name,Integer key, boolean type){
		keyMap.put(name, key);
		keysType.put(key, type);
	}
	public boolean getKeyPress(String keyName){
		if(!keyMap.containsKey(keyName)){
			//error
			System.out.println("ERROR : Invalid Key Request : " + keyName);
		}
		
		int key = keyMap.get(keyName);
		boolean type = true;
		if( !keysType.containsKey(key) || keysType.get(key) == false )type = false;
		if(!keysPressed.containsKey(key))
			return false;
		int val = keysPressed.get(key);
		if(!type)
			return val > 0;
		return val == handler.getGame().getTicks();
	}
	public void tick(){
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if( !keysPressed.containsKey(e.getKeyCode()) || keysPressed.get(e.getKeyCode()) == -1 ){
				keysPressed.put(e.getKeyCode(), handler.getGame().getTicks()+1);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keysPressed.put(e.getKeyCode(), -1);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
