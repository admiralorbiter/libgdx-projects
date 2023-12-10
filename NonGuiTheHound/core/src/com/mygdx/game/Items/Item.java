package com.mygdx.game.Items;

import com.mygdx.game.entities.Entity;

public abstract class Item extends Entity {

    private String name=null;
    private boolean pickupable=true;

    public void setName(String name){this.name=name;}
    public String getName(){return name;}
    public void setPickupFlag(boolean flag){this.pickupable=flag;}
    public boolean getPickupFlag(){return pickupable;}
}
