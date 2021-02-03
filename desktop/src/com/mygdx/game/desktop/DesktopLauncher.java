package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Pong;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Pong.DESKTOP_START_WIDTH;
		config.height = Pong.DESKTOP_START_HEIGHT;
		config.title = Pong.TITLE;
		config.forceExit = true;
		new LwjglApplication(new Pong(), config);
	}
}
