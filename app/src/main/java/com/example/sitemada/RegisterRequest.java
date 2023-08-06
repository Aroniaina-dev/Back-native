package com.example.sitemada;

public class RegisterRequest {
    private String designation;
    private String email;
    private String password;
    private String contact;
    private String descriptionAgence;

    // Ajoutez un constructeur par défaut pour la désérialisation JSON
    public RegisterRequest() {
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDescriptionAgence() {
        return descriptionAgence;
    }

    public void setDescriptionAgence(String descriptionAgence) {
        this.descriptionAgence = descriptionAgence;
    }
}
