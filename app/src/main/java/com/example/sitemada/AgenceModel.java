package com.example.sitemada;
import java.util.List;

// AgenceModel.java
public class AgenceModel {
    private String designation;
    private String email;
    private String password;
    private String contact;
    private String descriptionAgence;
//    private Publication publication;

    public AgenceModel() {

    }

    public AgenceModel(String designation, String email, String password, String contact, String descriptionAgence) {
        this.designation = designation;
        this.email = email;
        this.password = password;
        this.contact = contact;
        this.descriptionAgence = descriptionAgence;
//        this.publication = publication;
    }

//    public Publication getPublication() {
//        return publication;
//    }
//
//    public void setPublication(Publication publication) {
//        this.publication = publication;
//    }

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
