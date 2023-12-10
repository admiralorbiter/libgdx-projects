package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.Application;
import com.mygdx.game.Entity;
import com.mygdx.game.Player;
import com.mygdx.game.WorldRenderer;

import java.util.ArrayList;
import java.util.List;

public class WorldScreen implements Screen {

    Entity test = new Entity(110, 100);
    public Application game;
    public Player player;
    public WorldRenderer worldRenderer;
    public List<Entity> entityList = new ArrayList<>();

    public WorldScreen(Application game){
        this.game=game;
        this.player=game.player;
        worldRenderer= new WorldRenderer(player);
        entityList.add(test);
    }

    @Override
    public void show() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void render(float delta) {
        worldRenderer.begin();

        player.update(delta, Gdx.input, entityList);

        worldRenderer.render();
        test.update(delta, entityList);
        test.render(worldRenderer.shapeRenderer);

        worldRenderer.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        worldRenderer.dispose();
    }
}
