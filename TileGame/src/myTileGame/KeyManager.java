package myTileGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{
	private boolean btns[];
	private boolean up,down,left,right;
	public void tick(){
		up = btns[KeyEvent.VK_UP];
		down = btns[KeyEvent.VK_DOWN];
		left = btns[KeyEvent.VK_LEFT];
		right = btns[KeyEvent.VK_RIGHT];
	}
	@Override
	public void keyPressed(KeyEvent e) {
		btns[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		btns[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

}
