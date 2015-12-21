package myTileGame.objects.weapons;

import myTileGame.gfx.Assets;

public class ArmedSword extends Weapon{
	private static final int ANCHOR_X = 0;
	private static final int ANCHOR_Y = 32;
	private static final int DMG = 7;
	private static final int RANGE = 40;
	private static final int DELAY = 450;
	private static final int THROWBACK = 30;
	public ArmedSword() {
		super(32, 32, DMG, RANGE, THROWBACK, DELAY,ANCHOR_X,ANCHOR_Y, Assets.armedsword);
	}

}
