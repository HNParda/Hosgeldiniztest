package com.hosgeldiniz;


import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.hosgeldiniz.utils.Adapter.OrderMenuAdapterR;
import com.hosgeldiniz.utils.Configs;
import com.hosgeldiniz.utils.DefActivity;
import com.hosgeldiniz.utils.connect;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;

public class MainActivity extends DefActivity {
        public static OrderMenuAdapterR orderMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Configs.init(this);
        orderMenuAdapter = new OrderMenuAdapterR(this);
    }

    public void CreMenu(View view) {
        Intent intentScan = new Intent(this, CreateMenu.class);
        startActivity(intentScan);
    }

    public void ShowOrd(View view) {
        Intent intentScan = new Intent(this, ShowOrders.class);
        startActivity(intentScan);
    }

    public void Order(View view) {
        Intent intentScan = new Intent(this, Order.class);
        startActivity(intentScan);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.connect, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        startActivity(new Intent(this, connect.class));
        return true;
    }

    private String getLocalIpAddress() throws UnknownHostException {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        assert wifiManager != null;
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipInt = wifiInfo.getIpAddress();
        return InetAddress.getByAddress(ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(ipInt).array()).getHostAddress();
    }
}