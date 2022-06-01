package com.hosgeldiniz;

import static com.hosgeldiniz.MainActivity.orderMenuAdapter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hosgeldiniz.utils.Adapter.OrderMenuAdapter;
import com.hosgeldiniz.utils.Adapter.OrderMenuAdapterR;
import com.hosgeldiniz.utils.DefActivity;
@SuppressLint("SetTextI18n")
public class ShowOrders extends DefActivity {

    RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_orders);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        list = (RecyclerView) findViewById(R.id.list);
        list.setLayoutManager(layoutManager);
        list.setAdapter(orderMenuAdapter);
    }

}