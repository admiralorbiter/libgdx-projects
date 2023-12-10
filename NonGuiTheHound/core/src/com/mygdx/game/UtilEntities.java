package com.mygdx.game;

import com.mygdx.game.entities.Entity;

public final class UtilEntities {

    public static boolean isEntityDrawable(Entity entity){
        return entity!=null && entity.draw && entity.hasBeenRendered==false;
    }

    public static boolean isEntityRepresented(Entity entity){
        return entity!=null && entity.represntation!=null && entity.hasBeenRendered==false;
    }

}
