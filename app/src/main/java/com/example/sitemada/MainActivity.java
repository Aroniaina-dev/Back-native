package com.example.sitemada;

import android.content.ClipData;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        itemList = new ArrayList<>();
        itemList.add(new Item(R.drawable.image_1, "Description de l'élément 1"));
        itemList.add(new Item(R.drawable.image_2, "Description de l'élément 2"));
        itemList.add(new Item(R.drawable.image_2, "Description de l'élément 3"));

        ItemAdapter adapter = new ItemAdapter(itemList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Gérez les clics sur les éléments de la BottomNavigationView ici
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        // Action lorsque l'élément "Accueil" est sélectionné
                        // Remplacez "HomeActivity" par le nom de votre classe d'activité pour la page d'accueil
                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                        return true;
                    case R.id.navigation_dashboard:
                        // Action lorsque l'élément "Tableau de bord" est sélectionné
                        // Remplacez "DashboardActivity" par le nom de votre classe d'activité pour le tableau de bord
                        startActivity(new Intent(MainActivity.this, DashboardActivity.class));
                        return true;
                    case R.id.navigation_notifications:
                        // Action lorsque l'élément "Notifications" est sélectionné
                        // Remplacez "NotificationsActivity" par le nom de votre classe d'activité pour les notifications
                        startActivity(new Intent(MainActivity.this, NotificationsActivity.class));
                        return true;
                }
                return false;
            }
        });
    }
}