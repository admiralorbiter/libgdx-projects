package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.Entity;

import java.awt.*;

public final class Collision {

    public static Entity tryMove(Entity entity, Vector2 deltaPos, Entity[][] entityList, Point edge) {

        Vector2 newPos = new Vector2(entity.position);
        newPos.add(deltaPos);
        //System.out.println(newPos);
        if(newPos.x<0 || newPos.y<0)
            return new Entity();

        if(newPos.x>edge.x-1 || newPos.y>edge.y-1)
            return new Entity();

        /*Rectangle testBounds = new Rectangle(newPos.x, newPos.y, entity.bounds.width, entity.bounds.height);
        for(int x=(int)testBounds.x; x<(int)testBounds.x+testBounds.width*Utilities.getDefaultEntitySize().width; x++){
            for(int y=(int)testBounds.y; y<(int)testBounds.y+testBounds.height*Utilities.getDefaultEntitySize().height; y++){
                //System.out.println(x+","+y);
                if(entityList[x][y]!=null && entityList[x][y]!=entity){
                    return entityList[x][y];
                }
            }
        }*/

        for(int x=0; x<100; x++){
            for(int y=0; y<100; y++){
                if(entityList[x][y]!=null && entityList[x][y]!=entity){
                    Rectangle bounds = new Rectangle((int)entityList[x][y].bounds.x, (int)entityList[x][y].bounds.y, (int)entityList[x][y].bounds.width, (int)entityList[x][y].bounds.height);
                    if(bounds.intersects(new Rectangle((int)newPos.x, (int)newPos.y, (int)entity.bounds.width, (int)entity.bounds.height)))
                        return entityList[x][y];
                }
            }
        }

        return null;
    }
}
