package com.mygdx.game.Worlds;

import com.mygdx.game.NPCS.Hoid;
import com.mygdx.game.Player;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.House;

import java.util.ArrayList;
import java.util.Random;
import java.awt.*;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;

public class World1 extends WorldDAO {

    @Override
    public void load() {
        worldDimension = new Point(100, 100);
        entityList=new Entity[(int) worldDimension.x][(int) worldDimension.y];
        //Entity test = new Entity("T", 7, 7, entityList);
        Hoid npc = new Hoid("Hoid", 7, 7, entityList);
        //entityList[(int)test.position.x][(int)test.position.y]=test;
        House house = new House(10, 10, entityList);
        //entityList[(int)house.position.x][(int)house.position.y]=house;
        player = new Player(5, 5, entityList);
        //entityList
        viewDimensions = new Dimension(15, 15);
    }

    public void generateRandom(){
        worldDimension = new Point(100, 100);
        entityList=new Entity[(int) worldDimension.x][(int) worldDimension.y];
        Random random = new Random();
        int dx = random.nextInt(2);
        int dy = random.nextInt(2);
        ArrayList<Point> pointList = new ArrayList<>();
        player = new Player(5+50*dx, 5+50*dy, entityList);
        for(int i=0; i<5; i++){
            Point p = null;
            do{
                int x=random.nextInt(10);
                int y=random.nextInt(10);
                p = new Point(x*5+dx*50, y*5+dy*50);
            }
            while(pointList.contains(p));
            pointList.add(p);
            House house = new House(p.x, p.y, entityList);
        }
        viewDimensions = new Dimension(15, 15);
    }
}
