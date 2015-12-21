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
	private Queue<Entity>pendingAddList;
	private Queue<Entity>pendingRemoveList;
	private Handler handler;
	public EntityManager(Handler handler) {
		this.handler = handler;
		currEntities = new ArrayList<Entity>();
		pendingAddList = new LinkedList<Entity>();
		pendingRemoveList = new LinkedList<Entity>();
	}
	public Iterator<Entity> getCurrentEntities(){
		return currEntities.iterator();
	}
	public void addEntity(Entity e){
		if(e == null || currEntities == null)
			return;
		if(handler.getWorld().isTicking())
			pendingAddList.add(e);
		else
			currEntities.add(e);	
	}
	public void removeEntity(Entity e){
		if(e == null || currEntities == null)
			return;
		if(handler.getWorld().isTicking())
			pendingRemoveList.add(e);
		else
			currEntities.remove(e);
	}
	public void removeEntity(int index){
		if(index < 0 || index >= currEntities.size())
		if(handler.getWorld().isTicking())
			pendingRemoveList.add(currEntities.get(index));
		else if( !handler.getWorld().isTicking() )
			currEntities.remove(index);
	}
	public void checkPending(){
		while(!pendingAddList.isEmpty()){
			Entity e = pendingAddList.poll();
			if( e != null )
				currEntities.add(e);
		}
		while(!pendingRemoveList.isEmpty()){
			Entity e = pendingRemoveList.poll();
			if( e != null )
				currEntities.remove(e);
		}
	}
	public void sortEntities() {

		if(currEntities != null)
			Collections.sort(currEntities);
	}
}
