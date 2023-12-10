package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.items.Item;

public abstract class Character extends  Entity{
    protected float MAX_VELOCITY;
    protected float ACCEL;
    protected Vector2 velocity = new Vector2();;
    protected Vector2 accel = new Vector2();;

    protected Inventory inventory = new Inventory();

    public Character(int x, int y) {
        super(x, y);
    }

    public void setMAX_VELOCITY(float max_velocity){this.MAX_VELOCITY=max_velocity;}
    public void setACCEL(float accel){this.ACCEL=accel;}
    public Vector2 getCurrentAccel(){return accel;}
    public Inventory getInventory(){return inventory;}
}
