package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.*;
import com.mygdx.game.renderers.EntityRenderer;
import com.mygdx.game.renderers.WorldRenderer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.graphics.Color;

public class WorldScreen implements Screen {
    public Application game;
    public Controller player;
    public WorldRenderer worldRenderer;
    public List<Entity> entityList = new ArrayList<>();
    OrthographicCamera camera;
    private Actions hotkey = null;
    private ActionManager actionManager = new ActionManager();

    public EntityRenderer entityRenderer;

    Entity selection=null;

    public WorldScreen(Application game){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.game=game;
        this.player=game.player;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, 240, 160);
        camera.position.set(player.position.x, player.position.y, 0);
        worldRenderer= new WorldRenderer(player, camera);
        entityRenderer = new EntityRenderer(camera);

        entityList= Testing.getEnemyList();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        procesInput();

        entityRenderer.begin();
        //Will eventually need an engine that picks what the pawns will be doing based on thier perfrence
        for(Entity entity: entityList){
            if(entity.getDestination()!=null)
                entity.movement(entityList);
            else if(!actionManager.getChopList().isEmpty() && entity.getType()!="tree"){
                entity.setDestination(actionManager.getChopList().get(0).getPosition(), entityList);
                actionManager.getChopList().remove(0);
                System.out.println("Entity set to go to tree");
            }
        }
        entityRenderer.render(entityList);
        //if(selection!=null)
           // entityRenderer.render(selection);
        entityRenderer.end();

        worldRenderer.begin();
        player.update(delta, entityList);
        worldRenderer.render();
        if(selection!=null)
            worldRenderer.render(selection);
        worldRenderer.end();

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

    private void procesInput(){
        if(Gdx.input.isButtonJustPressed(0)){
            Point mouseCoords=Utilities.calcMouseCoords(camera);
            for(Entity entity: entityList){
                if(mouseCoords.equals(new Point((int)entity.position.x, (int)entity.position.y))){
                    //if(selection!=null)
                        //selection.setColor(Color.WHITE);
                    selection = entity;
                    //selection.setColor(Color.CYAN);
                    System.out.println("Selection");
                    break;
                }
            }

            if(hotkey!=null && selection.type!=null){
                switch (selection.type){
                    case "tree":
                        if(hotkey.equals(Actions.CHOP)){
                            actionManager.addAction(Actions.CHOP, selection);
                            System.out.println("Added Chop");
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        if(Gdx.input.isButtonJustPressed(1)){
            if(selection!=null){
                selection.setDestination(Utilities.calcMouseCoords(camera), entityList);
            }
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.B)){
            hotkey=Actions.CHOP;
        }
    }
}
