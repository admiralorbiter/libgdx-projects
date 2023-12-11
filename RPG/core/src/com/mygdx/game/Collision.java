package com.mygdx.game;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.*;

import java.util.List;

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

    public static MapObject tryMove(Entity entity, Vector2 deltaPos, MapObjects collisionObjects) {

        Vector2 newPos = new Vector2(entity.position);
        newPos.add(deltaPos);

        Polygon rPoly = new Polygon(new float[]{0, 0, entity.bounds.width, 0, entity.bounds.width, entity.bounds.height, 0, entity.bounds.height});
        rPoly.setPosition(newPos.x, newPos.y);

        for(MapObject object : collisionObjects) {
            Polygon p=null;
            if(object instanceof PolygonMapObject)
                p = ((PolygonMapObject) object).getPolygon();
            else if(object instanceof RectangleMapObject) {
                Rectangle r = ((RectangleMapObject) object).getRectangle();
                p = new Polygon(new float[]{0, 0, r.width, 0, r.width, r.height, 0, r.height});
                p.setPosition(r.x, r.y);
            }

            if(Intersector.overlapConvexPolygons(rPoly, p)) {
                return object;
            }
        }

        return null;
    }
}
