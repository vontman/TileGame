package myTileGame.objects.weapons;

import myTileGame.gfx.Assets;

public class LongSword extends Weapon{
	private static final int ANCHOR_X = 0;
	private static final int ANCHOR_Y = 32;
	private static final int DMG = 5;
	private static final int RANGE = 30;
	private static final int DELAY = 400;
	private static final int THROWBACK = 20;
	public LongSword() {
		super(32, 32, DMG, RANGE, THROWBACK, DELAY,ANCHOR_X,ANCHOR_Y, Assets.longsword);
	}

}
