package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.List;

public final class Testing {
    public static void displayLines(ShapeRenderer shapeRenderer){
        shapeRenderer.setColor(Color.WHITE);
        for(int x=0; x<Utilities.getWorldSizeCompact().width; x++){
            shapeRenderer.line(x*Utilities.getDefaultEntitySize().width, 0, x*Utilities.getDefaultEntitySize().width, Utilities.getWorldSize().height);
        }

        for(int y=0; y<Utilities.getWorldSizeCompact().height; y++){
            shapeRenderer.line(0, y*Utilities.getDefaultEntitySize().height, Utilities.getWorldSize().width, y*Utilities.getDefaultEntitySize().height);
        }
    }

    public static List<Entity> getEnemyList(){
        List<Entity> entityList = new ArrayList<>();
        entityList.add(new Entity(25, 25));
        entityList.get(0).setType("tree");
        entityList.add(new Entity(20, 20));
        entityList.add(new Entity(5, 5));
        return entityList;
    }
}
