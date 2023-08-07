package com.example.sitemada;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DestiDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DestiDetails extends Fragment {

    private TextView textViewTitre;
    private TextView textViewLocalisation;
    private TextView textViewDescri;

    public static DestiDetails newInstance(DestinationModel desti) {
        DestiDetails fragment = new DestiDetails();
        Bundle args = new Bundle();
        args.putParcelable("destination", desti);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_desti_details, container, false);

        textViewTitre = rootView.findViewById(R.id.textTitre);
        textViewLocalisation = rootView.findViewById(R.id.textLocalisation);
        textViewDescri = rootView.findViewById(R.id.textDescri);

        // Récupérer les données de l'agence depuis les arguments du fragment
        Bundle bundle = getArguments();
        if (bundle != null) {
            DestinationModel desti = bundle.getParcelable("destination");
            if (desti != null) {
                afficherDetailsDest(desti);
            }
        }

        return rootView;
    }


    public void afficherDetailsDest(DestinationModel desti) {
        textViewTitre.setText(desti.getTitre());
        textViewLocalisation.setText(desti.getLocalisation());
        textViewDescri.setText(desti.getDescription());
    }
}