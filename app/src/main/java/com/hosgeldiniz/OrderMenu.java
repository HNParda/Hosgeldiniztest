package com.hosgeldiniz;

import static com.hosgeldiniz.utils.connect.outList;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.hosgeldiniz.utils.Adapter.OrderMenuAdapter;
import com.hosgeldiniz.utils.Configs;
import com.hosgeldiniz.utils.DefActivity;

import java.io.IOException;

public class OrderMenu extends DefActivity {

    public int table;
    ListView ListView;
    OrderMenuAdapter OrderMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_menu);
        table = getIntent().getIntExtra("table", 1);
        Configs.Toast(String.valueOf(table));
        ListView = findViewById(R.id.List);
        OrderMenuAdapter = new OrderMenuAdapter(this, ListView);
        ListView.setAdapter(OrderMenuAdapter);
    }

    public void test(View view) {
        StringBuilder t = new StringBuilder();
        for (int i = 0; i < OrderMenuAdapter.order.size(); i++)
            t.append(OrderMenuAdapter.order.get(i));
        ((TextView) findViewById(R.id.textt)).setText(t);
        Log.e("testtest", "order$" + t);
        try {
            outList.writeUTF("order$" + t);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
