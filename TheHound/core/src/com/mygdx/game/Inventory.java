package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.items.Item;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    public List<Item> itemList = new ArrayList<>();
    private boolean show = false;
    public Inventory(){

    }

    public void add(Item item){
        itemList.add(item);
        item.position=null;
    }

    public void setShow(boolean show){this.show=show;}
    public boolean getShow(){return show;}
    public void flipShow(){show=(!show);}

    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.LIGHT_GRAY);

        shapeRenderer.rect(Utilities.getScreenSize().width/4, Utilities.getScreenSize().height/10, Utilities.getScreenSize().width*2/4, Utilities.getScreenSize().height/4);
        shapeRenderer.end();


        int count=0;
        for(Item item : itemList) {
            //TODO
            //Refactor this so it is only called once when an item is added to the inventory
            item.bounds = new Rectangle(Utilities.getScreenSize().width / 4 + 10 * (count + 1), Utilities.getScreenSize().height / 10 + Utilities.getScreenSize().height / 4 - 2 * 50, 50, 50);

            if(item.getTexture()==null) {
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setColor(item.getColor());
                //shapeRenderer.rect(Utilities.getScreenSize().width / 4 + 10 * (count + 1), Utilities.getScreenSize().height / 10 + Utilities.getScreenSize().height / 4 - 2 * 50, 50, 50);

                shapeRenderer.rect(item.bounds.x, item.bounds.y, item.bounds.width, item.bounds.height);
                shapeRenderer.end();
            }else {
                batch.begin();
                if (item.getTexture() != null)
                    batch.draw(item.getTexture(), Utilities.getScreenSize().width / 4 + 10 * (count + 1), Utilities.getScreenSize().height / 10 + Utilities.getScreenSize().height / 4 - 2 * 50, 50, 50);
                batch.end();
                count++;
            }
        }
        parseInput(Gdx.input, shapeRenderer);
    }

    public void parseInput(Input input, ShapeRenderer shapeRenderer){
        Vector2 point = new Vector2(input.getX(), Utilities.getScreenSize().height-input.getY());
        for(Item item: itemList){
            System.out.println(item.bounds);
            if(item.bounds.contains(point)){
                shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
                shapeRenderer.setColor(Color.WHITE);
                shapeRenderer.rect(item.bounds.x, item.bounds.y, item.bounds.width, item.bounds.height);
                shapeRenderer.end();
            }
        }

    }
}
