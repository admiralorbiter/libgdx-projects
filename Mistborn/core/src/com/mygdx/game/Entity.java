package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class Entity {

    boolean jump=false;
    public final float MAX_VELOCITY = 90.0f;
    public final float ACCEL = 600f;
    public Vector2 velocity = new Vector2();
    public Vector2 accel = new Vector2();

    public Vector2 position = new Vector2();
    public Rectangle bounds = null;

    public Color color = Color.WHITE;
    public String type = "Unknown";

    public Entity(int x, int y) {
        position.set(x, y);
        bounds = new Rectangle(position.x, position.y, Utilities.getDefaultEntitySize().width, Utilities.getDefaultEntitySize().height);
    }

    public Entity(int x, int y, int WIDTH, int HEIGHT) {
        position.set(x, y);
        bounds = new Rectangle(position.x, position.y, WIDTH, HEIGHT);
    }

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
        //System.out.println(position);
    }

    public void update(float deltaTime, List<Entity> entityList){
        if(bounds.y>Utilities.groundLevel) {
            accel.y = -600f;
        }
        else{
            velocity.y = 0.0f;
            accel.y = 0.0f;
        }
        updatePosition(deltaTime, entityList);
        bounds.setPosition(position);
    }

    public void updatePosition(float deltaTime, List<Entity> entityList) {

        if (accel.len() > 0) {
            accel.scl(deltaTime);  // dv = a * dt
            velocity.add(accel.x, accel.y);  // vf = vi + dv
            // Limit velocity to max
            if (velocity.len() > MAX_VELOCITY) {
                velocity.nor().scl(MAX_VELOCITY);
            }
        }
        Vector2 deltaPos = new Vector2(velocity.x * deltaTime,
                velocity.y * deltaTime);
        List<Entity> entities = new ArrayList<>(entityList);
        entities.remove(this);
        Entity collisionObject = Collision.tryMove(this, deltaPos, entities);

        if(collisionObject!=null)
            System.out.println(bounds+", "+collisionObject.bounds);

        if(collisionObject!=null) {
            //System.out.println("Detected Collision - Not Updating Position");
            accel.set(0, 0);
            velocity.set(0, 0);
            jump=false;
        }else {
            position.add(deltaPos);
        }
        //System.out.println(position+", "+accel);
    }
}
