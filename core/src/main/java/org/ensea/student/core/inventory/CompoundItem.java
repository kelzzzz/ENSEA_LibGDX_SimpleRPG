package org.ensea.student.core.inventory;

import java.util.ArrayList;

public class CompoundItem implements Item{
    private final ArrayList<Item> itemGroup = new ArrayList<Item>();

    public void addItem(Item i){
        itemGroup.add(i);
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
        return itemGroup.get(0).getItemLabel();
    }

    @Override
    public String getItemDescription() {
        return itemGroup.get(0).getItemDescription();
    }

    @Override
    public String getItemIconLocation() {
        return itemGroup.get(0).getItemIconLocation();
    }

    @Override
    public String getItemType() {
        return itemGroup.get(0).getItemType();
    }
}
