package com.mygdx.game.Dialogue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.RenderDAO;
import com.mygdx.game.Utilities;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DialogGUI {

    public float width = Utilities.getDefaultEntitySize().width;
    public float height = Utilities.getDefaultEntitySize().height;
    private List<String> dialog = new ArrayList<>();
    private int index=0;

    public DialogGUI(String dialog){
        this.dialog.add(dialog);
        addEscapeCharacter();
    }

    public DialogGUI(List<String> dialog){
        this.dialog=dialog;
        addEscapeCharacter();
    }

    public void add(String dialog){
        this.dialog.add(dialog);
    }

    public void update(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            index++;
            if(index==dialog.size())
                index=0;
        }
    }

    public void render(RenderDAO renderer, Dimension viewDimensions){

        int x = 50;
        //int x = Utilities.getScreenSize().width/2-viewDimensions.width*Utilities.getDefaultEntitySize().width;
        int width=Utilities.getScreenSize().width-100;
        //int width = viewDimensions.height*Utilities.getDefaultEntitySize().height*2;

        ShapeRenderer shapeRenderer = renderer.shapeRenderer;
        SpriteBatch batch = renderer.batch;

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        //Draw white background for dialog box then the character portraits
        shapeRenderer.setColor(Color.WHITE);

        //dialogbox
        shapeRenderer.rect(x, 0, width, (Utilities.getScreenSize().height-viewDimensions.height*Utilities.getDefaultEntitySize().height*2)/2);


        //left character
        //shapeRenderer.rect(leftBoxX, 0, charBoxSize, charBoxSize);
        //right character
        //shapeRenderer.rect(width*.75f, 0, charBoxSize, charBoxSize);

        //Draw black background for character potraits
        shapeRenderer.setColor(Color.BLACK);
        //dialog
        shapeRenderer.rect(10+x, 10, width-20, (Utilities.getScreenSize().height-viewDimensions.height*Utilities.getDefaultEntitySize().height*2)/2-20);

        //left character
        //shapeRenderer.rect(leftBoxX+charBoxSize*.05f, height*.01f, charBoxSize*.9f, charBoxSize*.9f);
        //right character
        //shapeRenderer.rect(width*.75f+charBoxSize*.05f, height*.01f, charBoxSize*.9f, charBoxSize*.9f);
        shapeRenderer.end();

        batch.begin();
        renderer.font.setColor(Color.WHITE);
        renderer.setFontsize(16);
        renderer.font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        renderer.font.draw(batch, " "+dialog.get(index), 25+x, (Utilities.getScreenSize().height-viewDimensions.height*Utilities.getDefaultEntitySize().height*2)/2-40);
        /*
        if(characterImage1!=null)
            batch.draw(characterImage1, leftBoxX+charBoxSize*.05f, height*.01f, charBoxSize*.9f, charBoxSize*.9f);

        if(characterImage2!=null)
            batch.draw(characterImage2, width*.75f+charBoxSize*.05f, height*.01f, charBoxSize*.9f, charBoxSize*.9f);
        */
        batch.end();
    }

    private void addEscapeCharacter(){
        int stringLength=190;

        if(dialog.get(index).length()>stringLength){
            int testLength=stringLength;
            while(dialog.get(index).length()>testLength) {
                int cutOffLenght=dialog.get(index).lastIndexOf(" ", testLength);
                String newString = dialog.get(index).substring(0, cutOffLenght) + '\n' + dialog.get(index).substring(cutOffLenght);
                dialog.remove(index);
                dialog.add(index, newString);
                testLength=testLength+stringLength;
            }
        }
    }
}
