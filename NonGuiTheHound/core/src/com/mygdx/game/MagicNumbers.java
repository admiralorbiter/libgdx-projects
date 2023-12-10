package com.mygdx.game;

import com.mygdx.game.Utilities;

import java.awt.*;

public final class MagicNumbers {
    public static int metalGUIwidth(Dimension viewDimensions){
        return Utilities.getScreenSize().width-viewDimensions.width*Utilities.getDefaultEntitySize().width / 24;
    }

    public static int metalGUIheight = Utilities.getScreenSize().height / 12;
}
