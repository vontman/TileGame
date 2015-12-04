package myTileGame;

import myTileGame.engine.Game;

public class Handler {
	private Game game;
	public Handler(Game game){
		this.game = game;
	}
	public Game getGame(){
		return this.game;
	}
}
