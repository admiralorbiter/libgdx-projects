package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import java.awt.*;

public final class Utilities {

    private static int worldsize=100;
    private static int entitysize=4;

    public static Dimension getWorldSizeCompact(){return new Dimension(worldsize, worldsize);}
    public static Dimension getWorldSize(){return new Dimension(worldsize*entitysize, worldsize*entitysize);}
    public static Dimension getScreenSize() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

        if(size!=null)
            return size;
        else
            return new Dimension(1800, 900);
    }
    public static Rectangle getWorldBounds(){return new Rectangle(0, 0, worldsize*entitysize, worldsize*entitysize);}
    public static Dimension getDefaultEntitySize(){
        return new Dimension(entitysize, entitysize);
    }

    //Returns compact coordinates for where the mouse is
    public static Point calcMouseCoords(Camera camera){
        Vector3 coord =camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

        return new Point((int)(coord.x/Utilities.getDefaultEntitySize().width)*Utilities.getDefaultEntitySize().width,
                (int)(coord.y/Utilities.getDefaultEntitySize().height)*Utilities.getDefaultEntitySize().height);
    }
}
