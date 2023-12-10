package com.mygdx.game;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public final class Collision {

    public static Entity tryMove(Entity entity, Vector2 deltaPos, List<Entity> entityList) {

        Vector2 newPos = new Vector2(entity.bounds.x, entity.bounds.y);
        newPos.add(deltaPos);

        Rectangle testBounds = new Rectangle(newPos.x, newPos.y, entity.bounds.width, entity.bounds.height);
        for(Entity object : entityList) {

            if(Intersector.intersectRectangles(testBounds, object.bounds, new Rectangle())) {
                return object;
            }
        }

        return null;
    }
}
