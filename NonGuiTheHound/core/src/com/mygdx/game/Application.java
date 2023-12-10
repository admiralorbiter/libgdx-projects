package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.screens.MapEditor;
import com.mygdx.game.screens.SplashScreen;
import com.mygdx.game.screens.WorldScreen;

public class Application extends Game {
	private WorldScreen gameScreen;

	@Override
	public void create () {
		gameScreen = new WorldScreen(this);
		setScreen(new SplashScreen(this));
		//setScreen(new MapEditor(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {

	}
}
