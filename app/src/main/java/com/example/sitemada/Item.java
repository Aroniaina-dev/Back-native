package com.example.sitemada;

public class Item {
    private int imageResId;
    private String description;

    public Item(int imageResId, String description) {
        this.imageResId = imageResId;
        this.description = description;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getDescription() {
        return description;
    }
}
