package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.dialogue.DialogGUI;

import java.awt.*;

public class WorldRenderer {

    Player player;

    public OrthographicCamera camera;
    RenderDAO renderer = new RenderDAO();
    TiledMap map;
    OrthogonalTiledMapRenderer tileRenderer;
    DialogGUI dialogGUI = null;

    public WorldRenderer(Player player) {
        this.player=player;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 240, 160);
        camera.position.set(player.position.x, player.position.y, 0);

        //renderer.basicBatchBegin();

        renderer.setProjectMatrix(camera);

        map = new TmxMapLoader().load("Map\\samplemap.tmx");
        tileRenderer= new OrthogonalTiledMapRenderer(map);
    }

    public void begin(){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.shapeRenderer.setProjectionMatrix(camera.combined);

        renderer.batch.begin();
        renderer.batch.setProjectionMatrix(camera.combined);
    }

    public void end(){
        renderer.batch.end();
        renderer.shapeRenderer.end();
    }

    public void render() {
        processKeys();
        camera.position.set(player.position.x, player.position.y, 0);
        camera.update();

        player.render(renderer.shapeRenderer);
        tileRenderer.setView(camera);
        tileRenderer.render();

        tileRenderer.getBatch().begin();
        TiledMapTileLayer trees = (TiledMapTileLayer)map.getLayers().get("tree");
        tileRenderer.renderTileLayer(trees);
        tileRenderer.getBatch().end();


        if(dialogGUI!=null){
            dialogGUI.update();
            dialogGUI.render(renderer, player.getBounds());
        }
    }

    public void processKeys(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
            if(dialogGUI!=null)
                dialogGUI=null;
    }

}
