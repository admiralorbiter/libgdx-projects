package com.mygdx.game.buildings;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Door;
import com.mygdx.game.Entity;
import com.mygdx.game.Utilities;

public class Building extends Entity {

    Door door;

    public Building(int x, int y, int WIDTH, int HEIGHT, Texture texture) {
        super(x, y, WIDTH, HEIGHT);
        setTexture(texture);
        door = new Door((int)bounds.x+ Utilities.getDefaultEntitySize().width, (int)bounds.y+ Utilities.getDefaultEntitySize().width);
    }

    public Door getDoor() { return door; }
}
