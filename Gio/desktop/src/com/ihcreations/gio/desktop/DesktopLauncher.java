package com.ihcreations.gio.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ihcreations.gio.Gio;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		setConfig(config);
		new LwjglApplication(new Gio(), config);
	}
	
	// Set Window Configurations
	public static void setConfig(LwjglApplicationConfiguration config) {
		config.width = 1024;
		config.height = 768;
		config.resizable = false;
		config.title = "Gio";
	}
}

