package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.awt.*;

public class MUPuzzle extends Game {
	SpriteBatch batch;
	BitmapFont font;
	FreeTypeFontGenerator generator;
	FreeTypeFontGenerator.FreeTypeFontParameter parameter;
	GameScreen gameScreen;

	@Override
	public void create () {
		batch = new SpriteBatch();
		//font = new BitmapFont();
		generator = new FreeTypeFontGenerator(Gdx.files.internal("roboto.ttf"));
		parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 12;
		font = generator.generateFont(parameter);
		gameScreen = new GameScreen(this);
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
	}
}
