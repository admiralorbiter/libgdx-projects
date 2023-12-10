package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import sun.nio.ch.Util;

import java.awt.*;

public class PauseRenderer {
    ShapeRenderer shapeRenderer;
    SpriteBatch batch;

    public PauseRenderer(){
        batch = new SpriteBatch(5460);
        shapeRenderer = new ShapeRenderer();
    }

    public void render(){
        batch.begin();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.LIGHT_GRAY);
        shapeRenderer.rect(Utilities.getScreenSize().width/3, Utilities.getScreenSize().height/3, Utilities.getScreenSize().width/3, Utilities.getScreenSize().height/3);

        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(Utilities.getScreenSize().width*4/10, Utilities.getScreenSize().height*5/10, Utilities.getScreenSize().width*2/10, Utilities.getScreenSize().height*1/10);
        shapeRenderer.rect(Utilities.getScreenSize().width*4/10, Utilities.getScreenSize().height*4/10-25, Utilities.getScreenSize().width*2/10, Utilities.getScreenSize().height*1/10);

        batch.end();
        shapeRenderer.end();
    }
}
