package com.mygdx.game.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Controller;
import com.mygdx.game.Entity;
import com.mygdx.game.Testing;
import com.mygdx.game.Utilities;

import java.awt.*;

public class WorldRenderer {

    OrthographicCamera camera;
    Controller player;
    SpriteBatch batch;
    BitmapFont font;
    public ShapeRenderer shapeRenderer;

    public WorldRenderer(Controller player, OrthographicCamera camera) {
        this.camera=camera;
        this.player=player;
        font = new BitmapFont();
        batch = new SpriteBatch(5460);
        shapeRenderer = new ShapeRenderer();

        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    public void begin(){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        batch.begin();
    }

    public void end(){
        batch.end();
        shapeRenderer.end();
    }

    public void render(Entity selection){
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(selection.position.x, selection.position.y, Utilities.getDefaultEntitySize().width, Utilities.getDefaultEntitySize().height);
    }

    public void render() {
        camera.position.set(player.position.x, player.position.y, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);

        //player.render(shapeRenderer);

        Testing.displayLines(shapeRenderer);

        renderMouse();
    }

    private void renderMouse() {
            Point mouseCoords = Utilities.calcMouseCoords(camera);
        if (Utilities.getWorldBounds().contains(mouseCoords)) {
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.rect(mouseCoords.x, mouseCoords.y, Utilities.getDefaultEntitySize().width, Utilities.getDefaultEntitySize().height);
        }
    }
}
