package com.mygdx.game.NPCS;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Dialogue.DialogGUI;
import com.mygdx.game.Pathfinding.Pathfinding;
import com.mygdx.game.Worlds.WorldDAO;
import com.mygdx.game.entities.Entity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Hoid extends NPC {


    public Hoid(String name, int x, int y, Entity[][] entityList) {
        super(name, x, y, entityList);
        //System.out.println(represntation);
        //represntation="X";
    }

    @Override
    public void setDialog() {
        List<String> dialog = new ArrayList<>();
        dialog.add("Hello. Welcome to the game. Press space to continue dialog...");
        dialog.add("Press escape to end dialog...");
        dialog.add("Your actions in the world may change what NPCs have to say.");
        dialogGUI = new DialogGUI(dialog);

    }

    @Override
    public void update(WorldDAO worldDAO, int turn){
        //List<Point> blockedList = Pathfinding.createBlockedPointList(worldDAO);
        //blockedList.remove(getPosPoint());

        if(turn%50==0) {
            movement(worldDAO);
        }

        if(turn%250==0 && destination==null){
            if(getPosPoint().equals(new Point(7, 7))){
                destination=new Point(15, 4);
            }else{
                destination=new Point(7, 7);
            }
        }
    }
}

