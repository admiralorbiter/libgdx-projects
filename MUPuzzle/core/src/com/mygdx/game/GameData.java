package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class GameData {

    private String string = "MU";

    public GameData(){

    }

    private void processKeys() {
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            Gdx.app.exit();
        }

        if(Gdx.input.isKeyPressed(Input.Keys.NUM_1)){

        }

        if(Gdx.input.isKeyPressed(Input.Keys.NUM_2)){

        }

        if(Gdx.input.isKeyPressed(Input.Keys.NUM_3)){

        }

        if(Gdx.input.isKeyPressed(Input.Keys.NUM_4)){

        }
    }

    public String getString(){return string;}
    public void setString(String string){this.string=string;}
}
