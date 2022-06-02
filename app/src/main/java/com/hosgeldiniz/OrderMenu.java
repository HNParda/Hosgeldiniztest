package com.hosgeldiniz;

import static com.hosgeldiniz.utils.connect.outList;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.hosgeldiniz.utils.Adapter.OrderMenuAdapter;
import com.hosgeldiniz.utils.Configs;
import com.hosgeldiniz.utils.DefActivity;

import java.io.IOException;
import java.util.ArrayList;

public class OrderMenu extends DefActivity {

    public int table;
    ListView ListView;
    OrderMenuAdapter OrderMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_menu);
        table = getIntent().getIntExtra("table", 1);
        ListView = findViewById(R.id.List);
        OrderMenuAdapter = new OrderMenuAdapter(this, ListView);
        ListView.setAdapter(OrderMenuAdapter);
    }

    public void test(View view) {
        StringBuilder t = new StringBuilder();
        for (int i = 0; i < OrderMenuAdapter.order.size(); i++) t.append(OrderMenuAdapter.order.get(i));
        Thread th = new Thread(() -> {
            try {
                if (outList == null || t.toString().isEmpty()) return;
                Configs.Toast("Siparis gönderildi!", Toast.LENGTH_LONG);
                outList.writeUTF("order$" + table + "$" + t);
                outList.flush();
            } catch (IOException e) {
                Log.e("testtest", e.getMessage());
            }
        });
        th.start();
        th.interrupt();
        OrderMenuAdapter.order = new ArrayList<>();
        for (int i = 0; i < OrderMenuAdapter.EditText.size(); i++) OrderMenuAdapter.EditText.get(i).setText("0");
    }
}
