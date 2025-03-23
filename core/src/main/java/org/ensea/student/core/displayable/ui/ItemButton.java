package org.ensea.student.core.displayable.ui;

import org.ensea.student.core.displayable.Displayable;
import org.ensea.student.core.inventory.Item;

public class ItemButton implements Displayable, Selectable {
    public String pathToIcon;
    public boolean isSelected;

    public ItemButton(Item item){
        //set the button features
    }

    @Override
    public void draw() {

    }

    @Override
    public void onSelect() {
        isSelected = true;
        //highlight
        //display description
        //display features
    }
}
