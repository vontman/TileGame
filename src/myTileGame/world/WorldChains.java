package myTileGame.world;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import myTileGame.objects.entites.Entity;

public class WorldChains {
	private int width;
	private int height;
	private List<List<Entity>> chains;
	public WorldChains(int width,int height) {
		this.width = width;
		this.height = height;
		chains = new ArrayList<List<Entity>>(width*height);
		for(int i=0;i<width*height;i++)
			chains.add(new ArrayList<Entity>());
	}
	private List<Entity> getList(int index){
		if(index < 0 || index >= width*height)
			return new ArrayList<Entity>();
		List<Entity> temp = chains.get(index);
		if(temp == null){
			chains.set(index, new ArrayList<Entity>());
			temp = chains.get(index);
		}
		return temp;
	}
	public void addChain(int x,int y, Entity e){
		addChain(x*height+y,e);
	}
	public void addChain(int index, Entity e){
		getList(index).add(e);
	}
	public void removeChain(int x,int y, Entity e){
		removeChain(x*height+y,e);
	}
	public void removeChain(int index, Entity e){
		getList(index).remove(e);
	}
	public Iterator<Entity> getChains(int x,int y){
		return getChains(x*height+y);
	}
	public Iterator<Entity> getChains(int index){
		return getList(index).iterator();
	}
//	public void tick(){
//		int index = 0;
//		for(List<Entity>l : chains){
//			if( l == null )
//				continue;
//			Stack<Entity> st = new Stack<Entity>();
//			for(Entity e : l){
//				int x = index/height * Assets.CELL_WIDTH;
//				int y= (index - index/height) * Assets.CELL_HEIGHT;
//				if( !e.getBounds().intersects(x,y,Assets.CELL_WIDTH,Assets.CELL_HEIGHT))
//					st.push(e);
//				index ++ ;
//			}
//			while(!st.isEmpty()){
//				l.remove(st.pop());
//			}
//		}
//	}
}
