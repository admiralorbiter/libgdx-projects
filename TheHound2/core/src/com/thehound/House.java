package com.thehound;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class House extends Entity {
	
	private float r=0;
	private float g=0;
	private float b=0;
	
	private Door door;
	
	public House(int x, int y) {
		super(x, y);
		WIDTH=64;
		HEIGHT=64;
		door = new Door((int)position.x+1, (int)position.y);
		type = "house";
		bounds = new Rectangle(position.x, position.y, WIDTH, HEIGHT);
	}

	@Override
	public void render(ShapeRenderer shapeRenderer) {

		r+=.05;
		g+=.03;
		b+=.07;
		if(r>1)
			r=0;
		if(g>1)
			g=0;
		if(b>1)
			b=0;
    	shapeRenderer.setColor(r, g, b, 0);
		//shapeRenderer.setC
    	shapeRenderer.rect(position.x, position.y, WIDTH, HEIGHT);
    	
    	door.render(shapeRenderer);
	}
	
	public List<Entity> load() {
		List<Entity> entityList  = new ArrayList<Entity>();
		
		return entityList;
	}
	
	//Getters and Setters
	public Door getDoor() {return door;}
}
