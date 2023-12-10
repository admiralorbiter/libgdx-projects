package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Items.Item;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    public List<Item> itemList = new ArrayList<>();
    private boolean show = false;

    private MetalsDataObject metalsDAO = new MetalsDataObject();

    public Inventory(){

    }

    public void add(Item item){
        if(item.getPickupFlag()) {
            itemList.add(item);
            item.position = null;
        }
    }

    public void render(RenderDAO renderer, Dimension viewDimensions){

        renderer.shapeRenderer.setColor(Color.MAROON);
        //Draw the inventory box
        renderer.shapeRenderer.rect(Utilities.getScreenSize().width/2-viewDimensions.width*Utilities.getDefaultEntitySize().width, 0, viewDimensions.height*Utilities.getDefaultEntitySize().height*2, (Utilities.getScreenSize().height-viewDimensions.height*Utilities.getDefaultEntitySize().height*2)/2);

        //Draw metal boxes
        int width = Utilities.getScreenSize().width-viewDimensions.width*Utilities.getDefaultEntitySize().width;

        int index=0;
        for(int x=0; x<6; x++) {
            renderer.shapeRenderer.rect(5+(width / 24)*x+5*x, Utilities.getScreenSize().height / 2+(Utilities.getScreenSize().height / 12)+20, width / 24, Utilities.getScreenSize().height / 12);

            renderer.shapeRenderer.end();
            renderer.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            renderer.shapeRenderer.rect(5+(width / 24)*x+5*x, Utilities.getScreenSize().height / 2+(Utilities.getScreenSize().height / 12)+20, width / 24, (int)(Utilities.getScreenSize().height / 12*metalsDAO.metals.get(index).metalRatio));
            renderer.shapeRenderer.end();

            renderer.basicBatchBegin();
            renderer.setFontsize(16);
            renderer.font.draw(renderer.batch, metalsDAO.metals.get(index).name, 5+(width / 24)*x+5*x, Utilities.getScreenSize().height / 2+(Utilities.getScreenSize().height / 12)+15);
            renderer.batch.end();
            index++;

            renderer.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        }
    }

    public void parseInput(){

    }
}
