package com.thehound;

import com.badlogic.gdx.math.Vector2;

public final class Assests {

	static Entity test;
	static Entity test2;
	
	public static void load() {
		test = new Entity(0, 0);
		test2 = new Entity(1000, 1000);
	}
	
}
