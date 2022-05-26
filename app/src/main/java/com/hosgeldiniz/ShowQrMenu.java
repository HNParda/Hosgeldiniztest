package com.hosgeldiniz;

import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;

import com.hosgeldiniz.utils.DefActivity;

public class ShowQrMenu extends DefActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_qr_menu);
        Uri LinkData = getIntent().getData();
        String data;

        if(LinkData != null){
            data = LinkData.getPathSegments().toString();
        } else {
            data = getIntent().getExtras().getString("URL");
        }
    }

}
