package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.awt.*;

public final class Utilities {
    public static Dimension getScreenSize() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

        if(size!=null)
            return size;
        else
            return new Dimension(1800, 900);
    }

    public static Dimension getDefaultEntitySize(){
        return new Dimension(4, 4);
    }

    public static Point getWorldCoordinate(int x, int y, Camera camera){
        Vector3 vec = camera.unproject(new Vector3(x, y, 0));
        //camera.project(new Vector3(x, y, 0));
        return new Point((int)vec.x, (int)vec.y);
    }

    public static Boolean checkWorldEntityContainsCursor(Rectangle bounds, Camera camera){
        Vector3 coord =camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        return bounds.contains(coord.x, coord.y);
    }
}
