package com.hosgeldiniz.utils;

import static com.hosgeldiniz.utils.Configs.getIp;
import static com.hosgeldiniz.utils.Configs.getName;
import static com.hosgeldiniz.utils.Configs.getPort;
import static com.hosgeldiniz.utils.Configs.setConfigs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hosgeldiniz.R;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class connect extends DefActivity {

    public static ObjectOutputStream outList;
    public static ObjectInputStream inList;

    public static Socket socket;
    public static ServerSocket serversocket;

    public static MsgAsync msgAsyncs;
    public static Thread threads;


    public static void initThread(boolean host, int Port) {

        threads = new Thread(() -> {

            try {

                if (host) {
                    serversocket = new ServerSocket(Port);
                    socket = serversocket.accept();
                } else socket = new Socket(getIp(), Port);

                outList = new ObjectOutputStream(socket.getOutputStream());
                inList = new ObjectInputStream(socket.getInputStream());

                outList.writeUTF("name$" + getName());
                outList.flush();

                msgAsyncs = new MsgAsync(inList, host);
                msgAsyncs.execute();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        threads.start();
    }

    @SuppressLint({"SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connect);
        String ip = Formatter.formatIpAddress(((WifiManager) getSystemService(WIFI_SERVICE)).getConnectionInfo().getIpAddress());
        ((TextView) findViewById(R.id.localip)).setText(
                "Host IP: " + ip);
    }

    public void Stop(View view) {
        try {
            if (socket != null) socket.close();
            if (serversocket != null) serversocket.close();
            if (threads != null) threads.interrupt();
            if (msgAsyncs != null) msgAsyncs.stop();
            if (outList != null) outList.close();
            if (inList != null) inList.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        outList = null;
        socket = null;
        serversocket = null;
        threads = null;
        msgAsyncs = null;
        inList = null;
    }

    public void Host(View view) {
        initThread(true, getPort());
    }

    public void Client(View view) {
        initThread(false, getPort());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View v = inflater.inflate(R.layout.settings, null);
        builder.setView(v);
        AlertDialog ad = builder.create();
        ad.setCanceledOnTouchOutside(false);
        ad.show();
        ad.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        EditText editIp = v.findViewById(R.id.editIP);
        EditText editName = v.findViewById(R.id.editName);
        EditText editPort = v.findViewById(R.id.editPort);
        Button nBtn = v.findViewById(R.id.nBtn);
        Button pBtn = v.findViewById(R.id.pBtn);
        editIp.setText(getIp());
        editName.setText(String.valueOf(getName()));
        editPort.setText(String.valueOf(getPort()));
        nBtn.setOnClickListener(view -> ad.dismiss());
        pBtn.setOnClickListener(view -> {
            String IP = editIp.getEditableText().toString();
            String Port = editPort.getEditableText().toString();
            String Name = editName.getEditableText().toString();
            if (!IP.equals("") && !Port.equals("") && IP.split("\\.").length == 4) {
                setConfigs(Name, IP, Integer.parseInt(Port));
                ad.dismiss();
            } else
                Toast.makeText(this, "Bir Hata olustu, Ip ve Portu kontrol ediniz.", Toast.LENGTH_SHORT).show();
        });
        return true;
    }

}
