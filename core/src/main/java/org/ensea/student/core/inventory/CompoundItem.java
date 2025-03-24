package org.ensea.student.core.inventory;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.ensea.student.core.displayable.SpriteWrapper;

import java.util.ArrayList;

public class CompoundItem implements Item{
    private final ArrayList<Item> itemGroup = new ArrayList<Item>();

    public void addItem(Item i){
        itemGroup.add(i);
    }

    public void bulkAddItem(ArrayList<Item> items){
        itemGroup.addAll(items);
    }

    public void removeItem(Item i){
        itemGroup.remove(i);
    }

    public ArrayList<Item> getItemGroup(){
        return this.itemGroup;
    }

    @Override
    public float getItemWeight() {
        float w = 0;
        for(Item item : itemGroup){
            w+=item.getItemWeight();
        }
        return w;
    }

    @Override
    public float getItemValue() {
        float w = 0;
        for(Item item : itemGroup){
            w+=item.getItemValue();
        }
        return w;
    }

    @Override
    public String getItemLabel() {
        if(!itemGroup.isEmpty()){
            return itemGroup.get(0).getItemLabel();
        }return "";
    }

    @Override
    public String getItemDescription() {
        if(!itemGroup.isEmpty()){
            return itemGroup.get(0).getItemDescription();
        }
        return "";
    }

    @Override
    public TextureRegion getItemSprite() {
        return itemGroup.get(0).getItemSprite();
    }

    @Override
    public void setIcon(TextureRegion icon) {
        System.out.println("Nothing right now");
    }

    @Override
    public String getItemType() {
        return itemGroup.get(0).getItemType();
    }
}
