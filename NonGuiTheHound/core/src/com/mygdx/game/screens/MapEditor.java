package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Application;
import com.mygdx.game.MapEditorRenderer;
import com.mygdx.game.Utilities;
import com.mygdx.game.entities.Entity;

import java.awt.*;
import java.util.ArrayList;

public class MapEditor implements Screen {

    Application game=null;
    MapEditorRenderer renderer = null;

    ArrayList stringList = new ArrayList<String>();

    Entity[][] entities = null;
    Stage stage;
    public MapEditor(Application game){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.game=game;
        renderer = new MapEditorRenderer();
        entities = new Entity[(int)renderer.edge.x][(int)renderer.edge.y];
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        processKeys();
        renderer.render(entities);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public void createEntity(final Point mouseCoord){
        stringList = new ArrayList<String>();

        Input.TextInputListener listener = new Input.TextInputListener() {
            @Override
            public void input(String text) {
                stringList.add(text);
                System.out.println(stringList.get(0));
                new Entity(text, mouseCoord.x, mouseCoord.y, Utilities.getDefaultEntitySize().width, Utilities.getDefaultEntitySize().height, entities);
            }

            @Override
            public void canceled() {

            }
        };
        Gdx.input.getTextInput(listener, "Rep"+mouseCoord.x+","+mouseCoord.y, "", null);


    }

    public void processKeys(){
        Input input = Gdx.input;

        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            System.out.println(Utilities.getMouseCoordinates(input, renderer));
            createEntity(Utilities.getMouseCoordinates(input, renderer));
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) || Gdx.input.isKeyJustPressed(Input.Keys.D)){
            renderer.moveRight();
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT) || Gdx.input.isKeyJustPressed(Input.Keys.A)){
            renderer.moveLeft();
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.UP) || Gdx.input.isKeyJustPressed(Input.Keys.W)){
            renderer.moveUp();
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN) || Gdx.input.isKeyJustPressed(Input.Keys.S)){
            renderer.moveDown();
        }
    }
}
