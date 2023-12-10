package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Worlds.WorldDAO;
import com.mygdx.game.entities.Entity;

import java.awt.*;

public class Player extends Entity {

    public final float MAX_VELOCITY = 90.0f;
    public final float ACCEL = 600f;

    public Vector2 velocity;
    public Vector2 accel;

    public Inventory inventory = new Inventory();
    public int pickupRange=3;

    public Player(int x, int y, Entity[][] entityList) {
        super("P", x, y, entityList);
        bounds.setCenter(position);
        velocity = new Vector2();
        accel = new Vector2();
        color= Color.RED;
    }

    public Player(int x, int y, int width, int height, Entity[][] entityList) {
        super("P", x, y, width, height, entityList);
        color= Color.RED;
    }

    public void update (float deltaTime, Input input, WorldDAO worldDAO, WorldRenderer worldRenderer) {
        processKeys(input, worldRenderer, worldDAO.entityList);
        updatePosition(deltaTime, worldDAO.entityList, worldDAO.worldDimension);
        bounds.setCenter(position);
    }

    void processKeys(Input input, WorldRenderer worldRenderer, Entity[][] entityList) {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            accel.x = -ACCEL;
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            accel.x = ACCEL;
        } else {
            velocity.x = 0.0f;
            accel.x = 0.0f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            accel.y = ACCEL;
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            accel.y = -ACCEL;
        } else {
            velocity.y = 0.0f;
            accel.y = 0.0f;
        }
        /*
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            if(Utilities.getViewWindowBounds(worldRenderer.viewDimensions).contains(new Point(input.getX(), input.getY()))) {
                Point mouseCoordinates = Utilities.getMouseCoordinates(input, worldRenderer);
                if (entityList[mouseCoordinates.x][mouseCoordinates.y] != null) {
                    if(entityList[mouseCoordinates.x][mouseCoordinates.y] instanceof Item) {
                        if (Utilities.distanceBetweenTwoPoints(mouseCoordinates, new Point((int) position.x, (int) position.y)) < pickupRange) {
                            inventory.add((Item) entityList[mouseCoordinates.x][mouseCoordinates.y]);
                            entityList[mouseCoordinates.x][mouseCoordinates.y] = null;
                        }
                    }else if(entityList[mouseCoordinates.x][mouseCoordinates.y] instanceof NPC){

                    }
                }
            }
        }*/
    }

    private void updatePosition(float deltaTime, Entity[][] entityList, Point edge) {
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

        Entity collisionObject = Collision.tryMove(this, deltaPos, entityList, edge);
        //System.out.println(collisionObject);
        if(collisionObject!=null) {
            accel.set(0, 0);
            velocity.set(0, 0);
        }else {
            entityList[(int)position.x][(int)position.y]=null;
            position.add(deltaPos);
            entityList[(int)position.x][(int)position.y]=this;
            //System.out.println(position);
        }
    }

    public void render(BitmapFont font, SpriteBatch batch) {
        font.setColor(Color.WHITE);
        font.draw(batch, represntation, Utilities.getScreenSize().width/2, Utilities.getScreenSize().height/2);
        //shapeRenderer.setColor(color);
        //shapeRenderer.rect(position.x, position.y, bounds.width, bounds.height);
    }
}
