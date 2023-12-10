package com.thehound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class WorldRenderer {
	World world;
    OrthographicCamera camera;
    SpriteBatch batch;
    BitmapFont font;
    ShapeRenderer shapeRenderer;
    
    public WorldRenderer(World world) {
        this.world = world;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 320);
        camera.position.set(world.player.position.x, world.player.position.y, 0);
        font = new BitmapFont();
        
        batch = new SpriteBatch(5460);
        shapeRenderer = new ShapeRenderer();

    }
    
    public void render() {
    	camera.position.set(world.player.position.x, world.player.position.y, 0);
    	camera.update();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeType.Filled);

        batch.begin();
        renderPlayer();
        renderUI();
        renderBullets();
        batch.end();
        shapeRenderer.end();
    }

    private void renderBullets(){

        for(int i=0; i<world.bulletList.size(); i++){

            world.bulletList.get(i).render(shapeRenderer);
            if(world.bulletList.get(i).update()) {
                world.bulletList.remove(i);
                i--;
            }
        }
    }

    private void renderPlayer() {
    	//shapeRenderer.setColor(1, 1, 1, 1);
    	//shapeRenderer.rect(world.player.position.x, world.player.position.y, world.player.WIDTH, world.player.HEIGHT);
    	world.player.render(shapeRenderer);
    	world.house.render(shapeRenderer);
    }
    
    private void renderUI() {
    	 font.draw(batch, "World Screen! "+world.player.position, 200, 240);
        font.draw(batch, "Mouse Position: "+Gdx.input.getX()+", "+Gdx.input.getY(), 200, 200);
    }
}
