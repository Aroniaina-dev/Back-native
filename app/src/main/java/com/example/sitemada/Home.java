package com.example.sitemada;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button agence;
    private Button Desti;
    private Button Gal;

    private RecyclerView recyclerView;
    private DestiAdapter destiAdapter;
    private List<DestinationModel> desti = new ArrayList<>();
    RecyclerView resu;
    private Resultat lista;

    public Home() {
        // Required empty public constructor
    }
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public View.OnClickListener agenceListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainer, new AgenceListFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        }
    };
    public View.OnClickListener gallListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainer, new DestiList());
            transaction.addToBackStack(null);
            transaction.commit();
        }
    };
    public View.OnClickListener destiListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainer, new DestiList());
            transaction.addToBackStack(null);
            transaction.commit();
        }
    };

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
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        agence = (Button) rootView.findViewById(R.id.button8);
        agence.setOnClickListener(agenceListener);
        Desti = (Button) rootView.findViewById(R.id.button5);
        Desti.setOnClickListener(destiListener);
        resu= rootView.findViewById(R.id.repa);
//        Gal = (Button) rootView.findViewById(R.id.button6);
//        Gal.setOnClickListener(gallListener);

        SearchView searchView = rootView.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ApiService apiService = ApiClient.getService();

                Call<List<DestinationModel>> call = apiService.search(query);
                lista = new Resultat();
                call.enqueue(new Callback<List<DestinationModel>>() {
                    @Override
                    public void onResponse(Call<List<DestinationModel>> call, Response<List<DestinationModel>> response) {
                        if (response.isSuccessful()) {
                            List<DestinationModel> DestiFromApi = response.body();

                            if (DestiFromApi != null) {
                                desti.clear();
                                desti.addAll(DestiFromApi);

                                destiAdapter = new DestiAdapter(desti, new DestiList.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(DestinationModel destination) {
                                        navigateToDestiDetails(destination);
                                    }
                                });
                                resu.setAdapter(destiAdapter);
                                resu.setLayoutManager(new LinearLayoutManager(getActivity()));
                            } else {
                                Toast.makeText(getActivity(), "Aucune agence trouvée.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Erreur lors de la récupération des agences", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<DestinationModel>> call, Throwable t) {
                        Toast.makeText(getActivity(), "Erreur de connexion: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Code à exécuter lorsque le texte de recherche change
                // Vous pouvez utiliser newText pour effectuer une recherche en temps réel
                return false;
            }
        });
        return rootView;

    }
    public void navigateToDestiDetails(DestinationModel destination) {
        DestiDetails detailsFragment = DestiDetails.newInstance(destination);
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, detailsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}