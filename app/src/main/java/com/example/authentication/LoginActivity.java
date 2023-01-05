package com.example.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText et_email, et_password;
    private TextView tv_register;
    private Button btn_login;
    private ProgressBar progressBar;
    public FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

            mAuth = FirebaseAuth.getInstance();

            et_email = findViewById(R.id.email);
            et_password = findViewById(R.id.password);
            btn_login = findViewById(R.id.btn_login);
            progressBar = findViewById(R.id.progressbar);
            tv_register=findViewById(R.id.textviewLogin);

            tv_register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
                }
            });

            btn_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loging();

                }
            });
    }
    public static final String TAG = "YOUR-TAG-NAME";
    private void loging(){

        progressBar.setVisibility(View.VISIBLE);
        String email, password;

        email = et_email.getText().toString().trim();
        password = et_password.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            et_email.setError("Email is required.");
            return;
        }
        if (TextUtils.isEmpty(password)){
            et_password.setError("Password is required.");
            return;
        }
        if (password.length() <4){

            et_password.setError("Password must have at least 4 characters");
            return;
        }

        //authenticate user
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(LoginActivity.this, "Login successful!!!", Toast.LENGTH_LONG).show();
                    startActivity( new Intent(getApplicationContext(),MainActivity.class));

                }else {

                    Toast.makeText(LoginActivity.this,"Error !"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
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
