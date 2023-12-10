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
import com.mygdx.game.RenderDAO;
import com.mygdx.game.Utilities;

public class SplashScreen implements Screen {

    private long displayTime;
    private RenderDAO render = new RenderDAO();
    private Application game;

    public SplashScreen(Application game){
        this.game=game;
        displayTime = TimeUtils.nanoTime();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        render.batch.begin();
        render.parameter.size = 60;
        render.font = render.generator.generateFont(render.parameter);
        render.font.setColor(Color.WHITE);
        render.font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        render.font.draw(render.batch, "Untitled Game", Utilities.getScreenSize().width/3, Utilities.getScreenSize().height/2);
        render.batch.end();

        // Hide splash after 2 seconds
        if (TimeUtils.nanoTime() - displayTime > 2000000000) {
            //game.setScreen(game.gameScreen);
            game.setScreen(new WorldScreen(game));
        }

        if (Gdx.input.isTouched()) {
            //game.setScreen(game.gameScreen);
            game.setScreen(new WorldScreen(game));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            //game.setScreen(game.gameScreen);
            game.setScreen(new WorldScreen(game));
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
