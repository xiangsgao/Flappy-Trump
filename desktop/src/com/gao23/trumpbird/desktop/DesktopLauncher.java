package com.gao23.trumpbird.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gao23.trumpbird.TrumpBirdMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = TrumpBirdMain.WIDTH;
		config.height = TrumpBirdMain.HEIGHT;
		config.title = TrumpBirdMain.TITLE;
		new LwjglApplication(new TrumpBirdMain(), config);
	}
}
