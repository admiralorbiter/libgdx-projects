package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.buildings.Building;
import com.mygdx.game.buildings.House;
import com.mygdx.game.items.Item;
import com.mygdx.game.items.Zoom;

import java.util.List;

public final class Load {
    public static void items(List<Entity> entityList){
        entityList.add(new Zoom(120, 120, Utilities.getDefaultEntitySize().width, Utilities.getDefaultEntitySize().height));
    }

    public static void buildings(List<Entity> entityList){
        House house = new House(100, 120, 25, 25);
        entityList.add(house);
        entityList.add(house.getDoor());
    }

    public static void worldObjects(List<Entity> entityList){
        Entity entity = new Entity(0, 200, 250, 15);
        entity.setCollision(new Rectangle(0, 200, 250, 5));
        entity.setTexture(new Texture(Gdx.files.internal("wall.png")));
        entityList.add(entity);
    }
}
