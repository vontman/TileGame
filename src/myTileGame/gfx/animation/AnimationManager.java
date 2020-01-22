package myTileGame.gfx.animation;

import java.awt.image.BufferedImage;
import java.util.Hashtable;

public class AnimationManager {
	private Hashtable<Integer,Animation> currAnimations;
	private int defaultKey;
	public AnimationManager(Animation animations[] , int keys[]) {
		//Error !! invalid keys to values relation
		if( animations.length != keys.length || animations.length == 0)
			throw new RuntimeException();
		
		currAnimations = new Hashtable<Integer,Animation>();
		
		// take last key as default
		defaultKey = keys[keys.length - 1];
		
		for( int i = 0 ; i < keys.length ; i++ ){
			//Error
			if(animations[i] == null)
				throw new RuntimeException();
			
			currAnimations.put( keys[i], animations[i] );
		}
	}
	public AnimationManager(BufferedImage imgs[][], int delay[] , int keys[]) {
		//Error !! invalid keys to values relation
		if( imgs.length != keys.length || imgs.length != delay.length || imgs.length == 0)
			throw new RuntimeException();
		
		currAnimations = new Hashtable<Integer,Animation>();
		
		// take last key as default
		defaultKey = keys[keys.length - 1];
		
		for( int i = 0 ; i < keys.length ; i++ ){
			currAnimations.put( keys[i], new Animation(imgs[i],delay[i]) );
		}
	}
	public AnimationManager(BufferedImage imgs[][], int delay , int keys[]) {
		//Error !! invalid keys to values relation
		if( imgs.length != keys.length || imgs.length == 0)
			throw new RuntimeException();
		
		currAnimations = new Hashtable<Integer,Animation>();
		
		// take last key as default
		defaultKey = keys[keys.length - 1];
		
		for( int i = 0 ; i < keys.length ; i++ ){
			currAnimations.put( keys[i], new Animation(imgs[i],delay) );
		}
	}
	public Animation getCurrAnimation(int key){
		if( currAnimations.containsKey(key) ){
			return currAnimations.get(key);
		}
		//default case
		return currAnimations.get(defaultKey);
	}
	public void setDefaultKey(int key){
		if( !currAnimations.containsKey(key) )
			throw new RuntimeException();
		defaultKey = key;
	}
	public BufferedImage getCurrImage(int key,boolean moving){
		return getCurrAnimation(key).getCurrentImage(moving);
	}
	public void setDelay(int delay) {
		for( Animation animation : currAnimations.values() )
			animation.setDelay(delay);
	}
}
