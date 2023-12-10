package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.screens.*;

public class Application extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	public FreeTypeFontGenerator generator;
	public FreeTypeFontGenerator.FreeTypeFontParameter parameter;
	public WorldScreen gameScreen;
	public SelectScreen selectScreen;
	public IntroAreaScreen introAreaScreen;
	public ShapeRenderer shapeRenderer;
	public Player player = new Player(100, 40);

	@Override
	public void create () {
		shapeRenderer = new ShapeRenderer();
		batch = new SpriteBatch();
		generator = new FreeTypeFontGenerator(Gdx.files.internal("roboto.ttf"));
		parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 12;
		font = generator.generateFont(parameter);
		gameScreen = new WorldScreen(this);
		selectScreen = new SelectScreen(this);
		introAreaScreen = new IntroAreaScreen(this);
		setScreen(new SplashScreen(this));
		loadGame();
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		gameScreen.dispose();
		batch.dispose();
		font.dispose();
		generator.dispose();
		shapeRenderer.dispose();
	}

	public void loadGame(){
		Json json = new Json();
		FileHandle fileHandle = Gdx.files.local("bin/GameData.json");
		if (fileHandle.exists()) {
			GameData data = json.fromJson(GameData.class, Base64Coder.decodeString(fileHandle.readString()));
			player=data.getPlayer();
		}
		player.load_sprites();
	}

	public void saveGame(GameData data){
		Json json = new Json();
		FileHandle fileHandle = Gdx.files.local("bin/GameData.json");

		if (data != null) {
			fileHandle.writeString(Base64Coder.encodeString(json.prettyPrint(data)), false);
		}
	}
}
