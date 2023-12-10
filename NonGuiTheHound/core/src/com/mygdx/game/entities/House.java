package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Utilities;

import java.awt.*;


public class House extends Entity {

    public House(int x, int y, Entity[][] entityList){
        super("H", x, y, 5, 5, entityList);
        draw=true;
        description="This is a normal house, no different than any of the others in the quiet village.";
    }

    public void render(ShapeRenderer shapeRenderer){
        shapeRenderer.rect(renderPoint.x, renderPoint.y, bounds.width* Utilities.getDefaultEntitySize().width, bounds.height*Utilities.getDefaultEntitySize().height);
        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BROWN);
        shapeRenderer.rect(renderPoint.x+Utilities.getDefaultEntitySize().width, renderPoint.y,Utilities.getDefaultEntitySize().width, Utilities.getDefaultEntitySize().height*2);
        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
    }


}
