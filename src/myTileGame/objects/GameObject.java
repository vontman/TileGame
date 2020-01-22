package myTileGame.objects;

public abstract class GameObject {

	protected int width;
	protected int height;
	public GameObject(int width,int height) {
		this.width = width;
		this.height = height;
	}
	public boolean isSolid(){
		return false;
	}
}
