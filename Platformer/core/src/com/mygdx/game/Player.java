package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class Player extends Entity {

    public Player(int x, int y) {
        super(x, y);
        bounds.setCenter(position);
    }

    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void update (float deltaTime, Input input, List<Entity> entityList) {
        processKeys(input, entityList);
        updatePosition(deltaTime, entityList);
        bounds.setPosition(position);
    }

    void processKeys(Input input, List<Entity> entityList) {

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            accel.x = -ACCEL;
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            accel.x = ACCEL;
        } else {
            velocity.x = 0.0f;
            accel.x = 0.0f;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.W) || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if(!Collision.inAir(this, entityList) && !jump) {
                accel.y = ACCEL;
                velocity.y=MAX_VELOCITY;
                jump=true;
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            if(bounds.y>Utilities.groundLevel)
                accel.y = -ACCEL;
        } else {
            if(bounds.y>Utilities.groundLevel){
                if(Collision.tryMove(this, new Vector2(0, -.2f), entityList)==null)
                    accel.y = -600f;
            }else{
                velocity.y = 0.0f;
                accel.y = 0.0f;
                jump=false;
            }
        }
    }
}
