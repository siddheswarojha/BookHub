package com.siddheswar.bookhub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    TextView txtViewLogin,txtViewSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    txtViewLogin = findViewById(R.id.txtViewLogin);
    txtViewSignUp = findViewById(R.id.txtViewSignup);
    txtViewLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            txtViewLogin.setTextColor(getResources().getColor(R.color.colorAccent));
            txtViewSignUp.setTextColor(getResources().getColor(R.color.colorblack));
            FragmentTransaction frtOpen = getSupportFragmentManager().beginTransaction();
            frtOpen.replace(R.id.MainActivity, new Login()).commit();
        }
    });
    txtViewSignUp.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            txtViewSignUp.setTextColor(getResources().getColor(R.color.colorAccent));
            txtViewLogin.setTextColor(getResources().getColor(R.color.colorblack));
            FragmentTransaction frtOpenFragment = getSupportFragmentManager().beginTransaction();
            frtOpenFragment.replace(R.id.MainActivity,new SignUp()).commit();
        }
    });



    }

}