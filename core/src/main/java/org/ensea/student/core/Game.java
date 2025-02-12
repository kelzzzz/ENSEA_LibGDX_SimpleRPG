package org.ensea.student.core;

import com.badlogic.gdx.ApplicationListener;
import org.ensea.student.core.displayable.level.BasicLevel;
import org.ensea.student.core.engine.RenderEngine;

public class Game implements ApplicationListener {

	RenderEngine re;
	@Override
	public void create () {
		re = new RenderEngine(100,100,new BasicLevel());
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
