package com.hosgeldiniz.utils;

import static com.hosgeldiniz.utils.Configs.getName;
import static com.hosgeldiniz.utils.Configs.setMenu;
import static com.hosgeldiniz.utils.connect.initThread;
import static com.hosgeldiniz.utils.connect.outList;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.List;


public class MsgAsync extends AsyncTask<String, Void, Void> {

    ObjectInputStream oIn;
    DefActivity d;
    byte msgType;
    String text;
    boolean test = true;
    boolean host;
    String[] msg;

    public MsgAsync(ObjectInputStream oIn, DefActivity d, boolean host) {
        this.oIn = oIn;
        this.d = d;
        this.host = host;
    }

    public void stop() {
        try {
            oIn.close();
            test = false;
            cancel(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Void doInBackground(String... strings) {
        String HostName = "Cihaz 1";

        Log.e("testtest", "test");

        while (test) {
            Log.e("testtest", "test2");
            try {

                Log.e("testtest", "msg " + Arrays.toString(msg));
                msg = oIn.readUTF().split("[$]");
                switch (msg[0]) {

                    default:
                        if (oIn.readObject() != null) {
                            setMenu((List<String>) oIn.readObject());
                            Toast(HostName + " yeni bir Menü gönderdi");
                        }
                        //Thread.sleep(30);
                            break;

                    case "name":
                        HostName = msg[1];
                        Toast(HostName + " baglandi");
                       // if (host) initThread(true, CurrentPort);
                        break;

                    case "menu":
                        setMenu((List<String>) oIn.readObject());
                        Toast(HostName + " yeni bir Menü gönderdi");
                        break;

                }
                //oIn.reset();
                msg = new String[]{};

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        Toast(" test");
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
    }

    public void Toast(String s) {
        Log.e("testtest", s);
        d.runOnUiThread(() -> Toast.makeText(d, s, Toast.LENGTH_SHORT).show());
    }
}
