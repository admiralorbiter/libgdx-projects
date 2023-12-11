package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Pathfinding.Node;
import com.mygdx.game.Pathfinding.Pathfinding;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Entity {

    public Vector2 position = new Vector2();
    public Rectangle bounds = null;

    public Color color;
    public String type = "Unknown";

    private Point destination=null;
    private List<Node> path = null;
    private int pathIndex=0;

    public Entity(int x, int y) {
        position.set(x*Utilities.getDefaultEntitySize().width, y*Utilities.getDefaultEntitySize().height);
        bounds = new Rectangle(position.x, position.y, Utilities.getDefaultEntitySize().width, Utilities.getDefaultEntitySize().height);
        color = Color.WHITE;
    }

    public Entity(int x, int y, int WIDTH, int HEIGHT) {
        position.set(x*Utilities.getDefaultEntitySize().width, y*Utilities.getDefaultEntitySize().height);
        bounds = new Rectangle(position.x, position.y, WIDTH, HEIGHT);
        color = Color.WHITE;
    }

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.rect(position.x, position.y, bounds.width, bounds.height);
    }

    public void setType(String type){this.type=type;}

    public void setColor(Color color){
        this.color=color;
    }

    public Point getPosition(){return new Point((int)position.x, (int)position.y);}
    public void setDestination(Point destination, List<Entity> entities){
        this.destination=destination;
        this.path=Pathfinding.findPath(getPosition(), destination, Pathfinding.createBlockedPointList(getPosition(), entities));
        pathIndex=0;
        /*
        for(int i=0; i<=pathIndex; i++){
            System.out.println("TESTING: "+path.get(pathIndex));
        }*/
    }

    public Point getDestination(){return destination;}
    public String getType(){return type;}

    public void movement(List<Entity> entities){

        if(destination!=null){
            //This will probably be too slow for real implementation
            ArrayList<Point> blocked=Pathfinding.createBlockedPointList(getPosition(), entities);
            //if(blocked.contains(path.get(pathIndex).getPoint())){
            if(path.isEmpty()){
                if(blocked.contains(destination)){
                    blocked.remove(destination);
                }

                List<Node> path = Pathfinding.findPath(getPosition(), destination, blocked);
                this.path=path;
                pathIndex=0;


                if(path.isEmpty()) {
                    destination=null;
                    path=null;
                }
            }else {
                position=new Vector2(path.get(pathIndex).getPoint().x, path.get(pathIndex).getPoint().y);
                pathIndex++;

                if(getPosition().equals(destination)) {
                    destination=null;
                    path=null;
                    pathIndex=0;
                }
            }
        }
    }
}
