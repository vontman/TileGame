package myTileGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{
	private boolean btns[];
	public boolean up,down,left,right,shift;
	public KeyManager() {
		btns = new boolean[256];
	}
	public void tick(){
		up = btns[KeyEvent.VK_UP];
		down = btns[KeyEvent.VK_DOWN];
		left = btns[KeyEvent.VK_LEFT];
		right = btns[KeyEvent.VK_RIGHT];
		shift = btns[KeyEvent.VK_SHIFT];
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() < 256)
			btns[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() < 256)
			btns[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

}
