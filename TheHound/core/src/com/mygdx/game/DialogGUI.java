package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class DialogGUI {

    float width=Utilities.getScreenSize().width;;
    float height=Utilities.getScreenSize().height;;
    public FreeTypeFontGenerator generator= new FreeTypeFontGenerator(Gdx.files.internal("roboto.ttf"));
    public FreeTypeFontGenerator.FreeTypeFontParameter parameter= new FreeTypeFontGenerator.FreeTypeFontParameter();
    //private String dialog;
    private List<String> dialog = new ArrayList<>();
    private int index=0;

    SpriteBatch batch = new SpriteBatch();
    ShapeRenderer shapeRenderer = new ShapeRenderer();

    private Texture characterImage1=null;
    private Texture characterImage2=null;

    public DialogGUI(String dialog, Camera camera, Texture characterImage1, Texture characterImage2){
        this.dialog.add(dialog);
        System.out.println(dialog.length());
        //shapeRenderer.setProjectionMatrix(camera.combined);
        addEscapeCharacter();
        this.characterImage1=characterImage1;
        this.characterImage2=characterImage2;
    }

    public DialogGUI(List<String> dialog, Camera camera, Texture characterImage1, Texture characterImage2){
        this.dialog=dialog;
        //shapeRenderer.setProjectionMatrix(camera.combined);
        for(index=0; index<dialog.size(); index++)
            addEscapeCharacter();
        index=0;
        this.characterImage1=characterImage1;
        this.characterImage2=characterImage2;
    }

    public void render(Camera camera, BitmapFont font){

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        //Draw white background for dialog box then the character portraits
        shapeRenderer.setColor(Color.WHITE);
        float leftBoxX=width*.1f;
        float charBoxSize=.15f*(width);
        //dialogbox
        shapeRenderer.rect(leftBoxX+charBoxSize, height*.01f, width*.5f, height*.15f);
        //left character
        shapeRenderer.rect(leftBoxX, 0, charBoxSize, charBoxSize);
        //right character
        shapeRenderer.rect(width*.75f, 0, charBoxSize, charBoxSize);

        //Draw black background for character potraits
        shapeRenderer.setColor(Color.BLACK);
        //dialog
        shapeRenderer.rect(leftBoxX+charBoxSize+(width*.3f)*.05f, height*.01f*2, (width*.5f)*.95f, height*.15f*.85f);
        //left character
        shapeRenderer.rect(leftBoxX+charBoxSize*.05f, height*.01f, charBoxSize*.9f, charBoxSize*.9f);
        //right character
        shapeRenderer.rect(width*.75f+charBoxSize*.05f, height*.01f, charBoxSize*.9f, charBoxSize*.9f);
        shapeRenderer.end();

        batch.begin();
        font.setColor(Color.WHITE);
        parameter.size = 16;
        font = generator.generateFont(parameter);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.draw(batch, " "+dialog.get(index), leftBoxX+charBoxSize+(width*.3f)*.05f, height*.01f*2+height*.15f*.80f);

        if(characterImage1!=null)
            batch.draw(characterImage1, leftBoxX+charBoxSize*.05f, height*.01f, charBoxSize*.9f, charBoxSize*.9f);

        if(characterImage2!=null)
            batch.draw(characterImage2, width*.75f+charBoxSize*.05f, height*.01f, charBoxSize*.9f, charBoxSize*.9f);

        batch.end();

    }

    private void addEscapeCharacter(){
        if(dialog.get(index).length()>101){
            int testLength=101;
            while(dialog.get(index).length()>testLength) {
                int cutOffLenght=dialog.get(index).lastIndexOf(" ", testLength);
                String newString = dialog.get(index).substring(0, cutOffLenght) + '\n' + dialog.get(index).substring(cutOffLenght);
                dialog.remove(index);
                dialog.add(index, newString);
                testLength=testLength+101;
            }
        }
    }
}
