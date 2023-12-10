package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.*;
import java.util.List;

public class Player extends Entity {

    public final float MAX_VELOCITY = 90.0f;
    public final float ACCEL = 600f;

    public Vector2 velocity;
    public Vector2 accel;

    public Player(int x, int y) {
        super(x, y, 4, 8);
        bounds.setCenter(position);
        velocity = new Vector2();
        accel = new Vector2();
    }

    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void update (float deltaTime, Input input, List<Entity> entityList) {
        processKeys(input);
        updatePosition(deltaTime, entityList);
        bounds.setCenter(position);
    }

    void processKeys(Input input) {
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

        setSpriteIndex();

        if(collisionObject!=null) {
            accel.set(0, 0);
            velocity.set(0, 0);
        }else {
            position.add(deltaPos);
        }
    }

    public void setSpriteIndex(){
        if(accel.x ==0 && accel.y==0){
            textureIndex=0;
        }else if(accel.y<0){
            if(textureIndex!=1 || textureIndex!=2){
                textureIndex=1;
            }else if(textureIndex==1){
                textureIndex=2;
            }else{
                textureIndex=1;
            }
        }else if(accel.y>0){
            if(textureIndex!=10 || textureIndex!=11){
                textureIndex=10;
            }else if(textureIndex==10){
                textureIndex=11;
            }else if(textureIndex==11){
                textureIndex=10;
            }
        }else if(accel.x<0){
            if(textureIndex!=3){
                textureIndex=4;
            }else{
                textureIndex=3;
            }
        }else if(accel.x>0){
            if(textureIndex!=5){
                textureIndex=5;
            }else if(textureIndex<8){
                textureIndex++;
            }else{
                textureIndex=5;
            }
        }
    }

    public void load_sprites(){

        setTexture(new Texture(Gdx.files.internal("character/down_0.png")));
        setTexture(new Texture(Gdx.files.internal("character/down_1.png")));
        setTexture(new Texture(Gdx.files.internal("character/down_2.png")));
        setTexture(new Texture(Gdx.files.internal("character/left_0.png")));
        setTexture(new Texture(Gdx.files.internal("character/left_1.png")));
        setTexture(new Texture(Gdx.files.internal("character/right_0.png")));
        setTexture(new Texture(Gdx.files.internal("character/right_1.png")));
        setTexture(new Texture(Gdx.files.internal("character/right_2.png")));
        setTexture(new Texture(Gdx.files.internal("character/right_3.png")));
        setTexture(new Texture(Gdx.files.internal("character/up_0.png")));
        setTexture(new Texture(Gdx.files.internal("character/up_1.png")));
        setTexture(new Texture(Gdx.files.internal("character/up_2.png")));

    }

    /*
    @Override
    public void render(ShapeRenderer shapeRenderer){
        shapeRenderer.setColor(color);
        shapeRenderer.circle(position.x, position.y, bounds.width/2);
    }

     */
}
