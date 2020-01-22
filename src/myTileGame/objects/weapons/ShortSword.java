package myTileGame.objects.weapons;

import myTileGame.gfx.Assets;

public class ShortSword extends Weapon{
	private static final int ANCHOR_X = 6;
	private static final int ANCHOR_Y = 28;
	private static final int DMG = 3;
	private static final int RANGE = 30;
	private static final int DELAY = 200;
	private static final int THROWBACK = 10;
	public ShortSword() {
		super(32, 32, DMG, RANGE, THROWBACK, DELAY,ANCHOR_X,ANCHOR_Y, Assets.shortsword);
	}

}
