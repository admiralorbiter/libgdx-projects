package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Application;
import com.mygdx.game.Utilities;

import java.awt.*;


public class SelectScreen implements Screen {

    Application game;
    Rectangle easyModeButton;
    Rectangle hardModeButton;

    public SelectScreen(Application game){
        this.game=game;
        easyModeButton = new Rectangle(Utilities.getScreenSize().width/2-25, Utilities.getScreenSize().height*4/6-60, 350, 60+25);
        hardModeButton = new Rectangle(Utilities.getScreenSize().width/2-25, Utilities.getScreenSize().height*3/6-60, 350, 60+25);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        game.shapeRenderer.setColor(Color.WHITE);
        game.shapeRenderer.rect(easyModeButton.x, easyModeButton.y, easyModeButton.width, easyModeButton.height);
        game.shapeRenderer.rect(hardModeButton.x, hardModeButton.y, hardModeButton.width, hardModeButton.height);
        game.shapeRenderer.end();

        game.batch.begin();
        game.parameter.size = 60;
        game.font = game.generator.generateFont(game.parameter);
        game.font.setColor(Color.WHITE);
        game.font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        game.font.draw(game.batch, "Hush", Utilities.getScreenSize().width/2, Utilities.getScreenSize().height*5/6);
        game.font.setColor(Color.BLACK);
        game.font.draw(game.batch, "Easy Mode", Utilities.getScreenSize().width/2, Utilities.getScreenSize().height*4/6);
        game.font.draw(game.batch, "Hard Mode", Utilities.getScreenSize().width/2, Utilities.getScreenSize().height*3/6);
        game.batch.end();

        parseInput(Gdx.input);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public void parseInput(Input input){
        if(input.isButtonPressed(Input.Buttons.LEFT)){
            System.out.println(input.getX()+", "+input.getY());
            System.out.println(easyModeButton);
            if(easyModeButton.contains(new Point(input.getX(), Utilities.getScreenSize().height-input.getY()))){
                System.out.println("Easy Button Clicked");
                game.setScreen(game.introAreaScreen);
            }else if(hardModeButton.contains(new Point(input.getX(), input.getY()))){
                System.out.println("Hard Button Clicked");
                game.setScreen(game.introAreaScreen);
            }
        }
    }
}
