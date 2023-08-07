package com.example.sitemada;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;
import java.util.List;

public class DestinationModel implements Parcelable {
    private String _id;
    private String titre;
    private String description;
    private String localisation;
    private String[] image;

    // Ajoutez un constructeur par défaut pour la désérialisation JSON
    public DestinationModel() {
    }

    public DestinationModel(String titre, String description, String localisation, String[] image) {
        this.titre = titre;
        this.description = description;
        this.localisation = localisation;
        this.image = image;
    }

    // Ajoutez le constructeur qui prend un Parcel en entrée pour la désérialisation
    protected DestinationModel(Parcel in) {
        _id = in.readString();
        titre = in.readString();
        description = in.readString();
        localisation = in.readString();
        image = in.createStringArrayList().toArray(new String[0]);
    }

    // Ajoutez les getters et les setters pour les propriétés

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(titre);
        dest.writeString(description);
        dest.writeString(localisation);
        dest.writeStringList(Arrays.asList(image));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<DestinationModel> CREATOR = new Parcelable.Creator<DestinationModel>() {
        @Override
        public DestinationModel createFromParcel(Parcel in) {
            return new DestinationModel(in);
        }

        @Override
        public DestinationModel[] newArray(int size) {
            return new DestinationModel[size];
        }
    };

    public String getTitre() {
        return this.titre;
    }
    public String getDescription(){
        return this.description;
    }
    public String getLocalisation(){
        return this.localisation;
    }
}
