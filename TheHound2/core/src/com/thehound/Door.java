package com.thehound;

import com.badlogic.gdx.graphics.Color;

public class Door extends Entity{
	
	private boolean open=true;
	
	public Door(int x, int y) {
		super(x, y, 8, 4);
		color=Color.BLACK;
	}
	
	public boolean getDoorOpen() {return open;}
}
