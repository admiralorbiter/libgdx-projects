package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class Controller extends Entity {

    public final float MAX_VELOCITY = 90.0f;
    public final float ACCEL = 600f;

    public Vector2 velocity;
    public Vector2 accel;

    public Controller(int x, int y) {
        super(x, y);
        bounds.setCenter(position);
        velocity = new Vector2();
        accel = new Vector2();
        color= Color.CHARTREUSE;
    }

    public Controller(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void update (float deltaTime, List<Entity> entityList) {
        processKeys();
        updatePosition(deltaTime, entityList);
        bounds.setCenter(position);
    }

    void processKeys() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            accel.x = -ACCEL;
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            accel.x = ACCEL;
        } else {
            velocity.x = 0.0f;
            accel.x = 0.0f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            accel.y = ACCEL;
        } else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            accel.y = -ACCEL;
        } else {
            velocity.y = 0.0f;
            accel.y = 0.0f;
        }
    }

    private void updatePosition(float deltaTime, List<Entity> entityList) {
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

        //Entity collisionObject = Collision.tryMove(this, deltaPos, entityList);
        Entity collisionObject = null;

        Vector2 newPos = new Vector2(position);
        newPos.add(deltaPos);

        if(collisionObject!=null) {
            accel.set(0, 0);
            velocity.set(0, 0);
        }else if(newPos.x<0 || newPos.y<0 || newPos.x>Utilities.getWorldSize().width || newPos.y>Utilities.getWorldSize().height) {
            accel.set(0, 0);
            velocity.set(0, 0);
        }else{
            position.add(deltaPos);
        }
    }

    /*
    @Override
    public void render(ShapeRenderer shapeRenderer){
        shapeRenderer.setColor(color);
        shapeRenderer.circle(position.x, position.y, bounds.width/2);
    }

     */
}
