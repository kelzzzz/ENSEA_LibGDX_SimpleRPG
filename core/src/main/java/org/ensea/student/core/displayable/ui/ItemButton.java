package org.ensea.student.core.displayable.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import org.ensea.student.core.displayable.Displayable;
import org.ensea.student.core.inventory.Item;

public class ItemButton implements Displayable, Selectable {
    public Item item;
    public boolean isSelected;
    private ImageButton button;
    private int x = 0;
    private int y = 0;
    private Stage stage;
    private Color iconUntinted;

    public ItemButton(Item item, int x, int y, Stage stage){
        this.item = item;
        this.stage = stage;
        Sprite buttonSprite = new Sprite(this.item.getItemSprite());
        button = new ImageButton(new SpriteDrawable(buttonSprite));
        button.setPosition(x, y);
        button.setSize(32, 32);
        iconUntinted = button.getImage().getColor().cpy();
        this.x = x;
        this.y = y;
    }
    public Item getItem(){
        return this.item;
    }
    @Override
    public void draw() {
        stage.act();
        stage.draw();
    }

    public void deselect(){
        this.isSelected = false;
        button.getImage().setColor(iconUntinted);
        button.clearListeners();
    }

    @Override
    public void onSelect() {
        button.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                isSelected = true;
                button.getImage().setColor(new Color(iconUntinted.r,iconUntinted.g,iconUntinted.b,.2f));
            }
        });
        if(isSelected){
            button.clearListeners();
            button.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    isSelected = false;
                    button.getImage().setColor(iconUntinted);
                }
            });
        }
        stage.addActor(button);
    }

    public boolean isSelected() {
        return this.isSelected;
    }
}
