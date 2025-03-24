package org.ensea.student.core;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import org.apache.commons.io.FileUtils;
import org.ensea.student.core.displayable.level.BasicLevel;
import org.ensea.student.core.displayable.ui.ItemButton;
import org.ensea.student.core.engine.RenderEngine;
import org.ensea.student.core.inventory.Inventory;
import org.ensea.student.core.inventory.SerializedItemDB;
import org.ensea.student.core.util.Constants;
import java.io.IOException;

public class Game implements ApplicationListener {

	RenderEngine re;
	@Override
	public void create () {
		//Init the JSON string
		try {
			Constants.ITEMS_JSON_STRING = FileUtils.readFileToString((Gdx.files.internal("assets/items.json").file()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		//Deserialize all the items into memory
		SerializedItemDB sidb = SerializedItemDB.getInstance();
		Inventory inv = Inventory.getInstance();
		inv.addItemToInventory(sidb.getItemDB().get(0));
		inv.addItemToInventory(sidb.getItemDB().get(1));

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
