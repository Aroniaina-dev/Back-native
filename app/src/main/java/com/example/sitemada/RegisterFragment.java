package com.example.sitemada;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText inputDesignation;
    private EditText inputEmail;
    private EditText inputPassword;
    private EditText inputContact;
    private EditText inputDescriptionAgence;
    private ProgressBar progressBar;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private void afficherNotificationInscriptionReussie() {
        if (getContext() == null) {
            return; // Assurez-vous que le contexte est disponible
        }

        // Créer l'intention pour ouvrir l'activité principale
        Intent intent = new Intent(getContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);

        // Construire la notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), "inscription_channel")
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentTitle("Inscription réussie")
                .setContentText("Votre inscription a été effectuée avec succès.")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        // Afficher la notification
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getContext());
        notificationManager.notify(1, builder.build());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);
        progressBar = rootView.findViewById(R.id.progressBar);
        inputDesignation = rootView.findViewById(R.id.inputDesignation);
        inputEmail = rootView.findViewById(R.id.inputEmail);
        inputPassword = rootView.findViewById(R.id.inputPassword);
        inputContact = rootView.findViewById(R.id.inputContact);
        inputDescriptionAgence = rootView.findViewById(R.id.inputDescriptionAgence);

        inputDesignation.setText("Test");
        inputEmail.setText("fuck@gmail.com");
        inputPassword.setText("fuck");
        inputContact.setText("Test");
        inputDescriptionAgence.setText("Test");
        // Ajoutez un OnClickListener pour le bouton d'inscription
        Button btnRegister = rootView.findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(registerClickListener);

        return rootView;
    }
    private final View.OnClickListener registerClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // Récupérer les informations saisies par l'utilisateur
            String designation = inputDesignation.getText().toString().trim();
            String email = inputEmail.getText().toString().trim();
            String password = inputPassword.getText().toString().trim();
            String contact = inputContact.getText().toString().trim();
            String descriptionAgence = inputDescriptionAgence.getText().toString().trim();
            registerUtilisateur(designation, email, password, contact, descriptionAgence);
        }

        private void registerUtilisateur(String designation, String email, String password, String contact, String descriptionAgence) {
            progressBar.setVisibility(View.VISIBLE);
            ApiService apiService = ApiClient.getService();
            AgenceModel agenceModel = new AgenceModel(designation,email,password,contact,descriptionAgence);
            RegisterRequest registerRequest = new RegisterRequest();
            registerRequest.setDesignation(designation);
            registerRequest.setEmail(email);
            registerRequest.setPassword(password);
            registerRequest.setContact(contact);
            registerRequest.setDescriptionAgence(descriptionAgence);

            Call<RegisterResponse> registerResponseCall = apiService.register(registerRequest);

            registerResponseCall.enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                    progressBar.setVisibility(View.INVISIBLE);
                    if (response.isSuccessful()) {
                        RegisterResponse registerResponse = response.body();
                        if (registerResponse != null) {
                            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            Gson gson = new Gson();
                            String loginResponseJson = gson.toJson(registerResponse);
                            editor.putString("login_response", loginResponseJson);
                            editor.apply();
                            afficherNotificationInscriptionReussie();
                            // Afficher les informations dans le fragment suivant (AuthDetailsActivity)
                            AuthDetailsActivity authDetailsFragment = new AuthDetailsActivity();
                            Bundle bundle = new Bundle();

                            bundle.putParcelable("agence_model", agenceModel);
                            authDetailsFragment.setArguments(bundle);

                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.fragmentContainer, authDetailsFragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                            Toast.makeText(getActivity(), registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Erreur lors de l'enregistrement", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getActivity(), "Erreur lors de l'enregistrement", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RegisterResponse> call, Throwable t) {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getActivity(), "Erreur de connexion: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    };
}