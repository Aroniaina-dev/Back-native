package com.example.sitemada;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PublicationFragment extends Fragment {
    private ApiService apiService;
    private String agenceId;

    public PublicationFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiService = ApiClient.getService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_publication, container, false);

        // Récupérer les informations de l'agence passées en arguments du fragment
        String designation = getArguments().getString("designation");
        String contact = getArguments().getString("contact");
        String descriptionAgence = getArguments().getString("descriptionAgence");

        // Récupérer les TextViews dans le layout
        TextView designationTextView = rootView.findViewById(R.id.designationTextView);
        TextView contactTextView = rootView.findViewById(R.id.contactTextView);
        TextView descriptionAgenceTextView = rootView.findViewById(R.id.descriptionAgenceTextView);

        // Afficher les informations de l'agence dans les TextViews
        designationTextView.setText("Designation: " + designation);
        contactTextView.setText("Contact: " + contact);
        descriptionAgenceTextView.setText("Description: " + descriptionAgence);

        // Récupérer le conteneur des publications dans le layout
        LinearLayout publicationContainer = rootView.findViewById(R.id.publicationContainers);

        // Récupérer l'ID de l'agence pour récupérer ses publications
        agenceId = getArguments().getString("id");
        Call<AgenceModel> call = apiService.getAgenceById(agenceId);
        call.enqueue(new Callback<AgenceModel>() {
            @Override
            public void onResponse(Call<AgenceModel> call, Response<AgenceModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    System.out.println("Fetching data from API...");
                    AgenceModel agence = response.body();

                    // Récupérer les publications de l'agence
//                    List<Publication> publications = agence.getPublication();
//                    for (Publication publication : publications) {
//                        String description = publication.getDescription();
//                        String[] photos = publication.getPhoto();
//
//                        // Créer un TextView pour chaque publication et l'ajouter au conteneur des publications
//                        TextView publicationTextView = new TextView(getContext());
//                        publicationTextView.setLayoutParams(new LinearLayout.LayoutParams(
//                                LinearLayout.LayoutParams.MATCH_PARENT,
//                                LinearLayout.LayoutParams.WRAP_CONTENT
//                        ));
//                        publicationTextView.setText(description);
//                        publicationTextView.setTextColor(Color.BLACK);
//
//                        publicationContainer.addView(publicationTextView);
//                        System.out.println("Changement de page");
//                    }
                } else {
                    System.out.println("Failed to fetch data from API.");
                }
            }

            @Override
            public void onFailure(Call<AgenceModel> call, Throwable t) {
                System.out.println("Error occurred while fetching data: " + t.getMessage());
            }
        });

        return rootView;
    }
}
