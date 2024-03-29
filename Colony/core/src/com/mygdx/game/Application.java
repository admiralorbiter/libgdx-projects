package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.screens.SplashScreen;
import com.mygdx.game.screens.WorldScreen;

public class Application extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	public FreeTypeFontGenerator generator;
	public FreeTypeFontGenerator.FreeTypeFontParameter parameter;
	public WorldScreen gameScreen;
	public ShapeRenderer shapeRenderer;
	public Controller player = new Controller(0, 0);

	@Override
	public void create () {
		shapeRenderer = new ShapeRenderer();
		batch = new SpriteBatch();
		generator = new FreeTypeFontGenerator(Gdx.files.internal("roboto.ttf"));
		parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 12;
		font = generator.generateFont(parameter);
		gameScreen = new WorldScreen(this);
		setScreen(new SplashScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
		generator.dispose();
		shapeRenderer.dispose();
	}
}
