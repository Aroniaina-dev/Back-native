package com.example.sitemada;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class Login extends Fragment {
    private EditText inputEmail;
    private EditText inputPassword;
    private Button btnLogin;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        inputEmail = rootView.findViewById(R.id.inputEmail);
        inputPassword = rootView.findViewById(R.id.inputPassword);
        btnLogin = rootView.findViewById(R.id.btnLogin);
        progressBar = rootView.findViewById(R.id.progressBar);
        inputEmail.setText("fuck@gmail.com");
        inputPassword.setText("fuck");
        btnLogin.setOnClickListener(ConnexListener);

        return rootView;
    }

    private final View.OnClickListener ConnexListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String email = inputEmail.getText().toString().trim();
            String password = inputPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(getActivity(), "Veuillez saisir un e-mail et un mot de passe", Toast.LENGTH_SHORT).show();
            } else {
                loginUtilisateur(email, password);
            }
        }
    };

    private void loginUtilisateur(String email, String password) {
        progressBar.setVisibility(View.VISIBLE);
        ApiService apiService = ApiClient.getService();

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);

        Call<LoginResponse> loginResponseCall = apiService.login(loginRequest);

        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                progressBar.setVisibility(View.INVISIBLE);
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    System.out.println("Test de fuck");
                    System.out.println(response.body());
                    System.out.println(loginResponse);
                    if (loginResponse != null) {
                        // Enregistrez les informations de l'utilisateur dans les préférences partagées ici
                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        Gson gson = new Gson();
                        String loginResponseJson = gson.toJson(loginResponse);
                        editor.putString("login_response", loginResponseJson);
                        editor.apply();

                        // Afficher les informations dans le fragment suivant (AuthDetailsActivity)
                        AuthDetailsActivity authDetailsFragment = new AuthDetailsActivity();
                        Bundle bundle = new Bundle();

                        System.out.println(loginResponse.getAgenceModel());
                        bundle.putParcelable("agence_model", loginResponse.getAgenceModel());
                        authDetailsFragment.setArguments(bundle);

                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragmentContainer, authDetailsFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();

                    } else {
                        Toast.makeText(getActivity(), "Erreur de connexion", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Erreur de connexion", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(), "Erreur de connexion: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
