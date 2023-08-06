package com.example.sitemada;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class AgenceModel implements Parcelable {
    private String _id;
    private String idAgence;
    private String designation;
    private String email;
    private String password;
    private String contact;
    private String descriptionAgence;
    private List<String> publication;

    // Ajoutez un constructeur par défaut pour la désérialisation JSON
    public AgenceModel() {
    }

    public AgenceModel(String designation, String email, String password, String contact, String descriptionAgence) {
        this.designation = designation;
        this.email = email;
        this.password = password;
        this.contact = contact;
        this.descriptionAgence = descriptionAgence;
    }

    // Ajoutez le constructeur qui prend un Parcel en entrée pour la désérialisation
    protected AgenceModel(Parcel in) {
        _id = in.readString();
        idAgence = in.readString();
        designation = in.readString();
        email = in.readString();
        password = in.readString();
        contact = in.readString();
        descriptionAgence = in.readString();
        publication = in.createStringArrayList();
    }

    // Ajoutez les getters et les setters pour les propriétés

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getIdAgence() {
        return idAgence;
    }

    public void setIdAgence(String idAgence) {
        this.idAgence = idAgence;
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

    public List<String> getPublication() {
        return publication;
    }

    public void setPublication(List<String> publication) {
        this.publication = publication;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(idAgence);
        dest.writeString(designation);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(contact);
        dest.writeString(descriptionAgence);
        dest.writeStringList(publication);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<AgenceModel> CREATOR = new Parcelable.Creator<AgenceModel>() {
        @Override
        public AgenceModel createFromParcel(Parcel in) {
            return new AgenceModel(in);
        }

        @Override
        public AgenceModel[] newArray(int size) {
            return new AgenceModel[size];
        }
    };
}
