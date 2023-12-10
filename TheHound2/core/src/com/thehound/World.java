package com.thehound;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;

public class World {

	TheHound game;
	OrthographicCamera camera;
	Player player;
	House house;
	
	Screen nextScreen=null;
	
	public List<Entity> entityList = new ArrayList<Entity>();

	public List<Bullet> bulletList = new ArrayList<>();
	
	public World(TheHound game) {

		player = new Player (90, 80);
        house = new House(90, 90);
        entityList.add(house);
	}
	
	public void update(float delta) {
		player.update(delta, this);
	}
	
	public void setScreen(Screen screen) {
		nextScreen=screen;
	}

	public void createBullet(Bullet bullet){
		if(bullet!=null) {
			bulletList.add(bullet);
			bulletList.get(bulletList.size()-1).convertCoordinates(camera);
		}
	}

	public void setCamera(OrthographicCamera camera){
		this.camera=camera;
	}
}
