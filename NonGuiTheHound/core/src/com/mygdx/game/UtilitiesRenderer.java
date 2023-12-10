package com.mygdx.game;

import com.mygdx.game.Worlds.WorldDAO;

import java.awt.*;

public final class UtilitiesRenderer {
    //Calculates correct start point for rendering the 2d tiles
    public static Point calculateStartPoint(WorldDAO worldDAO){
        Player player = worldDAO.player;
        Dimension viewDimensions = worldDAO.viewDimensions;
        Point start = new Point(0, 0);

        if(player.position.x-viewDimensions.width>0)
            start.x=(int)(player.position.x-viewDimensions.width);
        if(player.position.y-viewDimensions.height>0)
            start.y=(int)(player.position.y-viewDimensions.height);

        return start;
    }

    //Calculates correct end point for rendering the 2d tiles
    public static Point calculateEndPoint(WorldDAO worldDAO){
        Player player = worldDAO.player;
        Dimension viewDimensions = worldDAO.viewDimensions;
        Point worldDimension = worldDAO.worldDimension;
        Point end = new Point(worldDimension.x-1, worldDimension.y-1);

        if(player.position.x+viewDimensions.width<end.x)
            end.x=(int)(player.position.x+viewDimensions.width);
        if(player.position.y+viewDimensions.height<end.y)
            end.y=(int)(player.position.y+viewDimensions.height);

        return end;
    }
}
