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

public class AgenceListFragment extends Fragment {

    private RecyclerView recyclerView;
    private AgenceAdapter agenceAdapter;
    private List<AgenceModel> agences = new ArrayList<>();

    public AgenceListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_agence_list, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView);

        // Charge toutes les agences depuis l'API
        getAllAgencesFromApi();

        return rootView;
    }

    private void getAllAgencesFromApi() {
        ApiService apiService = ApiClient.getService();

        Call<List<AgenceModel>> call = apiService.getAllAgence();
        call.enqueue(new Callback<List<AgenceModel>>() {
            @Override
            public void onResponse(Call<List<AgenceModel>> call, Response<List<AgenceModel>> response) {
                if (response.isSuccessful()) {
                    List<AgenceModel> agencesFromApi = response.body();

                    if (agencesFromApi != null) {
                        agences.clear();
                        agences.addAll(agencesFromApi);

                        agenceAdapter = new AgenceAdapter(agences, new OnItemClickListener() {
                            @Override
                            public void onItemClick(AgenceModel agence) {
                                navigateToAgenceDetails(agence);
                            }
                        });

                        recyclerView.setAdapter(agenceAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    } else {
                        Toast.makeText(getActivity(), "Aucune agence trouvée.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Erreur lors de la récupération des agences", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<AgenceModel>> call, Throwable t) {
                Toast.makeText(getActivity(), "Erreur de connexion: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void navigateToAgenceDetails(AgenceModel agence) {
        AgenceDetailsFragment detailsFragment = AgenceDetailsFragment.newInstance(agence);
        System.out.println(agence.getDesignation());
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, detailsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public interface OnItemClickListener {
        void onItemClick(AgenceModel agence);
    }
}