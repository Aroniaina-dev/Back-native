package com.example.sitemada;

// RegisterActivity.java
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private EditText inputDesignation;
    private EditText inputEmail;
    private EditText inputPassword;
    private EditText inputContact;
    private EditText inputDescriptionAgence;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputDesignation = findViewById(R.id.inputDesignationRegister);
        inputEmail = findViewById(R.id.inputEmailRegister);
        inputPassword = findViewById(R.id.inputPasswordRegister);
        inputContact = findViewById(R.id.inputContactRegister);
        inputDescriptionAgence = findViewById(R.id.inputDescriptionAgenceRegister);
        btnRegister = findViewById(R.id.btnRegister);
        progressBar = findViewById(R.id.progressBar);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (areFieldsValid()) {
                    progressBar.setVisibility(View.VISIBLE);
                    RegisterRequest registerRequest = new RegisterRequest();
                    registerRequest.setDesignation(inputDesignation.getText().toString());
                    registerRequest.setEmail(inputEmail.getText().toString());
                    registerRequest.setPassword(inputPassword.getText().toString());
                    registerRequest.setContact(inputContact.getText().toString());
                    registerRequest.setDescriptionAgence(inputDescriptionAgence.getText().toString());

                    registerUser(registerRequest);
                }
            }
        });
    }

    private boolean areFieldsValid() {
        // Vérifiez si les champs sont valides, par exemple si les champs obligatoires sont remplis.
        String designation = inputDesignation.getText().toString().trim();
        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();
        String contact = inputContact.getText().toString().trim();
        String descriptionAgence = inputDescriptionAgence.getText().toString().trim();

        if (designation.isEmpty() || email.isEmpty() || password.isEmpty() || contact.isEmpty() || descriptionAgence.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs.", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Vous pouvez ajouter d'autres vérifications selon vos besoins.
        // Par exemple, vérifier si l'email est au bon format, si le mot de passe a une longueur minimale, etc.

        return true;
    }

    private void registerUser(RegisterRequest registerRequest) {
        ApiService apiService = ApiClient.getRetrofit().create(ApiService.class);
        Call<RegisterResponse> registerCall = apiService.register(registerRequest);

        registerCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                progressBar.setVisibility(View.INVISIBLE);
                if (response.isSuccessful()) {
                    RegisterResponse registerResponse = response.body();
                    if (registerResponse != null && registerResponse.getMessage() != null) {
                        Toast.makeText(RegisterActivity.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
                    finish();
                    // Vous pouvez ajouter d'autres actions après l'inscription réussie.
                } else {
                    Toast.makeText(RegisterActivity.this, "Une erreur s'est produite lors de l'inscription.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(RegisterActivity.this, "Erreur: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}