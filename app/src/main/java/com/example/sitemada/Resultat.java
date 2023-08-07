package com.example.sitemada;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Resultat#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Resultat extends Fragment {

    public RecyclerView recyclerView;
    private DestiAdapter destiAdapter;
    private List<DestinationModel> desti = new ArrayList<>();
    View rootView;

    public Resultat() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_resultat, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView1);

        return rootView;
    }

    public void alimenter(List<DestinationModel> DestiFromApi) {
        if (DestiFromApi != null) {
            desti.clear();
            desti.addAll(DestiFromApi);

            destiAdapter = new DestiAdapter(desti, new DestiList.OnItemClickListener() {
                @Override
                public void onItemClick(DestinationModel destination) {
                    navigateToDestiDetails(destination);
                }
            });

            recyclerView.setAdapter(destiAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        } else {
            Toast.makeText(getActivity(), "Aucune agence trouvée.", Toast.LENGTH_SHORT).show();
        }
    }

    public void navigateToDestiDetails(DestinationModel destination) {
        DestiDetails detailsFragment = DestiDetails.newInstance(destination);
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, detailsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public static Resultat newInstance() {
        Resultat fragment = new Resultat();
        Bundle args = new Bundle();
        return fragment;
    }
    public interface OnItemClickListener {
        void onItemClick(DestinationModel desti);
    }
}