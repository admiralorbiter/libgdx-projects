package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class WorldRenderer {

    Player player;

    OrthographicCamera camera;
    SpriteBatch batch;
    BitmapFont font;
    public ShapeRenderer shapeRenderer;

    public WorldRenderer(Player player) {
        this.player=player;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 240, 160);
        camera.position.set(player.position.x, player.position.y, 0);
        font = new BitmapFont();
        batch = new SpriteBatch(5460);
        shapeRenderer = new ShapeRenderer();

        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
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

    public void render() {
        camera.position.set(player.position.x, player.position.y, 0);
        camera.update();
        player.render(shapeRenderer);
        shapeRenderer.setColor(Color.BROWN);
        shapeRenderer.rect(0, 0, Utilities.getScreenSize().width, Utilities.groundLevel);
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }

}
