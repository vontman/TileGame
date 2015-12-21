package myTileGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{
	private boolean btnsPressed[];
	private boolean btnsReleased[];
	private boolean btnsTyped[];
	public boolean up,down,left,right,shift,space,attack,messile,switchWep;
	public KeyManager() {
		btnsPressed = new boolean[256];
		btnsReleased = new boolean[256];
		btnsTyped = new boolean[256];
	}
	public void tick(){
		up = btnsPressed[KeyEvent.VK_UP];
		down = btnsPressed[KeyEvent.VK_DOWN];
		left = btnsPressed[KeyEvent.VK_LEFT];
		right = btnsPressed[KeyEvent.VK_RIGHT];
		shift = btnsPressed[KeyEvent.VK_SHIFT];
		space = btnsPressed[KeyEvent.VK_SPACE];
		attack = btnsPressed[KeyEvent.VK_X];
		messile = btnsReleased[KeyEvent.VK_Z];
		switchWep = btnsPressed[KeyEvent.VK_C];
	}
	public boolean getMessile(){
		boolean temp = messile;
		btnsReleased[KeyEvent.VK_Z] = false;
		return temp;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() < 256 )
			btnsPressed[e.getKeyCode()] = true;
			btnsReleased[e.getKeyCode()] = false;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() < 256){
			btnsPressed[e.getKeyCode()] = false;
			btnsReleased[e.getKeyCode()] = true;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyCode() < 256){
			btnsTyped[e.getKeyCode()] = true;
		}
	}

}
