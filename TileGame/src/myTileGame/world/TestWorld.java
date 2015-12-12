package myTileGame.world;

import myTileGame.Handler;
import myTileGame.entites.objects.creatures.Monster;
import myTileGame.objects.entites.statics.Tree;

public class TestWorld extends World{

	public TestWorld(Handler handler) {
		super(handler);
	}

	@Override
	public void init() {
		new Tree(handler,80,80);
		new Tree(handler,180,180);
		new Tree(handler,280,80);
		new Tree(handler,80,800);
		new Tree(handler,480,400);
		new Monster(handler,500,500,3,20);
		new Monster(handler,500,850,3,20);
		new Monster(handler,100,300,3,20);
		new Monster(handler,150,700,3,20);
	}
	
	
}
