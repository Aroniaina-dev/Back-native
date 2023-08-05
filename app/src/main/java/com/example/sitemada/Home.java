package com.example.sitemada;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Home extends Fragment {
    private Button agence;
    private Button Desti;
    private Button Gal;

    public View.OnClickListener agenceListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.i("Atooo e", "merci e");
            startActivity(new Intent(getActivity(), DashboardActivity.class));
        }
    };
    public View.OnClickListener gallListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(getActivity(), NotificationsActivity.class));
        }
    };
    public View.OnClickListener destiListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.i("Atooo e", "merci e");
            //startActivity(new Intent(getActivity(), Destination.class));
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        agence = (Button) rootView.findViewById(R.id.button8);
        agence.setOnClickListener(agenceListener);
        Desti = (Button) rootView.findViewById(R.id.button5);
        Desti.setOnClickListener(destiListener);
        Gal = (Button) rootView.findViewById(R.id.button6);
        Gal.setOnClickListener(gallListener);

        return rootView;
    }


}