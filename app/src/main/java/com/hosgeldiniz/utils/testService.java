package com.hosgeldiniz.utils;


import static com.hosgeldiniz.utils.Configs.Toast;
import static com.hosgeldiniz.utils.Configs.getName;
import static com.hosgeldiniz.utils.Configs.setMenu;
import static com.hosgeldiniz.utils.connect.inList;
import static com.hosgeldiniz.utils.connect.initThread;
import static com.hosgeldiniz.utils.connect.outList;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.sql.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class testService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

/*
    String[] msg;
    String text;
    boolean test = true;
    Handler handler;
    Timer timer = new Timer();
    int pos;
    boolean host;
Thread thread;

    public void stop() {
        test = false;
        thread.interrupt();

        stopSelf();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler();
        thread =  new Thread(() -> {


            final String[] HostName = {"Cihaz 1"};
            String[] clients;


            try {
                outList.get(pos).writeByte(1);
                outList.get(pos).writeUTF(getName());
                outList.get(pos).flush();
            } catch (IOException e) {
                e.printStackTrace();
            }


            TimerTask doAsynchronousTask = new TimerTask() {
                @Override
                public void run() {
                    // Add your code here.

                    msg = new String[]{};
                    try {

                        msg = inList.get(pos).readUTF().split("[$]");
                        switch (msg[0]) {

                            default:
                                Toast(Arrays.toString(msg));

                            case "name":
                                HostName[0] = inList.get(pos).readUTF();
                                Toast(HostName[0] + " baglandi " + (CurrentPort-1));
                                if (host) initThread(true, CurrentPort);

                            case "menu":
                                setMenu((List<String>) inList.get(pos).readObject());
                                Toast(HostName[0] + " yeni bir Menü gönderdi");

                        }

                        Thread.sleep(50);
                        inList.get(pos).reset();

                    } catch (IOException | ClassNotFoundException | InterruptedException e) {
                        e.printStackTrace();
                    }


                }
            };
            //Starts after 20 sec and will repeat on every 20 sec of time interval.
            timer.schedule(doAsynchronousTask, 0, 50);  // 20 sec timer
        });
        thread.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) stop();
        pos = intent.getIntExtra("pos", 0);
        host = intent.getBooleanExtra("host", false);
        // TODO do something useful
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
*/
}
