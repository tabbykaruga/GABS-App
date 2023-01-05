package com.example.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class LogOutFragment extends Fragment {
    public FirebaseAuth mAuth;
    private EditText et_email, et_password;
    private TextView tv_register;
    private Button btn_login;
    private ProgressBar progressBar;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_logout,container,false);
        mAuth=FirebaseAuth.getInstance();

        mAuth=FirebaseAuth.getInstance();
        et_email = view.findViewById(R.id.email);
        et_password = view.findViewById(R.id.password);
        btn_login = view.findViewById(R.id.btn_login);
        progressBar = view.findViewById(R.id.progressbar);
        tv_register=view.findViewById(R.id.textviewLogin);

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loging();

            }
        });

        return view;

    }
    public static final String TAG = "YOUR-TAG-NAME";
    private void loging() {

        progressBar.setVisibility(View.VISIBLE);
        String email, password;

        email = et_email.getText().toString().trim();
        password = et_password.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            et_email.setError("Email is required.");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            et_password.setError("Password is required.");
            return;
        }
        if (password.length() < 4) {

            et_email.setError("Password must have at least 4 characters");
            return;
        }

        //authenticate user
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information


                    Toast.makeText(getContext(), "Login successful!!!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getContext(), MainActivity.class));

                } else {
                    Toast.makeText(getContext(), "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}

