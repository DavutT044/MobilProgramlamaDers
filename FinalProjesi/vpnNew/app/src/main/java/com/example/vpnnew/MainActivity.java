package com.example.vpnnew;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.VpnService;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private TextView statusText;
    private ImageButton myButton;
    private boolean isActive = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Arayüz öğelerini başlatıyoruz
        statusText = findViewById(R.id.statusText);
        myButton = findViewById(R.id.myButton);

        // Bağlan butonuna tıklama olayı
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // VPN bağlantısını başlatmak için hazırlık
                Intent intent = VpnService.prepare(MainActivity.this);
                if (intent != null) {
                    // Eğer VPN izinleri gerekliyse, kullanıcıdan izin alınacak
                    startActivityForResult(intent, 0);
                } else {
                    // Eğer izinler zaten verilmişse, VPN servisini başlat
                    if (!isActive) {
                        startVpnService();
                    } else {
                        stopVpnService();
                    }
                    Log.d("VPN_DEBUG", "Buton arka planı değiştiriliyor: " + (isActive ? "ACTIVE" : "INACTIVE"));
                }
            }
        });
    }

    // onActivityResult ile kullanıcıdan izin aldıktan sonra işlemi başlatıyoruz
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                if (!isActive) {
                    startVpnService();
                } else {
                    stopVpnService();
                }
            } else {
                // İzin verilmediyse kullanıcıya bilgi verilebilir
                statusText.setText("VPN İzni verilmedi");
            }
        }
    }

    private void startVpnService() {
        isActive = true;
        Intent intent = new Intent(this, MyVpnService.class);
        startService(intent);

        statusText.setText("VPN Durumu: Bağlandı");
        myButton.setBackgroundResource(R.drawable.active_button);
    }

    private void stopVpnService() {
        isActive = false;
        Intent intent = new Intent(this, MyVpnService.class);
        stopService(intent);

        statusText.setText("VPN Durumu: Bağlantı Kesildi");
        myButton.setBackgroundResource(R.drawable.inactive_button);
    }
}
