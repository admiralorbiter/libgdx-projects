package com.mygdx.game.NPCs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Character;
import com.mygdx.game.Entity;
import com.mygdx.game.Inventory;
import com.mygdx.game.items.Item;

import java.util.ArrayList;
import java.util.List;

public class NPC extends Character {

    List<String> dialog = new ArrayList();

    public NPC(int x, int y) {
        super(x, y);
        MAX_VELOCITY=35f;
        ACCEL=75f;
        color = Color.CHARTREUSE;
        type="NPC";
        dialog.add("Hello");
        dialog.add("I am the first npc");
        dialog.add("This is a test and I will be gone by the time we get to production");
    }

    public void update(float deltaTime, List<Entity> entityList){
        for(Item item : inventory.itemList)
            item.effect(this);
    }

    public List<String> getDialog() {
        return dialog;
    }
}
