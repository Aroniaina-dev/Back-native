package com.example.sitemada;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AuthDetailsActivity#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AuthDetailsActivity extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private EditText editDesignation;
    private EditText editEmail;
    private EditText editContact;
    private EditText editDescription;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private static String userId;
    private static String token;
    public AuthDetailsActivity() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AuthDetailsActivity.
     */
    // TODO: Rename and change types and number of parameters
    public static AuthDetailsActivity newInstance(String param1, String param2) {
        AuthDetailsActivity fragment = new AuthDetailsActivity();
        Bundle args = new Bundle();
        args.putString("user_id", userId);
        args.putString("token", token);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_auth_details_activity, container, false);
        editDesignation = rootView.findViewById(R.id.editDesignation);
        editEmail = rootView.findViewById(R.id.editEmail);
        editContact = rootView.findViewById(R.id.editContact);
        editDescription = rootView.findViewById(R.id.editDescription);

        Bundle bundle = getArguments();
        if (bundle != null) {
            AgenceModel agenceModel = bundle.getParcelable("agence_model");
            if (agenceModel != null) {
                editDesignation.setText(agenceModel.getDesignation());
                editEmail.setText(agenceModel.getEmail());
                editContact.setText(agenceModel.getContact());
                editDescription.setText(agenceModel.getDescriptionAgence());

                Button btnModifier = rootView.findViewById(R.id.btnModifier);
                btnModifier.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String newDesignation = editDesignation.getText().toString();
                        String newEmail = editEmail.getText().toString();
                        String newContact = editContact.getText().toString();
                        String newDescription = editDescription.getText().toString();
                        agenceModel.setDesignation(newDesignation);
                        agenceModel.setDescriptionAgence(newDescription);
                        agenceModel.setContact(newContact);
                        agenceModel.setEmail(newEmail);
                        saveAgenceDetails(agenceModel);
                        Toast.makeText(getActivity(), "Donnée modifier!", Toast.LENGTH_SHORT).show();
                    }
                });

                Button btnSupprimer = rootView.findViewById(R.id.btnSupprimer);
                btnSupprimer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteAgence(agenceModel.get_id());
                        Toast.makeText(getActivity(), "Compte supprimer", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

        return rootView;
    }

    private void saveAgenceDetails(AgenceModel agenceModel) {
        // Créer un objet AgenceModel pour envoyer les détails modifiés à l'API
        AgenceModel agence = new AgenceModel();
        agence.setDesignation(agenceModel.getDesignation());
        agence.setDescriptionAgence(agenceModel.getDescriptionAgence());
        agence.setContact(agenceModel.getContact());
        agence.set_id(agenceModel.get_id());

        // Utilisez Retrofit pour appeler l'API pour enregistrer les modifications de l'agence
        Call<AgenceModel> call = ApiClient.getService().updateAgence(agence);
        call.enqueue(new Callback<AgenceModel>() {
            @Override
            public void onResponse(Call<AgenceModel> call, Response<AgenceModel> response) {
                if (response.isSuccessful()) {
                    // La modification a réussi, vous pouvez effectuer des actions supplémentaires ici
                    Toast.makeText(getActivity(), "Agence modifiée avec succès", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Échec de la modification de l'agence", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AgenceModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Erreur lors de la modification de l'agence: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteAgence(String agenceId) {
        Call<List<AgenceModel>> call = ApiClient.getService().deleteAgence(agenceId);
        call.enqueue(new Callback<List<AgenceModel>>() {
            @Override
            public void onResponse(Call<List<AgenceModel>> call, Response<List<AgenceModel>> response) {
                if (response.isSuccessful()) {
                    // La suppression a réussi, vous pouvez effectuer des actions supplémentaires ici
                    Toast.makeText(getActivity(), "Agence supprimée avec succès", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Échec de la suppression de l'agence", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<AgenceModel>> call, Throwable t) {
                Toast.makeText(getActivity(), "Erreur lors de la suppression de l'agence: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}