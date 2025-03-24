package org.ensea.student.core.inventory;

import java.util.ArrayList;
import java.util.Arrays;

public class Inventory {
    private static Inventory instance;
    private final ArrayList<Item> inventoryItems = new ArrayList<Item>();

    /*Singleton*/
    public static Inventory getInstance(){
        if(instance == null){
            instance = new Inventory();
        }
        return instance;
    }

    public ArrayList<Item> getInventory(){
        return this.inventoryItems;
    }

    public void addItemToInventory(Item i){
        this.inventoryItems.add(i);
    }

    public void bulkAddItemToInventory(ArrayList<Item> items){
        this.inventoryItems.addAll(items);
    }

    public void bulkAddItemToInventory(Item[] items){
        this.inventoryItems.addAll(Arrays.asList(items));
    }

    public void removeItemFromInventory(Item i){
        this.inventoryItems.remove(i);
    }

    public void bulkRemoveItemFromInventory(ArrayList<Item> items){
        this.inventoryItems.removeAll(items);
    }

    public void bulkRemoveItemFromInventory(Item[] items){
        this.inventoryItems.removeAll(Arrays.asList(items));
    }
}
