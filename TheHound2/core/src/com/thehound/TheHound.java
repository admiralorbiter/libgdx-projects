package com.thehound;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class TheHound extends Game {
	SpriteBatch batch;
	BitmapFont font;
	
	public WorldScreen worldScreen;
	//public BuildingScreen buildingScreen;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.BLACK);
		worldScreen = new WorldScreen(this);
		setScreen(new SplashScreen(this));
	}

	@Override
	public void render () {
		/*Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		font.draw(batch, "Hello World", 200, 200);
		batch.end();*/
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}
