package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.mygdx.game.*;
import com.mygdx.game.Dialogue.DialogGUI;
import com.mygdx.game.Items.Item;
import com.mygdx.game.NPCS.NPC;
import com.mygdx.game.Worlds.World1;
import com.mygdx.game.Worlds.WorldDAO;
import com.mygdx.game.entities.Entity;

import java.awt.*;

public class WorldScreen implements Screen {

    public Application game = null;
    public WorldRenderer worldRenderer = null;
    public DialogGUI dialogGUI = null;
    WorldDAO worldDataObject = null;

    public WorldScreen(Application game){
        this.game=game;
        worldRenderer= new WorldRenderer();
        worldDataObject = new World1();
        //worldDataObject.load();
        worldDataObject.generateRandom();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        processKeys();
        worldDataObject.player.update(delta, Gdx.input, worldDataObject, worldRenderer);
        worldRenderer.render(worldDataObject);

        if(dialogGUI!=null){
            dialogGUI.update();
            dialogGUI.render(worldRenderer.renderer, worldDataObject.viewDimensions);
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

    public void processKeys(){

        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT))
            if(Utilities.getViewWindowBounds(worldDataObject.viewDimensions).contains(new Point(Gdx.input.getX(), Gdx.input.getY())))
                processLeftClick();

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
            if(dialogGUI!=null)
                dialogGUI=null;
    }

    private Entity getEntity(Point coordinate){
        Entity[][] entities = worldDataObject.entityList;

        return entities[coordinate.x][coordinate.y];
    }

    //Logic for how the player interacts with objects when they left click
    private void processLeftClick(){

        Point mouseCoordinates = Utilities.getMouseCoordinates(Gdx.input, worldDataObject.viewDimensions);
        System.out.println(getEntity(mouseCoordinates));
        if (getEntity(mouseCoordinates) != null) {

            if(getEntity(mouseCoordinates) instanceof Item)
                processItem(mouseCoordinates);
            else if(getEntity(mouseCoordinates) instanceof NPC)
               processNPC(mouseCoordinates);
            else if(getEntity(mouseCoordinates).getDescription()!=null)
                processGenericEntity(mouseCoordinates);
        }

    }

    //ITEM
    //If Item is in range -> Put item in inventory and make the previous tile null
    private void processItem(Point mouseCoordinates){
        Entity[][] entityList = worldDataObject.entityList;
        Player player = worldDataObject.player;

        if (Utilities.distanceBetweenTwoPoints(mouseCoordinates, player.getPosPoint()) < player.pickupRange) {
            player.inventory.add((Item) getEntity(mouseCoordinates));
            entityList[mouseCoordinates.x][mouseCoordinates.y] = null;
        }
    }

    //NPC
    //If NPC is in range -> Talk to NPC
    //TODO: create function if player in range
    private void processNPC(Point mouseCoordinates){
        Player player = worldDataObject.player;
        if (Utilities.distanceBetweenTwoPoints(mouseCoordinates, player.getPosPoint()) < player.pickupRange) {
            NPC npc = (NPC) getEntity(mouseCoordinates);
            dialogGUI = npc.getDialogGUI();
        }
    }

    //Other - Generic Entity
    //Retrieve and display description of objects
    private void processGenericEntity(Point mouseCoordinates){
        dialogGUI= new DialogGUI(getEntity(mouseCoordinates).getDescription());
    }
}
