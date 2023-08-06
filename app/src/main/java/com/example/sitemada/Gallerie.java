package com.example.sitemada;

import android.content.ClipData;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Gallerie extends Fragment {
    private RecyclerView recyclerView;
    private List<Item> itemList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallerie, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        itemList = new ArrayList<>();
        itemList.add(new Item(R.drawable.image_1, "Description de l'élément 1"));
        itemList.add(new Item(R.drawable.image_2, "Description de l'élément 2"));
        itemList.add(new Item(R.drawable.image_2, "Description de l'élément 3"));

        ItemAdapter adapter = new ItemAdapter(itemList);
        recyclerView.setAdapter(adapter);

        return view;
    }

}