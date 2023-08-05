package com.example.sitemada;

public class Publication {
    String description;
    String photo;

    public Publication() {

    }

    public Publication(String description, String photo) {
        this.description = description;
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
