package com.example.healthcare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.healthcare.ui.login.LoginActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();
        Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();

        CardView exit = findViewById(R.id.cardLogOut);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            }
        });

        CardView cardFindDoc = findViewById(R.id.cardFindDoctor);
        cardFindDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,FindDoctorActivity.class));
            }
        });

        CardView cardLabTest = findViewById(R.id.cardLabTest);
        cardLabTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, LabTestActivity.class));
            }
        });

        CardView cardOrderDetails = findViewById(R.id.cardOrderDetails);
        cardOrderDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, OrderDetailsActivity.class));
            }
        });

        CardView cardBuyMeds = findViewById(R.id.cardBuyMeds);
        cardBuyMeds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, BuyMedicineActivity.class));
            }
        });

        CardView cardHealthCalendar = findViewById(R.id.cardHealthCalendar);
        cardHealthCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, HealthArticlesActivity.class));
            }
        });


    }
}