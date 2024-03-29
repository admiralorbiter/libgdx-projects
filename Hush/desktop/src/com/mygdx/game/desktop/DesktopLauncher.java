package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Application;
import com.mygdx.game.Utilities;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title="Hush";
		config.width= Utilities.getScreenSize().width;
		config.height=Utilities.getScreenSize().height;
		new LwjglApplication(new Application(), config);
	}
}
