package com.example.vpnnew;

import android.content.Intent;
import android.net.VpnService;
import android.os.ParcelFileDescriptor;
import android.util.Log;

import java.io.IOException;

public class MyVpnService extends VpnService {

    private static final String TAG = "MyVpnService";
    private ParcelFileDescriptor vpnInterface = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "VPN servisi başlatılıyor...");

        Builder builder = new Builder();

        builder.setSession("VPNNewService")
                .addAddress("10.0.2.0", 24)
                .addDnsServer("8.8.8.8")
                .addRoute("0.0.0.0", 0); // Tüm trafiği yönlendir

        try {
            vpnInterface = builder.establish();
            Log.i(TAG, "VPN bağlantısı kuruldu.");
        } catch (Exception e) {
            Log.e(TAG, "VPN kurulurken hata oluştu: ", e);
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            if (vpnInterface != null) {
                vpnInterface.close();
                vpnInterface = null;
                Log.i(TAG, "VPN bağlantısı sonlandırıldı.");
            }
        } catch (IOException e) {
            Log.e(TAG, "VPN bağlantısı kapatılırken hata: ", e);
        }
    }
}
