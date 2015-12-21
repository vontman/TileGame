package myTileGame;


public class Utils {
	public static int readInt(String path){
		return Integer.valueOf(path);
	}

	public static int getRandomNum(int x){
		return (int)(Math.random()*x);
	}
	public static int getRandomNum(int x,int y){
		return (int)(Math.random()*Math.abs(y-x)+Math.min(x, y));
	}
}
