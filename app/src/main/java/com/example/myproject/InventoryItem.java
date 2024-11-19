package com.example.myproject;

public class InventoryItem {
    String itemName, itemLocation;
    int itemImage;

    public InventoryItem(String itemName, String itemLocation, int itemImage) {
        this.itemName = itemName;
        this.itemLocation = itemLocation;
        this.itemImage = itemImage;
    }

    public String getName() {
        return itemName;
    }

    public void setName(String name) {
        this.itemName = name;
    }

    public String getLocation() {
        return itemLocation;
    }

    public void setLocation(String location) {
        this.itemLocation = location;
    }

    public int getItemImage() {
        return itemImage;
    }

    public void setItemImage(int itemImage) {
        this.itemImage = itemImage;
    }
}
