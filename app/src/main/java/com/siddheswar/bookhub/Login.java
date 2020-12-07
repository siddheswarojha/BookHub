package com.siddheswar.bookhub;

import android.content.Intent;
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
import com.github.ybq.android.spinkit.style.CubeGrid;
import com.github.ybq.android.spinkit.style.FoldingCube;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;


public class Login extends Fragment {
    TextInputEditText PhoneLogIn,PasswordLogIn;
    Button btnLogIn;
    String Phone, Password;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v;
        v = inflater.inflate(R.layout.fragment_login, container, false);

        PhoneLogIn = v.findViewById(R.id.PhoneLogIn);
        PasswordLogIn=v.findViewById(R.id.PasswordLogIn);
        btnLogIn=v.findViewById(R.id.btnLogin);
        mAuth = FirebaseAuth.getInstance();
        progressBar = v.findViewById(R.id.spin_kit);



        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sprite doubleBounce = new FoldingCube();
                progressBar.setIndeterminateDrawable(doubleBounce);
                progressBar.setVisibility(View.VISIBLE);

                Phone =  PhoneLogIn.getText().toString();
                Password = PasswordLogIn.getText().toString();
                mAuth.signInWithEmailAndPassword(Phone, Password)
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Intent i = new Intent(getActivity(), Home.class);
                                    startActivity(i);

                                } else {
                                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();

                                }

                            }
                        });


            }

        });





        return v;




    }
}