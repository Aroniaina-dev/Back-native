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

public class DestiList extends Fragment {
    public RecyclerView recyclerView;
    private DestiAdapter destiAdapter;
    private List<DestinationModel> desti = new ArrayList<>();

    public DestiList() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_desti_list, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerViewD);

        // Charge toutes les agences depuis l'API
        getAllDesti();

        return rootView;
    }

    private void getAllDesti() {
        System.out.println("atooooo !!!!!!!!!!!!!!!!!!!!!!!!!");
        ApiService apiService = ApiClient.getService();

        Call<List<DestinationModel>> call = apiService.getAllDesti();
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

                        recyclerView.setAdapter(destiAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
    }

    public void navigateToDestiDetails(DestinationModel destination) {
        DestiDetails detailsFragment = DestiDetails.newInstance(destination);
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, detailsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public interface OnItemClickListener {
        void onItemClick(DestinationModel desti);
    }
}