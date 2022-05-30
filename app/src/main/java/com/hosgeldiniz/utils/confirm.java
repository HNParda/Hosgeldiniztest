package com.hosgeldiniz.utils;

import static com.hosgeldiniz.utils.Configs.getMenu;
import static com.hosgeldiniz.utils.Configs.getName;
import static com.hosgeldiniz.utils.Configs.getTableCount;
import static com.hosgeldiniz.utils.connect.outList;

import android.os.Bundle;
import android.view.View;
import com.hosgeldiniz.R;
import java.io.IOException;

public class confirm extends DefActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm);
    }

    public void sendMenu(View view) {

        new Thread(() -> {
            try {
                //for(int i = 0; i < outList.size(); i++) {
                    outList.writeUTF("menu$" + getTableCount());
                    outList.writeObject(getMenu());
                    outList.flush();
                //}
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
