package com.example.vpnnew;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash); // Splash layout dosyanızı burada belirtin

        // 1 saniye bekle ve ardından LoginActivity'ye geçiş yap
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // LoginActivity'ye geçiş
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // SplashActivity'yi sonlandır
            }
        }, 1000); // 1000 milisaniye = 1 saniye
    }
}

