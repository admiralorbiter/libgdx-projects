package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class IntroAreaScreen implements Screen {

    Entity house = new Entity(110, 100, 100, 100);
    public Application game;
    public Player player;
    public WorldRenderer worldRenderer;
    public List<Entity> entityList = new ArrayList<>();

    private Boolean paused=false;
    private PauseRenderer pauseRenderer = new PauseRenderer();

    public IntroAreaScreen(Application game){
        Gdx.gl.glClearColor(0, 153/255f, 51/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.game=game;
        this.player=game.player;
        worldRenderer = new WorldRenderer(player);
        worldRenderer.setColor(new Color(0, 153/255f, 51/255f, 1));
        house.setTexture(new Texture(Gdx.files.internal("house.png")));
        entityList.add(house);
        entityList.add(new Entity(83, 42, 8, 16, "hedge", new Texture(Gdx.files.internal("hedge.png"))));
        entityList.add(new Entity(83, 42-16, 8, 16, "hedge", new Texture(Gdx.files.internal("hedge.png"))));
        entityList.add(new Entity(83, 42-16-16, 8, 16, "hedge", new Texture(Gdx.files.internal("hedge.png"))));
        entityList.add(new Entity(112, 42, 8, 16, "hedge", new Texture(Gdx.files.internal("hedge.png"))));
        entityList.add(new Entity(112, 42-16, 8, 16, "hedge", new Texture(Gdx.files.internal("hedge.png"))));
        entityList.add(new Entity(112, 42-16-16, 8, 16, "hedge", new Texture(Gdx.files.internal("hedge.png"))));
        entityList.add(new Entity(83+16, 42-16-16-8, 32, 4, "hedge", new Texture(Gdx.files.internal("hedge.png"))));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(paused==false) {
            worldRenderer.begin();

            update(delta);

            worldRenderer.render();
            //house.render(worldRenderer.batch, worldRenderer.shapeRenderer);
            for(Entity entity: entityList)
                entity.render(worldRenderer.batch, worldRenderer.shapeRenderer);

            worldRenderer.end();
        }else{
            pauseRenderer.render();
            parseInput(Gdx.input);
        }
    }

    private void update(float delta){
        player.update(delta, Gdx.input, entityList);
        parseInput(Gdx.input);
    }

    private void parseInput(Input input){

        if(input.isButtonJustPressed(Input.Buttons.LEFT)){
            Rectangle doorContainer = new Rectangle(90, 50, 15, 20);
            Vector3 mousePoint = worldRenderer.camera.unproject(new Vector3(Gdx.input.getX(),Gdx.input.getY(), 0));
            if(doorContainer.contains(new Point((int)mousePoint.x, (int)mousePoint.y))){
                game.setScreen(game.gameScreen);
            }
        }
            System.out.println(worldRenderer.camera.unproject(new Vector3(Gdx.input.getX(),Gdx.input.getY(), 0)));

        if(input.isKeyJustPressed(Input.Keys.ESCAPE)){

            if(paused==true) {
                paused = false;
            }
            else
                paused=true;
        }
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
}
