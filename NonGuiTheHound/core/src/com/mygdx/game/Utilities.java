package com.mygdx.game;

import com.badlogic.gdx.Input;

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
        return new Dimension(20, 20);
    }

    public static Rectangle getViewWindowBounds(Dimension viewDimensions){
       return new Rectangle(Utilities.getScreenSize().width/2-viewDimensions.width*Utilities.getDefaultEntitySize().width, Utilities.getScreenSize().height/2-viewDimensions.height*Utilities.getDefaultEntitySize().height, viewDimensions.width*Utilities.getDefaultEntitySize().width*2, viewDimensions.height*Utilities.getDefaultEntitySize().height*2);
    }

    public static Point getMouseCoordinates(Input input, Dimension viewDimensions){
        int sideX = (Utilities.getScreenSize().width-viewDimensions.width*Utilities.getDefaultEntitySize().width*2)/2;
        int sideY = (Utilities.getScreenSize().height-viewDimensions.height*Utilities.getDefaultEntitySize().height*2)/2;

        if(input.getX()>=sideX && input.getX()<=sideX+viewDimensions.width*Utilities.getDefaultEntitySize().width*2){
            if(input.getY()>=sideY && input.getY()<=sideY+viewDimensions.height*Utilities.getDefaultEntitySize().height*2){
                int x=(input.getX()-sideX)/Utilities.getDefaultEntitySize().width;
                int y=viewDimensions.height*2-((input.getY()-sideY))/Utilities.getDefaultEntitySize().height-1;
                return new Point(x, y);
            }
        }

        return null;
    }

    public static Point getMouseCoordinates(Input input, MapEditorRenderer renderer){
        /*
        int x = input.getX()/Utilities.getDefaultEntitySize().width;
        int y = (Utilities.getScreenSize().height-input.getY())/Utilities.getDefaultEntitySize().height;
        return new Point(x, y);*/
        int sideX = (Utilities.getScreenSize().width-renderer.viewDimensions.width*Utilities.getDefaultEntitySize().width*2)/2;
        int sideY = (Utilities.getScreenSize().height-renderer.viewDimensions.height*Utilities.getDefaultEntitySize().height*2)/2;

        if(input.getX()>=sideX && input.getX()<=sideX+renderer.viewDimensions.width*Utilities.getDefaultEntitySize().width*2){
            if(input.getY()>=sideY && input.getY()<=sideY+renderer.viewDimensions.height*Utilities.getDefaultEntitySize().height*2){
                int x=(int)(((input.getX()-sideX)/Utilities.getDefaultEntitySize().width)+renderer.position.x);
                int y=(int)(renderer.viewDimensions.height*2-((input.getY()-sideY))/Utilities.getDefaultEntitySize().height-1+renderer.position.y);
                return new Point(x, y);
            }
        }

        return null;
    }

    public static double distanceBetweenTwoPoints(Point p1, Point p2){
        return Point.distance(p1.x, p1.y, p2.x, p2.y);
    }

    public static Point getEntityXY(int x, int y, Dimension view){
        int newX=Utilities.getScreenSize().width/2+x*Utilities.getDefaultEntitySize().width-view.width*Utilities.getDefaultEntitySize().width;
        int newY= Utilities.getScreenSize().height/2+y*Utilities.getDefaultEntitySize().height-view.height*Utilities.getDefaultEntitySize().height;
        return new Point(newX, newY);
    }
}
