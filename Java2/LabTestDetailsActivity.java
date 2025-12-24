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

public class LabTestDetailsActivity extends AppCompatActivity {

    TextView packageName, TotalCost;
    EditText edDetails;
    Button back, cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab_test_details);

        packageName = findViewById(R.id.textViewBMCartPackages);
        TotalCost = findViewById(R.id.textViewBMDTotalCost);
        edDetails = findViewById(R.id.editTextBMDTextMultiLine);
        back = findViewById(R.id.buttonBMDBack);
        cart = findViewById(R.id.buttonBMDCart);

        edDetails.setKeyListener(null);

        Intent intent = getIntent();
        packageName.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        TotalCost.setText("Total Cost : "+ intent.getStringExtra("text3") + "RMB");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestDetailsActivity.this, LabTestActivity.class));
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                String product = packageName.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db = new Database(getApplicationContext(), "healthcare", null, 1);

                if(db.checkCart(username,product) == 1){
                    Toast.makeText(LabTestDetailsActivity.this, "Product already added", Toast.LENGTH_SHORT).show();
                }else {
                    db.addCart(username,product,price,"lab");
                    Toast.makeText(LabTestDetailsActivity.this, "Added to cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestDetailsActivity.this,LabTestActivity.class));
                }
            }
        });




    }
}