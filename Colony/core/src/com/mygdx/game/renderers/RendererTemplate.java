package com.mygdx.game.renderers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class RendererTemplate {
    OrthographicCamera camera;
    SpriteBatch batch;
    BitmapFont font;
    ShapeRenderer shapeRenderer;

    public RendererTemplate(OrthographicCamera camera){
        this.camera=camera;
        batch = new SpriteBatch(5460);
        shapeRenderer = new ShapeRenderer();
    }

    public void begin(){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        batch.begin();
    }

    public void end(){
        batch.end();
        shapeRenderer.end();
    }

    public void render() {
        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
    }
}
