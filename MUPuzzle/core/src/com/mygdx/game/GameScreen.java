package com.mygdx.game;

import com.badlogic.gdx.Screen;

public class GameScreen implements Screen {

    MUPuzzle game;
    UIRenderer ui;
    GameData gamedata;

    public GameScreen(MUPuzzle game){
        this.game=game;
        gamedata = new GameData();
        ui = new UIRenderer(gamedata);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ui.render();
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
}
