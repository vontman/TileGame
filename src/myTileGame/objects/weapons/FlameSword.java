package myTileGame.objects.weapons;

import myTileGame.gfx.Assets;

public class FlameSword extends Weapon{
	private static final int ANCHOR_X = 6;
	private static final int ANCHOR_Y = 28;
	private static final int DMG = 6;
	private static final int RANGE = 35;
	private static final int DELAY = 350;
	private static final int THROWBACK = 20;
	public FlameSword() {
		super(32, 32, DMG, RANGE, THROWBACK, DELAY,ANCHOR_X,ANCHOR_Y, Assets.flamesword);
	}

}
