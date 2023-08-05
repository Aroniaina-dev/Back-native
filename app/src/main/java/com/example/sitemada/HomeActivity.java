package com.example.sitemada;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    public ListView listView;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getTest();
    }

    public void getTest() {
        System.out.println("Fetching data from API...");
        Call<List<AgenceModel>> call = ApiClient.getService().getAllAgence();
        call.enqueue(new Callback<List<AgenceModel>>() {
            @Override
            public void onResponse(Call<List<AgenceModel>> call, Response<List<AgenceModel>> response) {
                System.out.println("Fuck0:");
                if (response.isSuccessful() && response.body() != null) {
                    List<AgenceModel> agenceList = response.body();
                    System.out.println("Fuck1:");
                    // Build a string to store all the designations
                    StringBuilder designationBuilder = new StringBuilder();

                    // Iterate through the list and append the designations
                    for (AgenceModel agence : agenceList) {
                        System.out.println("Fuck2:");
                        String designation = agence.getDesignation();
                        designationBuilder.append(designation).append("\n");
                    }

                    // Find the TextView and set the concatenated designations
                    TextView designationTextView = findViewById(R.id.designationTextView);
                    designationTextView.setText(designationBuilder.toString());
                } else {
                    System.out.println("Fuck else:");
                    System.out.println("Failed to fetch data from API.");
                }
            }

            @Override
            public void onFailure(Call<List<AgenceModel>> call, Throwable t) {
                System.out.println("Error occurred while fetching data: " + t.getMessage());
            }
        });
    }


}
