package org.ensea.student.java;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import org.ensea.student.core.Game;

public class GameDesktop {
	public static void main (String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 570;
		config.width = 380;
		new LwjglApplication(new Game(), config);
	}
}
