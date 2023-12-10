package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Pathfinding.Node;
import com.mygdx.game.Pathfinding.Pathfinding;
import com.mygdx.game.Utilities;
import com.mygdx.game.Worlds.WorldDAO;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Entity {

    public Vector2 position = new Vector2();
    public Rectangle bounds = null;

    public Color color = Color.WHITE;
    public String type = "Unknown";

    public String represntation=null;
    public boolean draw=false;
    public Point renderPoint = null;
    public String description = null;
    public boolean hasBeenRendered=false;

    //TODO - Put all these into an object
    public Point destination=null;
    List<Node> path = null;
    int pathIndex=0;

    public Entity(){

    }

    public Entity(String rep, int x, int y, Entity[][] entityList) {
        represntation=rep;
        position.set(x, y);
        bounds = new Rectangle(position.x, position.y, 1, 1);
        setEntity(entityList);
    }

    public Entity(String rep, int x, int y, int WIDTH, int HEIGHT, Entity[][] entityList) {
        represntation=rep;
        position.set(x, y);
        bounds = new Rectangle(position.x, position.y, WIDTH, HEIGHT);
        setEntity(entityList);
    }

    public void setGraphics(String[][] graphics){
        graphics[(int)position.x][(int)position.y]=represntation;
    }
    /*
    public void render(BitmapFont font, SpriteBatch batch) {
        font.setColor(Color.WHITE);
        font.draw(batch, represntation, Utilities.getDefaultEntitySize().width*position.x, Utilities.getDefaultEntitySize().height*position.y);
        //shapeRenderer.setColor(color);
        //shapeRenderer.rect(position.x, position.y, bounds.width, bounds.height);
    }*/

    public void setDraw(boolean draw){this.draw=draw;}

    public void setEntity(Entity[][] entity){
        /*
        boolean trigger=false;
        if(draw==true)
            trigger=true;
        draw=false;

        for(int x=0; x<bounds.width; x++){
            for(int y=0; y<bounds.height; y++){
                entity[(int)position.x+x][(int)position.y+y]=this;
                //entity[(int)position.x+x][(int)position.y+y].setDraw(false);
            }
        }

        if(trigger)
            entity[(int)position.x][(int)position.y].setDraw(true);

         */
        //entity[(int)position.x][(int)position.y]=this;
        for(int width=0; width<bounds.width; width++){
            for(int height=0; height<bounds.height; height++){
                entity[(int)position.x+width][(int)position.y+height]=this;
            }
        }
    }

    public void render(ShapeRenderer shapeRenderer, WorldDAO worldDAO, int turn){
        update(worldDAO, turn);
        //Point coord = Utilities.getEntityXY((int)position.x, (int)position.y, view);
        //System.out.println(bounds+" /  "+coord);
        if(!hasBeenRendered) {
            shapeRenderer.rect(renderPoint.x, renderPoint.y, bounds.width * Utilities.getDefaultEntitySize().width, bounds.height * Utilities.getDefaultEntitySize().height);
            hasBeenRendered=true;
        }
    }

    public void render(ShapeRenderer shapeRenderer){
        //Point coord = Utilities.getEntityXY((int)position.x, (int)position.y, view);
        //System.out.println(bounds+" /  "+coord);
        if(!hasBeenRendered) {
            shapeRenderer.rect(renderPoint.x, renderPoint.y, bounds.width * Utilities.getDefaultEntitySize().width, bounds.height * Utilities.getDefaultEntitySize().height);
            hasBeenRendered=true;
        }
    }

    public void update(WorldDAO worldDAO, int turn){

    }

    public void movement(WorldDAO worldDAO){
        List<Point> blockedPoints = Pathfinding.createBlockedPointList(worldDAO);
        if(destination!=null) {

            if(path==null) {
                path = Pathfinding.findPath(getPosPoint(), destination, blockedPoints);
                pathIndex = 0;

                if (path.isEmpty()) {
                    destination = null;
                    path = null;
                }
            } else {
                updatePosition(worldDAO, path.get(pathIndex).getVector2());
                pathIndex++;

                if(getPosPoint().equals(destination)){
                    destination=null;
                    path=null;
                    pathIndex=0;
                }
            }
        }
    }

    public void updatePosition(WorldDAO worldDAO, Vector2 newPosition){
        Entity[][] entities = worldDAO.entityList;

        for(int x=(int)position.x; x<position.x+bounds.width; x++){
            for(int y=(int)position.y; y<position.y+bounds.height; y++){
                entities[x][y]=null;
            }
        }
        position=newPosition;
        for(int x=(int)position.x; x<position.x+bounds.width; x++){
            for(int y=(int)position.y; y<position.y+bounds.height; y++){
                entities[x][y]=this;
            }
        }
    }

    public void setRenderPoint(Point renderPoint){this.renderPoint = renderPoint;}

    //Getters and Setters
    public void setDescription(String description){this.description=description;}
    public String getDescription(){return description;}
    public Vector2 getPosition(){return position;}
    public Point getPosPoint(){return new Point((int)position.x, (int)position.y);}
}
