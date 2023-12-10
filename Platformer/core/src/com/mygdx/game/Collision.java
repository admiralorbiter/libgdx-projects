package com.mygdx.game;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

/*
 * Notes for Refactoring:
 *  tryMove: implement a spatial partitioning system to reduce the number of entities checked for collision
 * inAir: Replace hardcoded magic number values with constants
 * Rectangle Reuse: Reusing a rectangle to avoid creating a new one every time a collision is checked
 */
public final class Collision {

    public static Entity tryMove(Entity entity, Vector2 deltaPos, List<Entity> entityList) {

        Vector2 newPos = new Vector2(entity.position);
        newPos.add(deltaPos);

        Rectangle testBounds = new Rectangle(newPos.x, newPos.y, entity.bounds.width, entity.bounds.height);
        for(Entity object : entityList) {

            if(Intersector.intersectRectangles(testBounds, object.bounds, new Rectangle())) {
                return object;
            }
        }

        return null;
    }

    public static boolean inAir(Entity entity, List<Entity> entityList){
        if(entity.bounds.y-1<=Utilities.groundLevel)
            return false;

        Rectangle extendedBounds = new Rectangle(entity.bounds);
        extendedBounds.height=extendedBounds.height+2;
        extendedBounds.y--;
        for(Entity object : entityList) {
            if (Intersector.intersectRectangles(extendedBounds, object.bounds, new Rectangle()))
                return false;
        }

        return true;
    }
}
