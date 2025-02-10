package org.ensea.student.core;

import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import org.ensea.student.core.displayable.DynamicSprite;
import org.ensea.student.core.displayable.SimpleTilemap;
import org.ensea.student.core.displayable.SolidSprite;
import org.ensea.student.core.engine.PhysicsEngine;

public class Game implements ApplicationListener {

	SimpleTilemap tm;
	SolidSprite ss;
	DynamicSprite ds;

	public static PhysicsEngine pe;
	float elapsed;

	@Override
	public void create () {
		tm = new SimpleTilemap(10,10,"grass.png");
		Texture ssTexture = new Texture(Gdx.files.internal("rock.png"));
		ss = new SolidSprite(ssTexture,5*ssTexture.getWidth(),5*ssTexture.getHeight());
		ds = new DynamicSprite("heroTileSheetLowRes.png",1,1);
		pe = new PhysicsEngine(ss);
	}

	@Override
	public void resize (int width, int height) {
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		tm.draw();
		ss.draw();
		ds.draw();
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
