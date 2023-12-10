package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.*;

import java.util.ArrayList;
import java.util.List;

public class WorldScreen implements Screen {

    public Application game;
    public Player player;
    public WorldRenderer worldRenderer;
    public List<Entity> entityList = new ArrayList<>();

    private Boolean paused=false;
    private PauseRenderer pauseRenderer = new PauseRenderer();

    public WorldScreen(Application game){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.game=game;
        this.player=game.player;
        worldRenderer = new WorldRenderer(player);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(paused==false) {
            worldRenderer.begin();

            update(delta);

            worldRenderer.render();

            worldRenderer.end();
        }else{
            pauseRenderer.render();
            parseInput(Gdx.input);
        }
    }

    private void update(float delta){
        player.update(delta, Gdx.input, entityList);
        parseInput(Gdx.input);
    }

    private void parseInput(Input input){
        if(input.isKeyJustPressed(Input.Keys.ESCAPE)){

            if(paused==true) {
                paused = false;
            }
            else
                paused=true;
        }
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

    }
}
