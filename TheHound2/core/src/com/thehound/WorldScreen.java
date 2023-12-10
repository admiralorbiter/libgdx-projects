package com.thehound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class WorldScreen implements Screen{

	TheHound game;
	World world;
	WorldRenderer worldRenderer;
	
	public WorldScreen(TheHound game) {
		this.game=game;
		world = new World(game);
		worldRenderer = new WorldRenderer(world);
		world.setCamera(worldRenderer.camera);
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		processKeys();
		world.update(delta);
		worldRenderer.render();
		
		if(world.nextScreen!=null)
			game.setScreen(world.nextScreen);
		
	}
	
	private void processKeys() {
		if (Gdx.input.isKeyPressed(Keys.Q)) {
			Gdx.app.exit();
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
