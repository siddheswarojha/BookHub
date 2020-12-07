package com.siddheswar.bookhub;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.FoldingCube;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;

public class SignUp extends Fragment {
    TextInputEditText UserNameSignUp, PhoneSignUp, PasswordSignUp;
    Button btnSignup ;
    private FirebaseAuth mAuth;
    String User,PhoneNumber,Password;
    ProgressBar progressBar;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.fragment_sign_up, container, false);

        UserNameSignUp=V.findViewById(R.id.UserNameSignUp);
        PhoneSignUp=V.findViewById(R.id.PhoneSignUp);
        PasswordSignUp=V.findViewById(R.id.PasswordSignUp);
        btnSignup= V.findViewById(R.id.btnSignUp);
        mAuth = FirebaseAuth.getInstance();
        progressBar = V.findViewById(R.id.spin_kit2);




        btnSignup.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Sprite doubleBounce = new FoldingCube();
        progressBar.setIndeterminateDrawable(doubleBounce);
        progressBar.setVisibility(View.VISIBLE);
        User = UserNameSignUp.getText().toString();

        PhoneNumber=PhoneSignUp.getText().toString();
        Password = PasswordSignUp.getText().toString();
        mAuth.createUserWithEmailAndPassword( PhoneNumber,Password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Registered Successfully", Toast.LENGTH_LONG).show();

                            progressBar.setVisibility(View.INVISIBLE);
                        } else {
                            Toast.makeText(getActivity(), "Error 404", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.INVISIBLE);

                        }

                    }
                });





    }
});




        return  V;
    }
}