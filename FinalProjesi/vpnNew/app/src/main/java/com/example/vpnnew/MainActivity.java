package com.example.vpnnew;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView statusText;
    private ImageButton myButton;
    private TextView welcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusText = findViewById(R.id.statusText);
        myButton = findViewById(R.id.myButton);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectWithOpenVPN();
            }
        });

        welcomeText = findViewById(R.id.welcomeText);
        String username = getIntent().getStringExtra("username");

// Eğer veri geldiyse TextView'a yaz
        if (username != null && !username.isEmpty()) {
            welcomeText.setText("Hoş geldiniz, " + username);
        }
    }

    private void connectWithOpenVPN() {
        // OpenVPN for Android paket adı
        String vpnPackage = "de.blinkt.openvpn";

        // Uygulamayı başlatma niyeti
        Intent intent = getPackageManager().getLaunchIntentForPackage(vpnPackage);

        if (intent != null) {
            startActivity(intent);
            statusText.setText("OpenVPN açılıyor...");
            myButton.setBackgroundResource(R.drawable.active_button);
        } else {
            Toast.makeText(this, "OpenVPN uygulaması bulunamadı. Yükleniyor...", Toast.LENGTH_LONG).show();
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=de.blinkt.openvpn")));
        }
    }
}
