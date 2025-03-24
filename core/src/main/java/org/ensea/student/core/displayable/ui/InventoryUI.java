package org.ensea.student.core.displayable.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import org.ensea.student.core.displayable.Displayable;
import org.ensea.student.core.inventory.CompoundItem;
import org.ensea.student.core.inventory.Inventory;
import org.ensea.student.core.inventory.Item;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InventoryUI implements Displayable {
    Boolean open = false;
    ShapeRenderer shape = new ShapeRenderer();
    ArrayList<ItemButton> itemButtons = new ArrayList<ItemButton>();
    CompoundItem currentItem;
    int UIX = 100;
    int UIY = 200;
    int UIW = 190;
    int UIH = 220;
    Stage stage;
    BitmapFont font = font = new BitmapFont();

    public InventoryUI(){
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Inventory inv = Inventory.getInstance();
        int spacer = 0;
        //TODO: This is temporary -- it doesn't handle groups of items larger than the width of the window!!!
        for(Item i : inv.getInventory()){
            this.itemButtons.add(new ItemButton(i,UIX+spacer,UIY+UIH-32, stage));
            spacer+=32;
        }
    }

    public boolean isOpen(){
        return open;
    }

    @Override
    public void draw() {
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.WHITE);
        shape.rect(UIX,UIY,190,220);
        shape.end();

        for(ItemButton ib : itemButtons){
            ib.draw();
        }
        ArrayList<Item> items = getSelectedItems();
        currentItem = createCompositeItemFromSelection(items);
        SpriteBatch batch = new SpriteBatch();
        batch.begin();
        font.draw(batch, "Weight: ".concat(String.valueOf(currentItem.getItemWeight())), UIX,UIY+100);
        font.draw(batch, "Value: ".concat(String.valueOf(currentItem.getItemValue())), UIX+100,UIY+100);
        font.draw(batch, currentItem.getItemLabel(), UIX,UIY+70);
        font.draw(batch, currentItem.getItemDescription(), UIX,UIY+50);
        batch.end();
    }

    public void open() {
        if (Gdx.input.isKeyPressed(Input.Keys.I)) {
            open = true;
        }
        if (open) {
            draw();
            for(ItemButton ib : itemButtons){
                ib.onSelect();
            }

        }
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE) && open) {
                this.hide();
        }
    }

    public ArrayList<Item> getSelectedItems(){
        Stream<ItemButton> selections = itemButtons.stream()
                .filter(ib -> Boolean.TRUE.equals(ib.isSelected()));
        ArrayList<ItemButton> selectedItems = selections
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Item> result = new ArrayList<>();
        for(ItemButton ib : selectedItems){
            result.add(ib.getItem());
        }
        return result;
    }

    public CompoundItem createCompositeItemFromSelection(ArrayList<Item> items){
        CompoundItem item = new CompoundItem();
        item.bulkAddItem(items);
        return item;
    }

    public void hide(){
        this.open = false;
        for(ItemButton ib : itemButtons){
            ib.deselect();
        }
    }
}
