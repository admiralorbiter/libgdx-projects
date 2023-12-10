package com.mygdx.game.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Character;
import com.mygdx.game.Entity;
import com.mygdx.game.Player;
import com.mygdx.game.Utilities;

public abstract class Item extends Entity {

    int pickUpRange=30;

    public Item(int x, int y) {
        super(x, y);
    }

    public Item(int x, int y, int width, int height){
        super(x, y, width, height);
    }
    public Item(int x, int y, int width, int height, String type, Texture texture){
        super(x, y, width, height);
        this.type=type;
        this.texture=texture;
    }

    public abstract void effect(Character character);
    public abstract void render(Character character, ShapeRenderer shapeRenderer);

    public int getPickUpRange() {
        return pickUpRange;
    }

    public void setPickUpRange(int pickUpRange) {
        this.pickUpRange = pickUpRange;
    }
}
