package com.thehound;

import java.awt.Point;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Entity {
	
	public Entity(int x, int y) {
		position.set(x, y);
		WIDTH=4;
		HEIGHT=4;
		bounds = new Rectangle(position.x, position.y, WIDTH, HEIGHT);
	}
	
	public Entity(int x, int y, int WIDTH, int HEIGHT) {
		position.set(x, y);
		this.WIDTH=WIDTH;
		this.HEIGHT=HEIGHT;
		bounds = new Rectangle(position.x, position.y, WIDTH, HEIGHT);
	}
	
	public int WIDTH;
    public int HEIGHT;
    
    public Vector2 position = new Vector2();
    //public Rectangle bounds = new Rectangle(0 - WIDTH/2, 0 - HEIGHT/2, WIDTH, HEIGHT);
    public Rectangle bounds = null;

    public Color color = Color.WHITE;
    public String type = "Unknown";
    
    public void render(ShapeRenderer shapeRenderer) {
    	shapeRenderer.setColor(color);
    	shapeRenderer.rect(position.x, position.y, WIDTH, HEIGHT);
    }
}
