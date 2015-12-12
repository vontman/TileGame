package myTileGame.objects.entites;

import java.util.LinkedList;

public class EntityManager {
	public static LinkedList<Entity>currEntities;
	public EntityManager() {
		currEntities = new LinkedList<Entity>();
	}
	public LinkedList<Entity> getCurrentEntities(){
		return currEntities;
	}
	public void addEntity(Entity e){
		if( currEntities != null )
			currEntities.add(e);
	}
}
