package myTileGame.world;

public class WorldInfo {
	private int[][] board;
	private int spawnX;
	private int spawnY;
	private int width;
	private int height;
	public WorldInfo(int width,int height,int spawnX,int spawnY,int[][] board){
		this.width = width;
		this.height = height;
		this.spawnX = spawnX;
		this.spawnY = spawnY;
		this.board = board;
	}
	public int getElementAt(int x,int y){
		if(x < 0 || x >= width || y < 0 || y >= height){
			return -1;
//			System.out.println("Error : Element called out of board");
//			System.exit(1);
		}
		return board[x][y];
	}
	public int[][] getBoard() {
		return board;
	}
	public int getSpawnX() {
		return spawnX;
	}
	public int getSpawnY() {
		return spawnY;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
}
