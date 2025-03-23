package org.ensea.student.core.inventory;


public interface Item {
    public float getItemWeight();
    public float getItemValue();
    public String getItemLabel();
    public String getItemDescription();
    public String getItemIconLocation();
    public String getItemType();
}
