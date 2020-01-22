package myTileGame.states;

import java.awt.Graphics2D;

import myTileGame.Handler;

public abstract class State {
	protected Handler handler;
	private static State currentState = null;
	public static void setCurrentState(State state){
		currentState = state;
	}
	public static State getCurrentState(){
		return currentState;
	}
	public State(Handler handler) {
		this.handler = handler;
	}
	public abstract void tick();
	public abstract void render(Graphics2D g, float xOffset, float yOffset);
}
