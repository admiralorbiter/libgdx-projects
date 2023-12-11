package com.mygdx.game;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class ActionManager {

    List<Entity> treesToChop = new ArrayList<>();

    public ActionManager(){

    }

    public void addAction(@NotNull Actions action, Entity entity){
        switch (action){
            case CHOP:
                treesToChop.add(entity);
        }
    }

    public List<Entity> getChopList(){return treesToChop;}

}
