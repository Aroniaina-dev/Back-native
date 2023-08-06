package com.example.sitemada;
// EditAgenceActivity.java

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditAgenceActivity extends AppCompatActivity {

    private EditText nomEditText;
    private EditText adresseEditText;
    private EditText telephoneEditText;
    private Button enregistrerButton;

    private String agenceId; // Variable pour stocker l'ID de l'agence en cours de modification

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_agence);

        // Récupérer l'ID de l'agence à modifier depuis l'intent
        agenceId = getIntent().getStringExtra("agenceId");

        // Référencer les vues
        nomEditText = findViewById(R.id.nomEditText);
        adresseEditText = findViewById(R.id.adresseEditText);
        telephoneEditText = findViewById(R.id.telephoneEditText);
        enregistrerButton = findViewById(R.id.enregistrerButton);

        // Charger les informations de l'agence depuis l'API et les afficher dans les champs d'édition
        loadAgenceDetails();

        // Ajouter un OnClickListener pour le bouton "Enregistrer"
        enregistrerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Appeler la méthode pour enregistrer les modifications de l'agence via l'API
                saveAgenceDetails();
            }
        });
    }

    private void loadAgenceDetails() {
        // Utilisez Retrofit pour appeler l'API pour obtenir les détails de l'agence par son ID
        Call<AgenceModel> call = ApiClient.getService().getAgenceById(agenceId);
        call.enqueue(new Callback<AgenceModel>() {
            @Override
            public void onResponse(Call<AgenceModel> call, Response<AgenceModel> response) {
                if (response.isSuccessful()) {
                    // Afficher les informations de l'agence dans les champs d'édition
                    AgenceModel agence = response.body();
                    if (agence != null) {
                        nomEditText.setText(agence.getDesignation());
                        adresseEditText.setText(agence.getDescriptionAgence());
                        telephoneEditText.setText(agence.getContact());
                    }
                } else {
                    Toast.makeText(EditAgenceActivity.this, "Échec de chargement des détails de l'agence", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AgenceModel> call, Throwable t) {
                Toast.makeText(EditAgenceActivity.this, "Erreur lors du chargement des détails de l'agence: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveAgenceDetails() {
        // Récupérer les valeurs des champs d'édition
        String designation = nomEditText.getText().toString().trim();
        String descriptionAgence = adresseEditText.getText().toString().trim();
        String contact = telephoneEditText.getText().toString().trim();

        // Vérifier si les champs obligatoires sont remplis
        if (designation.isEmpty()) {
            Toast.makeText(this, "Veuillez saisir la désignation de l'agence", Toast.LENGTH_SHORT).show();
            return;
        }

        // Créer un objet AgenceModel pour envoyer les détails modifiés à l'API
        AgenceModel agence = new AgenceModel();
        agence.setDesignation(designation);
        agence.setDescriptionAgence(descriptionAgence);
        agence.setContact(contact);
        agence.set_id(agenceId);

        // Utilisez Retrofit pour appeler l'API pour enregistrer les modifications de l'agence
        Call<AgenceModel> call = ApiClient.getService().updateAgence(agence);
        call.enqueue(new Callback<AgenceModel>() {
            @Override
            public void onResponse(Call<AgenceModel> call, Response<AgenceModel> response) {
                if (response.isSuccessful()) {
                    // La modification a réussi, vous pouvez effectuer des actions supplémentaires ici
                    Toast.makeText(EditAgenceActivity.this, "Agence modifiée avec succès", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditAgenceActivity.this, "Échec de la modification de l'agence", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AgenceModel> call, Throwable t) {
                Toast.makeText(EditAgenceActivity.this, "Erreur lors de la modification de l'agence: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}