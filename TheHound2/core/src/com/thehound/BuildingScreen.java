package com.thehound;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class BuildingScreen implements Screen{

	TheHound game;
	
	Player player;
	
	List<Entity> entityList = new ArrayList<Entity>();
	
	OrthographicCamera camera;
	ShapeRenderer shapeRenderer;
	SpriteBatch batch;
	
	public BuildingScreen(TheHound game, House house, Player player) {
		this.game=game;
		this.player=player;
		house.load();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(true, 480, 320);
		camera.position.set(player.position.x, player.position.y, 0);
		
		batch = new SpriteBatch(5460);
		shapeRenderer = new ShapeRenderer();
	}
	
	@Override
	public void show() {
	
	}

	@Override
	public void render(float delta) {
	
	    camera.position.set(player.position.x, player.position.y, 0);
	    camera.update();
	    
		Gdx.gl.glClearColor(0, 0, 0, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    
	    shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeType.Filled);
	    
		batch.begin();
		player.render(shapeRenderer);
		batch.end();
		shapeRenderer.end();
		
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
