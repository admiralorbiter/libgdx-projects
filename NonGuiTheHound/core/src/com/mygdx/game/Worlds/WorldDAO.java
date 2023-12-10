package com.mygdx.game.Worlds;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Player;
import com.mygdx.game.entities.Entity;

import java.awt.*;

public abstract class WorldDAO {
    public Point worldDimension = null;
    public Entity[][] entityList = null;
    public Player player = null;
    public Dimension viewDimensions =null;

    public abstract void load();
    public abstract void generateRandom();
}
