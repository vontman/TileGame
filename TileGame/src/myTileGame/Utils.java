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
	public static float clampF(float val,float min,float max){
		if( val > max )
			return max;
		if( val < min )
			return min;
		return val;
	}
	public static int clamp(int val,float min,float max){
		return (int)clampF(val*1F,min,max);
	}
}
