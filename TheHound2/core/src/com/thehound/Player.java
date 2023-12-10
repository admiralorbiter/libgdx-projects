package com.thehound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;


public class Player extends Entity{

    Gun gun = new Gun(10, 10, 1);

	public final float MAX_VELOCITY = 90.0f;
    public final float ACCEL = 600f;

    public Vector2 velocity;
    public Vector2 accel;

    public Player(int x, int y) {
       	super(x, y);
        bounds.setCenter(position);
        velocity = new Vector2();
        accel = new Vector2();
    }

    public void update (float deltaTime, World world) {
        processKeys(world);
        updatePosition(deltaTime, world);
        bounds.setCenter(position);
    }

    void processKeys(World world) {
        if (Gdx.input.isKeyPressed(Keys.A)) {
            accel.x = -ACCEL;
        } else if (Gdx.input.isKeyPressed(Keys.D)) {
            accel.x = ACCEL;
        } else {
            velocity.x = 0.0f;
            accel.x = 0.0f;
        }
        if (Gdx.input.isKeyPressed(Keys.W)) {
            accel.y = ACCEL;
        } else if (Gdx.input.isKeyPressed(Keys.S)) {
            accel.y = -ACCEL;
        } else {
            velocity.y = 0.0f;
            accel.y = 0.0f;
        }

        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
                world.createBullet(gun.fire(position, new Vector2(Gdx.input.getX(), Gdx.input.getY())));
            }
        }
    }

    private void updatePosition(float deltaTime, World world) {
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

        Entity collisionObject = Collision.tryMove(this, deltaPos, world);

        if(collisionObject!=null) {
        	accel.set(0, 0);
        	velocity.set(0, 0);
        	checkDoor(collisionObject, deltaPos, world);
        }else {
        	 position.add(deltaPos);
        }
    }

    public void checkDoor(Entity collisionObject, Vector2 deltaPos, World world) {
    	if(collisionObject.type.equals("house")) {
    		House building = (House)collisionObject;
    		if(Collision.checkDoor(this, deltaPos, building.getDoor().bounds) && building.getDoor().getDoorOpen()) {
    			world.setScreen(new BuildingScreen(world.game, building, this));
    		}
    	}
    }
}
