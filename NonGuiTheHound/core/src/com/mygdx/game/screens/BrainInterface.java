package com.mygdx.game.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.RenderDAO;

public class BrainInterface implements Screen {

    public Application game = null;
    Texture texture = new Texture(Gdx.files.internal("brain.png"));;
    RenderDAO renderDAO = new RenderDAO();

    public BrainInterface(Application game){
        this.game=game;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        renderDAO.basicBatchBegin();

        renderDAO.batch.draw(texture, 10, 10);

        renderDAO.batch.end();
    }

    @Override
    public void resize(int i, int i1) {

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

    public void processKeys(){

    }
}
