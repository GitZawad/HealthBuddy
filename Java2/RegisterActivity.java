package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthcare.ui.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText edUserName, edUserPass, edEmail, edConfirmPass;
    TextView ExistUser;
    Button Regbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        edUserName = findViewById(R.id.editTextLTBAppFullName);
        edUserPass = findViewById(R.id.editTextLTBPinCode);
        edEmail = findViewById(R.id.editTextLTBAppAddress);
        edConfirmPass = findViewById(R.id.editTextLTBAPPContactNumber);
        ExistUser = findViewById(R.id.textViewExistUser);
        Regbtn = findViewById(R.id.buttonLTBReg);
        Database db = new Database(getApplicationContext(),"healthcare",null,1);

        ExistUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        Regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = edUserName.getText().toString();
                String password = edUserPass.getText().toString();
                String confirmPass = edConfirmPass.getText().toString();
                String email = edEmail.getText().toString();

                if(userName.length() == 0 || password.length() == 0 || confirmPass.length() == 0 || email.length() == 0){
                    Toast.makeText(RegisterActivity.this, "Please fill all the required fields", Toast.LENGTH_SHORT).show();

                }
                else{
                    if(password.compareTo(confirmPass) == 0){
                        if(email.contains("@gmail.com") || email.contains("@yahoo.com") || email.contains("@hotmail.com")){
                            if(isValid(password) && password.length() >= 8){
                                db.register(userName,email,password);
                                Toast.makeText(RegisterActivity.this, "Registration Complete", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            }
                            else{
                                Toast.makeText(RegisterActivity.this, "Password length should be at least 8 characters, letter, digit and special case", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(RegisterActivity.this, "Email address is incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(RegisterActivity.this, "Password and Confirm Password does not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    protected boolean isValid(String password){
        if(password.length() > 0){
            int f1 = 0;
            int f2 = 0;
            int f3 = 0;

            for(int a = 0; a < password.length(); a++){
                if(Character.isLetter(password.charAt(a))){
                    f1 = 1;
                }
            }

            for(int b = 0; b < password.length(); b++){
                if(Character.isDigit(password.charAt(b))){
                    f2 = 1;
                }
            }

            for(int c = 0; c < password.length(); c++){
                char d = password.charAt(c);

                if(d >= 33 && d <= 46 || d == 64){
                    f3 = 1;
                }
            }

            if(f1 == 1 && f2 == 1 && f3 == 1){
                return true;
            }
        }
        return false;
    }
}