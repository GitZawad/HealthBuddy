package com.example.healthcare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class BuyMedicineDetailsActivity extends AppCompatActivity {

    TextView packages, totalCost;
    EditText details;
    Button btnBack, btnAddtoCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buy_medicine_details);

        packages = findViewById(R.id.textViewBMCartPackages);
        totalCost = findViewById(R.id.textViewBMDTotalCost);
        details = findViewById(R.id.editTextBMDTextMultiLine);
        details.setKeyListener(null);
        btnAddtoCart = findViewById(R.id.buttonBMDCart);
        btnBack = findViewById(R.id.buttonBMDBack);

        Intent intent = getIntent();
        packages.setText(intent.getStringExtra("text1"));
        details.setText(intent.getStringExtra("text2"));
        totalCost.setText("Total Cost : "+ intent.getStringExtra("text3") + " RMB");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineDetailsActivity.this, BuyMedicineActivity.class));
            }
        });

        btnAddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "").toString();
                String product = packages.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db = new Database(getApplicationContext(), "healthcare", null, 1);

                if(db.checkCart(username, product) == 1){
                    Toast.makeText(BuyMedicineDetailsActivity.this, "Product already added", Toast.LENGTH_SHORT).show();
                }
                else{
                    db.addCart(username, product, price, "medicine");
                    Toast.makeText(BuyMedicineDetailsActivity.this, "Product added successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuyMedicineDetailsActivity.this, BuyMedicineActivity.class));
                }
            }
        });
    }
}