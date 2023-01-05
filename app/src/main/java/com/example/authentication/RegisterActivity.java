package com.example.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.content.Intent;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthResult;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_email, et_password;
    private Button btnregister;
    private TextView tv_login;
    public FirebaseAuth mAuth;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


        et_email=findViewById(R.id.email);
        et_password=findViewById(R.id.password);
        btnregister=findViewById(R.id.btn_register);
        tv_login=findViewById(R.id.textviewLogin);
        progressBar = findViewById(R.id.progressbar);


        if (mAuth.getCurrentUser() !=null){
            startActivity( new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));

            }
        });
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                register();
            }
        });
    }
    private void register(){

        progressBar.setVisibility(View.VISIBLE);
        final String name,email,password;

        email = et_email.getText().toString().trim();
        password = et_password.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            et_email.setError("Email is required.");
            et_email.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)){
            et_password.setError("Password is required.");
            et_password.requestFocus();
            return;
        }
        if (password.length() <8){
            et_password.setError("Password must have at least 8 characters");
            et_password.requestFocus();
            return;
        }


       mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(
                 new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //user is successfully registered and logged in
                            FirebaseUser user=mAuth.getCurrentUser();
                            updateUI(user);

                            Toast.makeText(RegisterActivity.this, "Registration successful!", Toast.LENGTH_LONG).show();
                            startActivity( new Intent(getApplicationContext(),MainActivity.class));
                           // progressBar.setVisibility(View.GONE);


                    }else{
                            Toast.makeText(RegisterActivity.this,"Error !"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            //progressBar.setVisibility(View.GONE);
                            updateUI(null);

                        }
                    }
                });
    }

    @Override
    public void onStart(){
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser=mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {

    }



    }

