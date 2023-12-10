package com.thehound;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public final class Collision {
	 public static Entity tryMove(Entity entity, Vector2 deltaPos, World world) {
		
		 Vector2 newPos = new Vector2(entity.position);
		 newPos.add(deltaPos);
		 
		 Rectangle testBounds = new Rectangle(newPos.x, newPos.y, entity.WIDTH, entity.HEIGHT);
		 
		 for(Entity object : world.entityList) {
		
			 if(Intersector.intersectRectangles(testBounds, object.bounds, new Rectangle())) {
				 //System.out.println("Collision");
				 return object;
			 }
		 }
		 
		 return null;
    }
	
	 public static boolean checkDoor(Entity entity, Vector2 deltaPos, Rectangle doorBounds) {
		 Vector2 newPos = new Vector2(entity.position);
		 newPos.add(deltaPos);
		 
		 Rectangle testBounds = new Rectangle(newPos.x, newPos.y, entity.WIDTH, entity.HEIGHT);
		 
		 if(Intersector.intersectRectangles(testBounds, doorBounds, new Rectangle()))
			 return true;
		 
		 return false;
	 }
}
