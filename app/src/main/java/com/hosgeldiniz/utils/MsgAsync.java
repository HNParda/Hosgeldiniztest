package com.hosgeldiniz.utils;

import static com.hosgeldiniz.MainActivity.orderMenuAdapter;
import static com.hosgeldiniz.utils.Configs.getOrder;
import static com.hosgeldiniz.utils.Configs.setMenu;
import static com.hosgeldiniz.utils.Configs.setOrder;
import static com.hosgeldiniz.utils.Configs.setTableCount;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.hosgeldiniz.utils.Adapter.OrderMenuAdapter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.List;


public class MsgAsync extends AsyncTask<String, Void, Void> {

    ObjectInputStream oIn;
    DefActivity d;
    boolean stop = true;
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
            stop = false;
            cancel(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Void doInBackground(String... strings) {
        String HostName = "Cihaz 1";

        Log.e("testtest", "test");

        while (stop) {
            Log.e("testtest1", "test2");
            try {

                msg = oIn.readUTF().split("[$]");
                Log.e("testtest2", Arrays.toString(msg));
                switch (msg[0]) {

                    default:
                        if (oIn.readObject() != null) {
                            setMenu((List<String>) oIn.readObject());
                            Toast(HostName + " yeni bir Menü gönderdi  2");
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
                        setTableCount(Integer.parseInt(msg[1]));
                        Toast(HostName + " yeni bir Menü gönderdi");
                        break;

                    case "order":
                        orderMenuAdapter.addO(Arrays.toString(msg));
                        Toast(HostName + " " + msg[1] + " " + msg[2]);
                        break;

                }
                oIn.reset();
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
        Log.e("testtest toast", s);
        d.runOnUiThread(() -> Toast.makeText(d, s, Toast.LENGTH_SHORT).show());
    }
}
