package org.ensea.student.core.displayable.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.ensea.student.core.displayable.Displayable;

public class InventoryUI implements Displayable {
    Boolean open = false;
    ShapeRenderer shape = new ShapeRenderer();

    public boolean isOpen(){
        return open;
    }

    @Override
    public void draw() {
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.WHITE);
        shape.rect(100,200,190,220);
        shape.end();
    }

    public void open() {
        if (Gdx.input.isKeyPressed(Input.Keys.I)) {
            open = true;
        }
        if (open) {
            draw();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE) && open) {
            this.hide();
        }
    }

    public void hide(){
        this.open = false;
    }
}
