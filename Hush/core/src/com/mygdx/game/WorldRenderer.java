package com.mygdx.game;

import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.awt.*;
import java.util.Random;

public class WorldRenderer {

    Player player;

    public OrthographicCamera camera;
    public SpriteBatch batch;
    BitmapFont font;
    public ShapeRenderer shapeRenderer;
    public Color color = new Color(0, 0, 0, 0);

    public WorldRenderer(Player player) {
        this.player=player;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 90, 60);
        camera.position.set(player.position.x, player.position.y, 0);
        font = new BitmapFont();
        batch = new SpriteBatch(5460);
        shapeRenderer = new ShapeRenderer();

        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    public void begin(){
        camera.position.set(player.position.x, player.position.y, 0);
        camera.update();

        Gdx.gl.glClearColor(color.r, color.g, color.b, color.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        batch.begin();
    }

    public void end(){
        batch.end();
        shapeRenderer.end();
    }

    public void render() {
        camera.position.set(player.position.x, player.position.y, 0);
        camera.update();

        player.render(batch, shapeRenderer);

        rain();
    }

    public void setColor(Color color){this.color=color;}

    public void rain(){
        Point leftCorner = new Point((int)(camera.position.x-camera.viewportWidth/2), (int)(camera.position.y-camera.viewportHeight/2));
        Point dimensions = new Point((int)camera.viewportWidth, (int)camera.viewportHeight);
        Random random = new Random();
        float x = leftCorner.x+random.nextFloat()*dimensions.x;
        float y = leftCorner.y+random.nextFloat()*dimensions.y;
        shapeRenderer.line(x, y, x, y-5);

        //shapeRenderer.rect(camera.position.x-camera.viewportWidth/2, camera.position.y-camera.viewportHeight/2, camera.viewportWidth, camera.viewportHeight);

    }

}
