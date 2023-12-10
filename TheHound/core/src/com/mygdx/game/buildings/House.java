package com.mygdx.game.buildings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class House extends Building {
    public House(int x, int y, int WIDTH, int HEIGHT) {
        super(x, y, WIDTH, HEIGHT, new Texture(Gdx.files.internal("house.png")));
    }
}
