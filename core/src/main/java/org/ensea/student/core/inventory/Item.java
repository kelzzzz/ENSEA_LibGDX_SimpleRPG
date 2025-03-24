package org.ensea.student.core.inventory;


import com.badlogic.gdx.graphics.g2d.TextureRegion;

public interface Item {
    public float getItemWeight();
    public float getItemValue();
    public String getItemLabel();
    public String getItemDescription();
    public TextureRegion getItemSprite();
    public void setIcon(TextureRegion icon);
    public String getItemType();
}
