package myTileGame.objects.entites;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import myTileGame.Handler;

public class EntityManager {
	public static List<Entity>currEntities;
	private Queue<Entity>pendingList;
	private Handler handler;
	public EntityManager(Handler handler) {
		this.handler = handler;
		currEntities = new ArrayList<Entity>();
		pendingList = new LinkedList<Entity>();
	}
	public Iterator<Entity> getCurrentEntities(){
		return currEntities.iterator();
	}
	public void addEntity(Entity e){
		if(e == null || currEntities == null)
			return;
		if(handler.getWorld().isTicking())
			pendingList.add(e);
		else
			currEntities.add(e);	
	}
	public void removeEntity(Entity e){
		if(currEntities != null)
			currEntities.remove(e);
	}
	public void removeEntity(int index){
		if(currEntities != null)
			currEntities.remove(index);
	}
	public void checkPending(){
		while(!pendingList.isEmpty()){
			Entity e = pendingList.poll();
			if( e != null )
				currEntities.add(e);
		}
	}
	public void sortEntities() {

		if(currEntities != null)
			Collections.sort(currEntities);
	}
}
