package org.ensea.student.core.inventory;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.ensea.student.core.util.Constants;

public class SingleItem implements Item{
    private String label;
    private String description;
    private float weight;
    private float value;
    private Constants.ITEM_TYPES type;
    private int iconRow;
    private int iconCol;
    private TextureRegion icon;

    @Override
    public void setIcon(TextureRegion icon) {
        this.icon = icon;
    }

    public int getIconCol() {
        return iconCol;
    }

    public int getIconRow() {
        return iconRow;
    }

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
    public TextureRegion getItemSprite() {
        return this.icon;
    }

    @Override
    public String getItemType() {
        String t = this.type.toString();
        return t.substring(0,1).toUpperCase() + t.substring(1).toLowerCase();
    }
}
