package com.mygdx.game.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Character;
import com.mygdx.game.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Zoom extends Item{
    Random random = new Random();

    public Zoom(int x, int y){
        super(x, y);
        setup();
    }

    public Zoom(int x, int y, int width, int height) {
        super(x, y, width, height);
        setup();
    }

    public void setup(){
        type="zoom";
        texture = new Texture(Gdx.files.internal("lightningbolt.png"));
    }

    public void effect(Character character) {
        character.setMAX_VELOCITY(100);
        character.setACCEL(600);
    }

    public void render(Character character, ShapeRenderer shapeRenderer) {
        List<Color> colorList = new ArrayList();
        colorList.add(Color.FIREBRICK);
        colorList.add(Color.SCARLET);
        colorList.add(Color.YELLOW);

        if(character.getCurrentAccel().x>0){
            shapeRenderer.setColor(Color.GREEN);
            for(int i=0; i<3; i++) {
                int x = random.nextInt((int)character.bounds.width);
                int y = random.nextInt((int)character.bounds.height);
                shapeRenderer.setColor(colorList.get(i));
                shapeRenderer.rect(character.position.x - character.bounds.width-x, character.position.y+y-character.bounds.height/2, 1, 1);
            }
        }else if(character.getCurrentAccel().x<0){
            shapeRenderer.setColor(Color.GREEN);
            for(int i=0; i<3; i++) {
                int x = random.nextInt((int) character.bounds.width);
                int y = random.nextInt((int) character.bounds.height);
                shapeRenderer.setColor(colorList.get(i));
                shapeRenderer.rect(character.position.x + character.bounds.width+x, character.position.y+y-character.bounds.height/2, 1, 1);
            }
        }else  if(character.getCurrentAccel().y>0){
            shapeRenderer.setColor(Color.GREEN);
            for(int i=0; i<3; i++) {
                int x = random.nextInt((int) character.bounds.width);
                int y = random.nextInt((int) character.bounds.height);
                shapeRenderer.setColor(colorList.get(i));
                shapeRenderer.rect(character.position.x+x-character.bounds.width/2, character.position.y - character.bounds.height-y, 1, 1);
            }
        }else if(character.getCurrentAccel().y<0){
            shapeRenderer.setColor(Color.GREEN);
            for(int i=0; i<3; i++) {
                int x = random.nextInt((int) character.bounds.width);
                int y = random.nextInt((int) character.bounds.height);
                shapeRenderer.setColor(colorList.get(i));
                shapeRenderer.rect(character.position.x+x-character.bounds.width/2, character.position.y + character.bounds.height+y, 1, 1);
            }
        }
    }
}
