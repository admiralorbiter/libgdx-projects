package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Worlds.WorldDAO;
import com.mygdx.game.entities.Entity;

import java.awt.*;
import java.util.ArrayList;

public class WorldRenderer {
    public RenderDAO renderer = new RenderDAO();
    private Player player = null;
    private Entity[][] entities = null;
    private Dimension viewDimensions = null;
    private Point start = null;
    private Point end = null;
    int viewWidth = 0;
    int viewHeight = 0;
    ArrayList<Entity> entitiesToDraw = new ArrayList<>();
    private int turn=0;

    public WorldRenderer() {
        renderer.create();

    }

    public void render(WorldDAO worldDAO) {

        //Sets member variables and clears the screen
        setup(worldDAO);

        //Start Batch Render process and set font size for displaying tile character
        renderer.basicBatchBegin();

        //Display turn tracker
        renderer.font.draw(renderer.batch,String.valueOf(turn),
                (float)Utilities.getScreenSize().width-100,(float)100);
        turn++;

        //Goes through the 2d tiles that are displayed
        for (int x = 0; x < viewWidth; x++) {
            for (int y = 0; y < viewHeight; y++) {

                //If entity exists & entity uses graphics -> set render point and add to draw list
                //Else entity exists -> draw tile character
                if(UtilEntities.isEntityDrawable(entities[start.x][start.y])) {
                    entities[start.x][start.y].hasBeenRendered = true;
                    entities[start.x][start.y].setRenderPoint(getRenderPoint(x, (y - 1)));
                    entitiesToDraw.add(entities[start.x][start.y]);
                    entities[start.x][start.y].update(worldDAO, turn);
                }
                else if(UtilEntities.isEntityRepresented(entities[start.x][start.y])) {
                    Point renderPoint = getRenderPoint(x, y);
                    renderer.font.draw(renderer.batch, entities[start.x][start.y].represntation,
                            renderPoint.x, renderPoint.y);
                    entities[start.x][start.y].hasBeenRendered = true;
                    entities[start.x][start.y].update(worldDAO, turn);
                }
                //Moves to the next tile on the y axis
                if(start.y<end.y)
                    start.y++;

            }

            //Resets y if necessary
            if(player.position.y-viewDimensions.height>0)
                start.y=(int)(player.position.y-viewDimensions.height);
            else
                start.y=0;

            //Moves to the next row on the x axis
            if(start.x<end.x)
                start.x++;
        }

        //End Batch Render process
        renderer.batch.end();

        //Start Shape Renderer process
        renderer.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.shapeRenderer.setColor(Color.WHITE);

        /*Test Function*/
        //showGrid();

        //Render Player Inventory
        player.inventory.render(renderer, viewDimensions);

        //Render View Box Frame
        Point renderPoint = getRenderPoint(0, 0);
        renderer.shapeRenderer.rect(renderPoint.x, renderPoint.y,
                viewDimensions.width*Utilities.getDefaultEntitySize().width*2,
                viewDimensions.height*Utilities.getDefaultEntitySize().height*2);

        //Render all entities that need graphics to be drawn
        for(Entity entity: entitiesToDraw) {
            //entity.render(renderer.shapeRenderer, worldDAO, turn);
            entity.render(renderer.shapeRenderer);
        }

        //End Shape Renderer Process
        renderer.shapeRenderer.end();
    }

    //Sets member variables and clears the screen
    private void setup(WorldDAO worldDAO){
        //Pull data from world data object
        player = worldDAO.player;
        entities = worldDAO.entityList;
        viewDimensions = worldDAO.viewDimensions;

        //Create method variables
        entitiesToDraw = new ArrayList<>();
        start = UtilitiesRenderer.calculateStartPoint(worldDAO);
        end = UtilitiesRenderer.calculateEndPoint(worldDAO);

        //Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Calculate the width and height of the tiles rendered
        //This is different from view dimensions when player is near a corner or edge
        viewWidth=end.x-start.x;
        viewHeight=end.y-start.y;

        //Reset check if entity has been rendered
        entityRenderReset();
    }

    //Rest check if entity has been renderered
    private void entityRenderReset(){
        //Goes through the 2d tiles that are displayed
        for (int x = 0; x < viewWidth; x++) {
            for (int y = 0; y < viewHeight; y++) {
                if(entities[x][y]!=null)
                    entities[x][y].hasBeenRendered=false;
            }
        }
    }

    public Point  getRenderPoint(int x, int y){
        int screenWidth = Utilities.getScreenSize().width;
        int screenHeight = Utilities.getScreenSize().height;
        int entityWidth = Utilities.getDefaultEntitySize().width;
        int entityHeight = Utilities.getDefaultEntitySize().height;

        return new Point(screenWidth/2+x*entityWidth-viewDimensions.width*entityWidth,
                screenHeight/2+y*entityHeight-viewDimensions.height*entityHeight);
    }

    private void showGrid(Dimension viewDimensions){
        for (int x = 0; x < viewDimensions.width*2; x++) {
            //Y Loop fucked up since y starts at the top of the screen
            for (int y = -1; y <viewDimensions.height*2-1; y++) {
                renderer.shapeRenderer.setColor(Color.WHITE);
                renderer.shapeRenderer.rect(Utilities.getScreenSize().width/2+x*Utilities.getDefaultEntitySize().width-viewDimensions.width*Utilities.getDefaultEntitySize().width,
                        Utilities.getScreenSize().height/2+y*Utilities.getDefaultEntitySize().height-viewDimensions.height*Utilities.getDefaultEntitySize().height,
                        Utilities.getDefaultEntitySize().width,Utilities.getDefaultEntitySize().height);
            }
        }
    }
}
