package com.example.sitemada;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class NotificationsActivity extends AppCompatActivity {

    DestiList a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        a=new DestiList();
        setContentView(R.layout.fragment_desti_list);
    }
}