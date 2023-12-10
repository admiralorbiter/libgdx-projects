package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class UIRenderer {

    GameData gamedata;

    OrthographicCamera camera;
    SpriteBatch batch;
    ShapeRenderer shapeRenderer;
    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    BitmapFont font;

    private int headerPadding=25;

    public UIRenderer(GameData gamedata){
        this.gamedata=gamedata;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 400, 400);
        camera.update();

        generator = new FreeTypeFontGenerator(Gdx.files.internal("roboto.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 36;
        font = generator.generateFont(parameter);

        batch = new SpriteBatch(5460);
        shapeRenderer = new ShapeRenderer();
    }

    public void render(){
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        batch.begin();
        //Title
        parameter.size = 36;
        font = generator.generateFont(parameter);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.draw(batch, "The MU Puzzle         Goal: Start with 'MI' and transform until you obtain 'MU'", 0, Utilities.getScreenSize().height-headerPadding);

        //The rules
        font.draw(batch, "The Rules", Utilities.getScreenSize().width*6/10, Utilities.getScreenSize().height-headerPadding*2);
        parameter.size = 32;
        font = generator.generateFont(parameter);
        font.draw(batch, "1. Add a 'u' to the end of a string ending in 'i'", Utilities.getScreenSize().width*6/10, Utilities.getScreenSize().height-headerPadding*3);
        font.draw(batch, "2. Double the string after the first 'm'", Utilities.getScreenSize().width*6/10, Utilities.getScreenSize().height-headerPadding*4);
        font.draw(batch, "3. Replace any single 'iii' with 'u'", Utilities.getScreenSize().width*6/10, Utilities.getScreenSize().height-headerPadding*5);
        font.draw(batch, "4. Remove any single 'uu'", Utilities.getScreenSize().width*6/10, Utilities.getScreenSize().height-headerPadding*6);

        //String
        parameter.size = 16;
        font = generator.generateFont(parameter);
        font.draw(batch, gamedata.getString(), 0, Utilities.getScreenSize().height-headerPadding*7);

        batch.end();
        shapeRenderer.end();
    }
}
