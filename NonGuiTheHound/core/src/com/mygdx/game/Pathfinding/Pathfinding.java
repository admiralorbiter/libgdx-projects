package com.mygdx.game.Pathfinding;

import com.mygdx.game.Utilities;
import com.mygdx.game.UtilitiesRenderer;
import com.mygdx.game.Worlds.WorldDAO;
import com.mygdx.game.entities.Entity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public final class Pathfinding {

    public static List<Node> findPath(Point start, Point end, List<Point> blockedPoints){
        Node initalNode = new Node(start);
        Node finalNode = new Node(end);
        int rows = Utilities.getScreenSize().width;
        int cols = Utilities.getDefaultEntitySize().height;

        AStar aStar = new AStar(rows, cols, initalNode, finalNode);
        aStar.setBlocks(blockedPoints);

        return aStar.findPath();
    }

    public static List<Point> createBlockedPointList(WorldDAO worldDAO){
        List<Point> blockedList = new ArrayList<>();
        Entity[][] entities = worldDAO.entityList;

        Point start = UtilitiesRenderer.calculateStartPoint(worldDAO);
        Point end = UtilitiesRenderer.calculateEndPoint(worldDAO);

        for(int x=start.x; x<end.x; x++){
            for(int y=start.y; y<end.y; y++){
                if(entities[x][y]!=null)
                    blockedList.add(new Point(x, y));
            }
        }

        return blockedList;
    }



}
