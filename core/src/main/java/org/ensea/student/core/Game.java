package org.ensea.student.core;

import com.badlogic.gdx.ApplicationListener;
import org.ensea.student.core.engine.RenderEngine;

public class Game implements ApplicationListener {

	RenderEngine re;
	@Override
	public void create () {
		re = new RenderEngine();
	}

	@Override
	public void resize (int width, int height) {
	}

	@Override
	public void render () {
		re.update();
	}

	@Override
	public void pause () {
	}

	@Override
	public void resume () {
	}

	@Override
	public void dispose () {
	}
}
