package com.example.vpnnew;

import android.net.VpnService;
import android.os.ParcelFileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class MyVpnService extends VpnService {

    private ParcelFileDescriptor vpnInterface;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(android.content.Intent intent, int flags, int startId) {
        if (vpnInterface == null) {
            startVpn();
        }
        return START_STICKY;
    }

    private void startVpn() {
        Builder builder = new Builder();
        builder.setMtu(1500);
        builder.addAddress("10.0.0.2", 24);
        builder.addRoute("0.0.0.0", 0);
        builder.addDnsServer("8.8.8.8");

        vpnInterface = builder.establish();

        if (vpnInterface != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    runVpnLoop();
                }
            }).start();
        }
    }

    private void runVpnLoop() {
        FileInputStream inputStream = new FileInputStream(vpnInterface.getFileDescriptor());
        FileOutputStream outputStream = new FileOutputStream(vpnInterface.getFileDescriptor());
        ByteBuffer buffer = ByteBuffer.allocate(32767);

        try {
            while (true) {
                int length = inputStream.read(buffer.array());
                if (length > 0) {
                    outputStream.write(buffer.array(), 0, length);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            if (vpnInterface != null) {
                vpnInterface.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        vpnInterface = null;
    }
}
