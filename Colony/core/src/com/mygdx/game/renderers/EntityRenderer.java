package com.mygdx.game.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Entity;

import java.util.List;

public class EntityRenderer {
    OrthographicCamera camera;
    SpriteBatch batch;
    ShapeRenderer shapeRenderer;

    public EntityRenderer(OrthographicCamera camera){
        this.camera=camera;
        batch = new SpriteBatch(5460);
        shapeRenderer = new ShapeRenderer();
    }

    public void begin(){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        batch.begin();
    }

    public void end(){
        batch.end();
        shapeRenderer.end();
    }

    public void render(Entity selection){
        selection.render(shapeRenderer);
    }

    public void render(List<Entity> entityList) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);

        for(Entity entity: entityList){
            entity.render(shapeRenderer);
        }
    }
}
