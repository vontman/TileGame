package myTileGame.world;

import myTileGame.Handler;
import myTileGame.entites.objects.creatures.monsters.Bat;
import myTileGame.entites.objects.creatures.monsters.BigWorm;
import myTileGame.entites.objects.creatures.monsters.EyeBall;
import myTileGame.entites.objects.creatures.monsters.Ghost;
import myTileGame.entites.objects.creatures.monsters.ManEater;
import myTileGame.objects.entites.statics.Tree;

public class TestWorld extends World{

	public TestWorld(Handler handler) {
		super(handler,"/worlds/testworld.png");
	}

	@Override
	public void init() {
		new Tree(handler,80,80);
		new Tree(handler,180,180);
		new Tree(handler,280,80);
		new Tree(handler,80,800);
		new Ghost(handler,50,50);
		new Bat(handler,500,500);
		new EyeBall(handler,500,850);
		new Bat(handler,100,300);
		new EyeBall(handler,150,700);
		new ManEater(handler,300,50);
		new BigWorm(handler,450,50);
		new BigWorm(handler,550,50);
		new BigWorm(handler,650,50);
		new BigWorm(handler,750,50);
	}
	
	
}
