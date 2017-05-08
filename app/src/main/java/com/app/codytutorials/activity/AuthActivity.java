package com.app.codytutorials.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.codytutorials.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etEmail;
    private EditText etPassword;

    private Button btnEntry;
    private Button btnRegistr;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Intent intent = new Intent(AuthActivity.this, MainActivity.class);
                    startActivity(intent);

                }else{
                    // User is signed out
                }


            }
        };

        etEmail = (EditText)findViewById(R.id.et_email);
        etPassword = (EditText)findViewById(R.id.et_password);

        findViewById(R.id.btn_entry).setOnClickListener(this);
        findViewById(R.id.btn_register).setOnClickListener(this);

        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null){
            Intent intent = new Intent(AuthActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_entry) {
            entry(etEmail.getText().toString(), etPassword.getText().toString());
            FirebaseUser user = mAuth.getCurrentUser();
            if (user != null) {
                // User is signed in
                Intent intent = new Intent(AuthActivity.this, MainActivity.class);
                startActivity(intent);
            }

            if (etEmail.getText().toString().isEmpty() && etPassword.getText().toString().isEmpty()){
                Toast.makeText(AuthActivity.this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            }
        } else if (v.getId() == R.id.btn_register) {
            registration(etEmail.getText().toString(), etPassword.getText().toString());
        }
    }

    public void entry(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(AuthActivity.this, "Авторизация прошла успешно", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(AuthActivity.this, "Неудалось авторизоваться", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void registration(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(AuthActivity.this, "Регистрация прошла успешно", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(AuthActivity.this, "Неудалось зарегистрироваться", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
