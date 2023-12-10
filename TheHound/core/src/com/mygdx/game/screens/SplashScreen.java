package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.Application;
import com.mygdx.game.Utilities;

public class SplashScreen implements Screen {

    Application game;
    long displayTime;

    public SplashScreen(Application game){
        this.game=game;
        displayTime = TimeUtils.nanoTime();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.parameter.size = 60;
        game.font = game.generator.generateFont(game.parameter);
        game.font.setColor(Color.WHITE);
        game.font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        game.font.draw(game.batch, "Untitled Game", Utilities.getScreenSize().width/3, Utilities.getScreenSize().height/2);
        game.batch.end();

        // Hide splash after 2 seconds
        if (TimeUtils.nanoTime() - displayTime > 2000000000) {
            game.setScreen(game.gameScreen);
        }

        if (Gdx.input.isTouched()) {
            game.setScreen(game.gameScreen);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.setScreen(game.gameScreen);
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
