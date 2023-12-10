package com.thehound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.utils.TimeUtils;

public class SplashScreen implements Screen{

	TheHound game;
	long displayTime;
	OrthographicCamera camera;
	
	public SplashScreen(TheHound game) {
		this.game=game;
		displayTime = TimeUtils.nanoTime();
		camera = new OrthographicCamera();
		camera.setToOrtho(true, 400, 800);
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    camera.update();
		game.batch.begin();
		game.font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		game.font.getData().setScale(10);
		game.font.draw(game.batch, "The Hound", 400, 800);
		game.batch.end();
		
		// Hide splash after 2 seconds
        if (TimeUtils.nanoTime() - displayTime > 2000000000) {
            game.setScreen(game.worldScreen);
        }

        if (Gdx.input.isTouched()) {
            game.setScreen(game.worldScreen);
        }
        if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
            game.setScreen(game.worldScreen);
        }
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
