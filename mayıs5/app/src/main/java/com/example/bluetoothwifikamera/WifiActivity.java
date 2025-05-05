package com.example.bluetoothwifikamera;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WifiActivity extends AppCompatActivity {

    Button btnEnableWifi;
    TextView txtWifiStatus;
    WifiManager wifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);

        btnEnableWifi = findViewById(R.id.btnEnableWifi);
        txtWifiStatus = findViewById(R.id.txtWifiStatus);

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        updateWifiStatus();

        btnEnableWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!wifiManager.isWifiEnabled()) {
                    wifiManager.setWifiEnabled(true);
                    Toast.makeText(WifiActivity.this, "WiFi açılıyor...", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(WifiActivity.this, "WiFi zaten açık.", Toast.LENGTH_SHORT).show();
                }
                updateWifiStatus();
            }
        });
    }

    private void updateWifiStatus() {
        if (wifiManager.isWifiEnabled()) {
            txtWifiStatus.setText("WiFi Durumu: Açık");
        } else {
            txtWifiStatus.setText("WiFi Durumu: Kapalı");
        }
    }
}
