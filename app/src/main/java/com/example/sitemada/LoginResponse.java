package com.example.sitemada;

public class LoginResponse {
    private String userId;
    private String token;
    private int satus;
    private AgenceModel agenceModel;
    // Ajoutez un constructeur par défaut pour la désérialisation JSON
    public LoginResponse() {
    }

    // Ajoutez les getters et les setters pour les propriétés userId et token

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getSatus() {
        return satus;
    }

    public void setSatus(int satus) {
        this.satus = satus;
    }

    public AgenceModel getAgenceModel() {
        return agenceModel;
    }

    public void setAgenceModel(AgenceModel agenceModel) {
        this.agenceModel = agenceModel;
    }
}
