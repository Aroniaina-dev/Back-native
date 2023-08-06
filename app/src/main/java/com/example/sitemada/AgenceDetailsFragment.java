package com.example.sitemada;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AgenceDetailsFragment} factory method to
 * create an instance of this fragment.
 */
public class AgenceDetailsFragment extends Fragment {

    private TextView textViewDesignation;
    private TextView textViewEmail;
    private TextView textViewContact;
    private TextView textViewDescription;

    public static AgenceDetailsFragment newInstance(AgenceModel agence) {
        AgenceDetailsFragment fragment = new AgenceDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable("agence", agence);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_agence_details, container, false);

        textViewDesignation = rootView.findViewById(R.id.textViewDesignation);
        textViewEmail = rootView.findViewById(R.id.textViewEmail);
        textViewContact = rootView.findViewById(R.id.textViewContact);
        textViewDescription = rootView.findViewById(R.id.textViewDescription);

        // Récupérer les données de l'agence depuis les arguments du fragment
        Bundle bundle = getArguments();
        if (bundle != null) {
            AgenceModel agence = bundle.getParcelable("agence");
            if (agence != null) {
                afficherDetailsAgence(agence);
            }
        }

        return rootView;
    }


    public void afficherDetailsAgence(AgenceModel agence) {
        textViewDesignation.setText(agence.getDesignation());
        textViewEmail.setText(agence.getEmail());
        textViewContact.setText(agence.getContact());
        textViewDescription.setText(agence.getDescriptionAgence());
    }
}
