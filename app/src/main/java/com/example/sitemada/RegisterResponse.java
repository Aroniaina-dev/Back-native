package com.example.sitemada;

public class RegisterResponse {
    private String message;

    // Ajoutez un constructeur par défaut pour la désérialisation JSON
    public RegisterResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
