package myTileGame.objects.weapons;

import myTileGame.entites.objects.creatures.Creature;
import myTileGame.gfx.Assets;

public class Sword extends Weapon{

	public Sword(Creature owner) {
		super(owner,32, 32, 5, 30, 20, 800, Assets.sword);
	}

}
