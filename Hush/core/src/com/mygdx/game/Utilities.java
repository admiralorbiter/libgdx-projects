package com.mygdx.game;

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
}
