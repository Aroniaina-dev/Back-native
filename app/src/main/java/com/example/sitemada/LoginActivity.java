package com.example.sitemada;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText inputEmail = findViewById(R.id.inputEmail);
        EditText inputPassword = findViewById(R.id.inputPassword);
        TextView noAccount = findViewById(R.id.linkCreerCompte);
        TextView ForgotPass = findViewById(R.id.linkMotPasseOublie);
        Button btnLogin = findViewById(R.id.btnLogin);
        progressBar = findViewById(R.id.progressBar);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "Veuillez remplir tous les champs.", Toast.LENGTH_SHORT).show();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    LoginRequest loginRequest = new LoginRequest();
                    loginRequest.setEmail(email);
                    loginRequest.setPassword(password);
                    loginUtilisateur(loginRequest);
                }
            }
        });

        noAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    public void loginUtilisateur(LoginRequest loginRequest) {
        ApiService apiService = ApiClient.getService();

        Call<LoginResponse> loginResponseCall = apiService.login(loginRequest);

        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                progressBar.setVisibility(View.INVISIBLE);
                if (response.isSuccessful()) {
                    System.out.println("Ty mother fuck ah: ");
                    System.out.println(response);
                    LoginResponse loginResponse = response.body();

//                    if (loginResponse != null) {
                        // Enregistrez les informations de l'utilisateur dans les préférences partagées ici
                        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        Gson gson = new Gson();
                        String loginResponseJson = gson.toJson(loginResponse);
                        editor.putString("login_response", loginResponseJson);
                        editor.apply();

                        // Naviguer vers la HomeActivity
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        finish();
//                    } else {
//                        Toast.makeText(LoginActivity.this, "Réponse de connexion invalide", Toast.LENGTH_SHORT).show();
//                    }
                } else {
                    System.out.println("Ty mother fuck ah: ");
                    System.out.println(response);
                    Toast.makeText(LoginActivity.this, "Erreur de connexion, Fuck eeee", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(LoginActivity.this, "Erreur de connexion: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
