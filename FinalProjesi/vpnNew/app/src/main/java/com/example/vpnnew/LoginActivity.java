package com.example.vpnnew;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameEt;
    private EditText passwordEt;
    private Button loginBtn;
    private TextView signupTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEt = findViewById(R.id.loginUsername);
        passwordEt = findViewById(R.id.loginPassword);
        loginBtn = findViewById(R.id.loginButton);
        signupTxt = findViewById(R.id.signUpText);

        loginBtn.setOnClickListener(v -> {
            String username = usernameEt.getText().toString().trim();
            String password = passwordEt.getText().toString();

            // Kullanıcı adı boş değilse MainActivity'e gönder
            if (!username.isEmpty()) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("username", username);  // 👈 kullanıcı adını taşıyoruz
                startActivity(intent);
            } else {
                usernameEt.setError("Kullanıcı adı boş olamaz");
            }
        });

        signupTxt.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
        });
    }
}
