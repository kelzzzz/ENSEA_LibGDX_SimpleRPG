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
    Boolean windowOpen = false;

    RoundedRectangleShapeRenderer shape = new RoundedRectangleShapeRenderer();
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
            this.itemButtons.add(new ItemButton(i,UIX+spacer,UIY+UIH-38, stage));
            spacer+=32;
        }
    }

    public boolean getWindowOpen(){
        return windowOpen;
    }

    @Override
    public void draw() {
        drawWindowRectangles();
        drawItemButtons();
        updateSelectedItems();
        drawItemDetails();
    }

    private void drawItemDetails() {
        SpriteBatch batch = new SpriteBatch();
        batch.begin();
        font.draw(batch, "Weight: ".concat(String.valueOf(currentItem.getItemWeight())), UIX+5,UIY+100);
        font.draw(batch, "Value: ".concat(String.valueOf(currentItem.getItemValue())), UIX+105,UIY+100);
        font.draw(batch, currentItem.getItemLabel(), UIX+5,UIY+70);
        font.draw(batch, currentItem.getItemDescription(), UIX+5,UIY+50);
        batch.end();
    }

    private void updateSelectedItems() {
        ArrayList<Item> items = getSelectedItems();
        currentItem = createCompositeItemFromSelection(items);
    }

    private void drawItemButtons() {
        for(ItemButton ib : itemButtons){
            ib.draw();
        }
    }

    private void drawWindowRectangles() {
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(.5f, .5f,.5f,1);
        shape.roundedRect(UIX-5,UIY,190,220,7);
        shape.end();
        shape.begin(ShapeRenderer.ShapeType.Line);
        shape.setColor(Color.BLACK);
        shape.roundedRect(UIX-5,UIY,190,220,7);
        shape.end();
    }

    public void open() {
        if (Gdx.input.isKeyPressed(Input.Keys.I)) {
            windowOpen = true;
        }
        if (windowOpen) {
            draw();
            for(ItemButton ib : itemButtons){
                ib.onSelect();
            }

        }
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE) && windowOpen) {
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
        this.windowOpen = false;
        for(ItemButton ib : itemButtons){
            ib.deselect();
        }
    }
}
