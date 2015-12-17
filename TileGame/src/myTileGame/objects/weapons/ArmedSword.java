package myTileGame.objects.weapons;

import myTileGame.entites.objects.creatures.Creature;
import myTileGame.gfx.Assets;

public class ArmedSword extends Weapon{
	private static final int ANCHOR_X = 0;
	private static final int ANCHOR_Y = 32;
	private static final int DMG = 7;
	private static final int RANGE = 30;
	private static final int DELAY = 450;
	private static final int THROWBACK = 30;
	public ArmedSword(Creature owner) {
		super(owner,32, 32, DMG, RANGE, THROWBACK, DELAY,ANCHOR_X,ANCHOR_Y, Assets.armedsword);
	}

}
