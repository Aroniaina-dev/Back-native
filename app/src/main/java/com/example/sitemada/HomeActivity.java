package com.example.sitemada;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.sitemada.databinding.ActivityHomeBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    private List<AgenceModel> agenceList;
    private List<PublicationFragment> publicationFragments;
    ActivityHomeBinding navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigation = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(navigation.getRoot());
        agenceList = new ArrayList<>();
        publicationFragments = new ArrayList<>();
//        getTest();
        goToOtherPAge(new Home());
        navigation.bottomNavigationView.setOnItemReselectedListener(item -> {
            switch (item.getItemId()){
                case R.id.navigation_home:
                    goToOtherPAge(new Home());
                    break;
                case R.id.navigation_notifications:
                    goToOtherPAge(new Gallerie());
                    break;
                case R.id.navigation_dashboard:
                    goToOtherPAge(new Login());
                    break;
            }
        });

    }

//    public void getTest() {
//        System.out.println("Fetching data from API...");
//        Call<List<AgenceModel>> call = ApiClient.getService().getAllAgence();
//        call.enqueue(new Callback<List<AgenceModel>>() {
//            @Override
//            public void onResponse(Call<List<AgenceModel>> call, Response<List<AgenceModel>> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    agenceList = response.body();
//                    LinearLayout agenceContainer = findViewById(R.id.agenceContainer);
//
//                    // HomeActivity.java
//                    for (AgenceModel agence : agenceList) {
//                        String designation = agence.getDesignation();
//                        String contact = agence.getContact();
//                        String descriptionAgence = agence.getDescriptionAgence();
//
//                        // Utilisez le contexte de MainActivity (HomeActivity.this) pour créer le CardView
//                        CardView cardView = new CardView(HomeActivity.this);
//                        cardView.setLayoutParams(new CardView.LayoutParams(
//                                CardView.LayoutParams.MATCH_PARENT,
//                                CardView.LayoutParams.WRAP_CONTENT
//                        ));
//                        cardView.setCardElevation(4);
//                        cardView.setContentPadding(16, 16, 16, 16);
//
//                        // Ajoutez la bordure autour de chaque CardView
//                        cardView.setCardBackgroundColor(Color.GRAY); // Couleur de fond de la carte
//                        cardView.setCardElevation(8); // Épaisseur de l'ombre
//                        cardView.setUseCompatPadding(true); // Pour éviter que l'ombre soit coupée à cause des marges
//                        cardView.setRadius(16);
//
//                        // Créez un TextView pour afficher les informations de l'agence dans la carte
//                        TextView agenceInfoTextView = new TextView(HomeActivity.this);
//                        agenceInfoTextView.setLayoutParams(new LinearLayout.LayoutParams(
//                                LinearLayout.LayoutParams.MATCH_PARENT,
//                                LinearLayout.LayoutParams.WRAP_CONTENT
//                        ));
//                        String agenceInfo = "Designation: " + designation + "\n"
//                                + "Contact: " + contact + "\n"
//                                + "Description: " + descriptionAgence;
//                        agenceInfoTextView.setText(agenceInfo);
//                        agenceInfoTextView.setTextColor(Color.WHITE);
//
//                        // Ajoutez le TextView à la carte et ajoutez la carte au conteneur
//                        cardView.addView(agenceInfoTextView);
//                        agenceContainer.addView(cardView);
//
//                        // Créez un fragment PublicationFragment pour chaque agence
//                        PublicationFragment publicationFragment = new PublicationFragment();
//                        Bundle args = new Bundle();
//                        args.putString("id", agence.get_id());
//                        args.putString("designation", designation);
//                        args.putString("contact", contact);
//                        args.putString("descriptionAgence", descriptionAgence);
//                        publicationFragment.setArguments(args);
//
//                        // Ajoutez le fragment à la liste
//                        publicationFragments.add(publicationFragment);
//
//                        // Définissez le clic sur le CardView pour afficher le fragment correspondant
//                        cardView.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                int position = agenceContainer.indexOfChild(cardView);
//                                displayPublicationFragment(position);
//                            }
//                        });
//
//                        // Créer un LinearLayout horizontal pour les boutons Modifier et Supprimer
//                        LinearLayout buttonLayout = new LinearLayout(HomeActivity.this);
//                        buttonLayout.setLayoutParams(new LinearLayout.LayoutParams(
//                                LinearLayout.LayoutParams.MATCH_PARENT,
//                                LinearLayout.LayoutParams.WRAP_CONTENT
//                        ));
//                        buttonLayout.setOrientation(LinearLayout.HORIZONTAL);
//                        buttonLayout.setGravity(Gravity.END); // Aligner les boutons à droite
//
//                        // Bouton Modifier
//                        Button editButton = new Button(HomeActivity.this);
//                        editButton.setLayoutParams(new LinearLayout.LayoutParams(
//                                LinearLayout.LayoutParams.WRAP_CONTENT,
//                                LinearLayout.LayoutParams.WRAP_CONTENT
//                        ));
//                        editButton.setText("Modifier");
//                        editButton.setId(View.generateViewId()); // Attribuer un ID unique
//                        buttonLayout.addView(editButton);
//
//                        // Bouton Supprimer
//                        Button deleteButton = new Button(HomeActivity.this);
//                        deleteButton.setLayoutParams(new LinearLayout.LayoutParams(
//                                LinearLayout.LayoutParams.WRAP_CONTENT,
//                                LinearLayout.LayoutParams.WRAP_CONTENT
//                        ));
//                        deleteButton.setText("Supprimer");
//                        deleteButton.setId(View.generateViewId()); // Attribuer un ID unique
//                        buttonLayout.addView(deleteButton);
//
//                        // Ajouter le LinearLayout des boutons à la carte
//                        cardView.addView(buttonLayout);
//
//                        // Définir les clics sur les boutons pour les actions de modification et de suppression
//                        // Lorsque vous cliquez sur le bouton Modifier
//                        // Lorsque vous cliquez sur le bouton Modifier
//                        editButton.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                // Récupérez l'ID de l'agence sélectionnée
//                                String agenceId = agence.get_id();
//
//                                // Créez une intention pour ouvrir l'activité de modification et passez l'ID en tant qu'argument
//                                Intent intent = new Intent(HomeActivity.this, EditAgenceActivity.class);
//                                intent.putExtra("agenceId", agenceId);
//                                startActivity(intent);
//                            }
//                        });
//
//
//
//                        deleteButton.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                String agenceId = agence.get_id();
//                                deleteAgence(agenceId);
//                            }
//                        });
//                    }
//
//                } else {
//                    System.out.println("Failed to fetch data from API.");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<AgenceModel>> call, Throwable t) {
//                System.out.println("Error occurred while fetching data: " + t.getMessage());
//            }
//        });
//    }

    // HomeActivity.java


    private void deleteAgence(String agenceId) {
        // Utilisez Retrofit pour appeler l'API de suppression d'agence
        Call<List<AgenceModel>> call = ApiClient.getService().deleteAgence(agenceId);
        call.enqueue(new Callback<List<AgenceModel>>() {
            @Override
            public void onResponse(Call<List<AgenceModel>> call, Response<List<AgenceModel>> response) {
                if (response.isSuccessful()) {
                    // La suppression a réussi, vous pouvez effectuer des actions supplémentaires ici
                    Toast.makeText(HomeActivity.this, "Agence supprimée avec succès", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(HomeActivity.this, "Échec de la suppression de l'agence", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<AgenceModel>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Erreur lors de la suppression de l'agence: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void displayPublicationFragment(int position) {
        // Utilisez un FragmentManager pour ajouter le fragment à MainActivity
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, publicationFragments.get(position));
        fragmentTransaction.addToBackStack(null); // Pour pouvoir revenir à MainActivity depuis le fragment si nécessaire
        fragmentTransaction.commit();
    }

    private void goToOtherPAge(Fragment position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, position);
        fragmentTransaction.commit();
    }
}
