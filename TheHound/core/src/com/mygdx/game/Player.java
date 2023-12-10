package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.items.Item;
import com.badlogic.gdx.graphics.Texture;

import java.util.List;

public class Player extends  Character{

    public Player(int x, int y) {
        super(x, y);
        MAX_VELOCITY=45f;
        ACCEL=100f;
        color = Color.BLUE;
        setTexture("smiley.png");
    }

    public void update (float deltaTime, Input input, List<Entity> entityList) {

        for(Item item : inventory.itemList)
            item.effect(this);

        processKeys(input);
        updatePosition(deltaTime, entityList);
        bounds.setCenter(position);
        collision.setCenter(position);
    }

    void processKeys(Input input) {
        if (input.isKeyPressed(Input.Keys.A)) {
            accel.x = -ACCEL;
        } else if (input.isKeyPressed(Input.Keys.D)) {
            accel.x = ACCEL;
        } else {
            velocity.x = 0.0f;
            accel.x = 0.0f;
        }
        if (input.isKeyPressed(Input.Keys.W)) {
            accel.y = ACCEL;
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            accel.y = -ACCEL;
        } else {
            velocity.y = 0.0f;
            accel.y = 0.0f;
        }

        if(input.isKeyJustPressed(Input.Keys.I)){
            inventory.flipShow();
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

        Entity collisionObject = Collision.tryMove(this, deltaPos, entityList);

        if(collisionObject!=null) {
            accel.set(0, 0);
            velocity.set(0, 0);
        }else {
            position.add(deltaPos);
        }
    }

    public boolean pickUpItem(Item item){
        double distance = Math.sqrt(Math.pow((item.getBounds().getCenter(new Vector2()).x -this.bounds.getCenter(new Vector2()).x),2)+Math.pow((item.getBounds().getCenter(new Vector2()).y-this.bounds.getCenter(new Vector2()).y), 2));

        if(distance<=item.getPickUpRange()) {
            inventory.add(item);
            return true;
        }

        return false;
    }

    @Override
    public void render(Batch batch, ShapeRenderer shapeRenderer){
        if(position!=null) {
            shapeRenderer.setColor(color);
            if(texture!=null)
                batch.draw(texture, bounds.x, bounds.y, bounds.width, bounds.height);
            else
                shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
        }

        for(Item item : inventory.itemList)
            item.render(this, shapeRenderer);

    }

}
