package myTileGame.world;

import myTileGame.Handler;
import myTileGame.objects.entites.statics.Tree;

public class TestWorld extends World{

	public TestWorld(Handler handler) {
		super(handler);

		new Tree(handler,80,80);
		new Tree(handler,180,180);
		new Tree(handler,280,80);
		new Tree(handler,80,800);
		new Tree(handler,480,400);
	}
	
	
}
