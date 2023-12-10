package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.TimeUtils;

import java.awt.*;

public class SplashScreen implements Screen {

    MUPuzzle game;
    OrthographicCamera camera;
    long displayTime;

    public SplashScreen(MUPuzzle game){
        this.game=game;
        displayTime = TimeUtils.nanoTime();
        camera = new OrthographicCamera();
        camera.setToOrtho(true, 10, 10);
        camera.update();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.begin();
        game.parameter.size = 60;
        game.font = game.generator.generateFont(game.parameter);
        game.font.setColor(Color.WHITE);
        game.font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        game.font.draw(game.batch, "The MU Puzzle", Utilities.getScreenSize().width/3, Utilities.getScreenSize().height/2);
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
