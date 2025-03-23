package org.ensea.student.core.inventory;

import org.ensea.student.core.util.Constants;

public class SingleItem implements Item{
    private String label;
    private String description;
    private float weight;
    private float value;
    private Constants.ITEM_TYPES type;
    private String iconPath;


    @Override
    public float getItemWeight() {
        return this.weight;
    }

    @Override
    public float getItemValue() {
        return this.value;
    }

    @Override
    public String getItemLabel() {
        return this.label;
    }

    @Override
    public String getItemDescription() {
        return this.description;
    }

    @Override
    public String getItemIconLocation() {
        return this.iconPath;
    }

    @Override
    public String getItemType() {
        String t = this.type.toString();
        return t.substring(0,1).toUpperCase() + t.substring(1).toLowerCase();
    }
}
