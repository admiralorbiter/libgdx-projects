package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Door extends Entity {
    public Door(int x, int y) {
        super(x, y, Utilities.getDefaultEntitySize().width, Utilities.getDefaultEntitySize().height*2);
        setTexture(new Texture(Gdx.files.internal("door.png")));
    }
}
