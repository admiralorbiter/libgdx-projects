package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.*;
import com.mygdx.game.NPCs.NPC;
import com.mygdx.game.items.Item;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WorldScreen implements Screen {

    NPC test = new NPC(110, 80);
    public Application game;
    public Player player;
    public WorldRenderer worldRenderer;
    public List<Entity> entityList = new ArrayList<>();

    DialogGUI dialog=null;

    public WorldScreen(Application game){
        Gdx.gl.glClearColor(131/255f, 168/255f, 133/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.game=game;
        this.player=game.player;
        worldRenderer= new WorldRenderer(player);
        entityList.add(test);
        Load.items(entityList);
        Load.buildings(entityList);
        Load.worldObjects(entityList);
        //dialog= new DialogGUI("Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World", worldRenderer.camera, new Texture(Gdx.files.internal("badlogic.jpg")), new Texture(Gdx.files.internal("badlogic.jpg")));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        worldRenderer.begin();

        player.update(delta, Gdx.input, entityList);


        for(Entity entity: entityList) {
            entity.render(worldRenderer.batch, worldRenderer.shapeRenderer);
        }
        worldRenderer.render();
        worldRenderer.end();

        if(dialog!=null)
            dialog.render(worldRenderer.camera, worldRenderer.font);

        if(player.getInventory().getShow()){
            player.getInventory().render(new SpriteBatch(), new ShapeRenderer());
        }

        worldRenderer.renderCursor(entityList);

        update(delta);
    }

    private void update(float delta){
        parseInput(Gdx.input);
    }

    private void parseInput(Input input){
        if(input.isButtonJustPressed(Input.Buttons.LEFT)){
            for(int i=0; i<entityList.size(); i++){
                if(entityList.get(i).getBounds().contains(Utilities.getWorldCoordinate(input.getX(), input.getY(), worldRenderer.camera).x, Utilities.getWorldCoordinate(input.getX(), input.getY(), worldRenderer.camera).y)){
                    if(entityList.get(i) instanceof Item){
                        if(player.pickUpItem((Item)entityList.get(i))) {
                            entityList.remove(i);
                            i++;
                        }
                    }else if(entityList.get(i) instanceof NPC){
                        if(((NPC) entityList.get(i)).getDialog().size()>0){
                            dialog = new DialogGUI(((NPC) entityList.get(i)).getDialog(), worldRenderer.camera, entityList.get(i).texture, player.texture);
                        }
                    }else{
                        if(!entityList.get(i).getClickResponse().isEmpty())
                            dialog = new DialogGUI(entityList.get(i).getClickResponse(), worldRenderer.camera, entityList.get(i).texture, player.texture);
                    }
                }
            }
        }

        if(input.isKeyJustPressed(Input.Keys.ESCAPE)){
            dialog=null;
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
