package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class RenderDAO {
    public SpriteBatch batch;
    public BitmapFont font;
    public FreeTypeFontGenerator generator;
    public FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    public ShapeRenderer shapeRenderer;

    public RenderDAO(){
        create();
    }

    public void basicBatchBegin(){
        batch.begin();
        setFontsize(12);
        font.setColor(Color.WHITE);
    }

    public void create(){
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        generator = new FreeTypeFontGenerator(Gdx.files.internal("roboto.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 12;
        font = generator.generateFont(parameter);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    public void setProjectMatrix(OrthographicCamera camera){
        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    public void setFontsize(int size){
        parameter.size = size;
        font = generator.generateFont(parameter);
        //System.out.println("Font Size is now: "+parameter.size);
    }

    public void dispose(){
        batch.dispose();
        font.dispose();
        generator.dispose();
        shapeRenderer.dispose();
    }
}
