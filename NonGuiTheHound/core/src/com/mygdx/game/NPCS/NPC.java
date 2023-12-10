package com.mygdx.game.NPCS;

import com.mygdx.game.Dialogue.DialogGUI;
import com.mygdx.game.entities.Entity;

public abstract class NPC extends Entity {
    public DialogGUI dialogGUI = null;
    public String name;

    public NPC(String name, int x, int y, Entity[][] entityList){
        super("X", x, y, entityList);
        this.name=name;
        setDialog();
    }

    public abstract void setDialog();
    public DialogGUI getDialogGUI(){return dialogGUI;}
}
