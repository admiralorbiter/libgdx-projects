package com.thehound

import com.badlogic.gdx.math.Vector2
import java.awt.Point
import java.util.concurrent.TimeUnit

class Gun(var damage: Int, var bulletCount: Int, var rate: Double, var position: Vector2?){
    constructor(damage: Int, bullets: Int, rate: Double): this(damage, bullets, rate, null);

    var lastShotTime: Long=System.currentTimeMillis();

    fun fire(startingPoint: Vector2, mousePoint: Vector2): Bullet? {
        if(bulletCount>0){
            System.out.println(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()-lastShotTime));
            if(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()-lastShotTime)>rate){
                lastShotTime=System.currentTimeMillis();
                bulletCount-=1;
                return Bullet(startingPoint, mousePoint);
            }
        }

        return null;
    }
}