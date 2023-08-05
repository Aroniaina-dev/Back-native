package com.example.sitemada;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Fragment {
    private EditText txtlogin;
    private EditText txtpass;
    private Button Connex;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        txtlogin = (EditText) rootView.findViewById(R.id.txtlogin);
        txtpass = (EditText) rootView.findViewById(R.id.txtpass);
        Connex = (Button) rootView.findViewById(R.id.Connex);

        return rootView;
    }

    public View.OnClickListener ConnexListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.i("Connexion ... ", txtlogin.getText() + "/" + txtpass.getText());
        }
    };
}