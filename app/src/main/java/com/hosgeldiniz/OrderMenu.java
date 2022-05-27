package com.hosgeldiniz;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.hosgeldiniz.utils.Adapter.OrderMenuAdapter;
import com.hosgeldiniz.utils.Configs;
import com.hosgeldiniz.utils.DefActivity;

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
        String t = "";
        for (int i = 0; i < OrderMenuAdapter.test().size(); i++) t += OrderMenuAdapter.test().get(i);
        ((TextView)findViewById(R.id.text)).setText(t);
    }
}
