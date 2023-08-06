package com.example.sitemada;
// Publication.java
public class Publication {
    private String description;
    private String[] photo;
    private String _id;

    // Ajoutez un constructeur par défaut pour la désérialisation JSON
    public Publication() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getPhoto() {
        return photo;
    }

    public void setPhoto(String[] photo) {
        this.photo = photo;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
