package com.thehound

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector3
import java.awt.Point

class Bullet(var startingPoint: Vector2, var mousePoint: Vector2) {

    var position = Vector2(startingPoint);
    var WIDTH = 3;
    var HEIGHT = 3;

    init {
        System.out.println("Initialization");
        System.out.println("P: "+position);
        System.out.println("MP: "+mousePoint);
        System.out.println("Dist: "+position.dst(mousePoint));
    }

    fun update(): Boolean{
        //System.out.println("P: "+position);
        //System.out.println("MP: "+mousePoint);
       //System.out.println("Dist: "+position.dst(mousePoint));
        if(position.dst(mousePoint)>1){
            //var temp = Vector2(mousePoint.sub(position)).scl(.01F);
            //position=Vector2(position.add(temp));
            //position=Vector2(temp);

            //var y = ((mousePoint.y-startingPoint.y)/(mousePoint.x-startingPoint.y))*(position.x-mousePoint.x+.1)+mousePoint.y;
            //position = Vector2(mousePoint.x+.1F, y.toFloat());

            var v = Vector2(mousePoint.x-position.x, mousePoint.y-position.y);
            v.nor();
            position = Vector2(position.x+v.x, position.y+v.y);
            return false;
        }
        return true;
    }

    fun render(shapeRenderer: ShapeRenderer) {
        shapeRenderer.color = Color.WHITE;
        shapeRenderer.rect(position.x, position.y, WIDTH.toFloat(), HEIGHT.toFloat())

        //shapeRenderer.color = Color.GREEN;
        //shapeRenderer.rect(startingPoint.x, startingPoint.y, WIDTH.toFloat(), HEIGHT.toFloat())
        //shapeRenderer.rect(mousePoint.x, mousePoint.y, WIDTH.toFloat(), HEIGHT.toFloat())
    }

    fun convertCoordinates(camera: OrthographicCamera){
        var temp = Vector3(mousePoint.x, mousePoint.y, 0F);
        camera.unproject(temp);
        mousePoint=Vector2(temp.x, temp.y);
    }
}