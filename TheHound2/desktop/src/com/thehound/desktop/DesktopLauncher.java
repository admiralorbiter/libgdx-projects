package com.thehound.desktop;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.thehound.TheHound;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title="The Hound";
		config.width=getScreensize().width;
		config.height=getScreensize().height;
		new LwjglApplication(new TheHound(), config);
	}
	
	public static Dimension getScreensize() {
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		
		if(size!=null)
			return size;
		else
			return new Dimension(1800, 900);
	}
}
