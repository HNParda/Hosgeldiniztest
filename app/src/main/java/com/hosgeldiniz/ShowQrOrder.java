package com.hosgeldiniz;

import android.annotation.SuppressLint;
import android.os.Bundle;


import com.hosgeldiniz.utils.DefActivity;
@SuppressLint("SetTextI18n")
public class ShowQrOrder extends DefActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_orders);
    }

}