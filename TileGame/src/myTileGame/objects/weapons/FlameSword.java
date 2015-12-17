package myTileGame.objects.weapons;

import myTileGame.entites.objects.creatures.Creature;
import myTileGame.gfx.Assets;

public class FlameSword extends Weapon{
	private static final int ANCHOR_X = 6;
	private static final int ANCHOR_Y = 28;
	private static final int DMG = 6;
	private static final int RANGE = 25;
	private static final int DELAY = 350;
	private static final int THROWBACK = 20;
	public FlameSword(Creature owner) {
		super(owner,32, 32, DMG, RANGE, THROWBACK, DELAY,ANCHOR_X,ANCHOR_Y, Assets.flamesword);
	}

}