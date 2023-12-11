package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Entity {

    public Vector2 position = new Vector2();
    public Rectangle bounds = null;

    public Color color = Color.WHITE;
    public String type = "Unknown";

    public Entity(float x, float y) {
        position.set(x, y);
        bounds = new Rectangle(position.x, position.y, Utilities.getDefaultEntitySize().width, Utilities.getDefaultEntitySize().height);
    }

    public Entity(float x, float y, int WIDTH, int HEIGHT) {
        position.set(x, y);
        bounds = new Rectangle(position.x, position.y, WIDTH, HEIGHT);
    }

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.rect(position.x, position.y, bounds.width, bounds.height);
    }

    public Rectangle getBounds(){return bounds;}
}
