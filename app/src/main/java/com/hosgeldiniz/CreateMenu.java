package com.hosgeldiniz;

import static com.hosgeldiniz.utils.Configs.getMenu;
import static com.hosgeldiniz.utils.Configs.getTableCount;
import static com.hosgeldiniz.utils.Configs.setMenu;
import static com.hosgeldiniz.utils.Configs.setTableCount;
import static java.lang.Integer.parseInt;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hosgeldiniz.utils.Adapter.MenuAdapter;
import com.hosgeldiniz.utils.DefActivity;
import com.hosgeldiniz.utils.confirm;

public class CreateMenu extends DefActivity {

    MenuAdapter adapter;
    android.widget.ListView ListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_menu);
        ListView = findViewById(R.id.list);
        adapter = new MenuAdapter(this, ListView, getMenu());
        ListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause() {
        super.onPause();
        setMenu(adapter.getList());
    }

    public void newA(View view) {
        Alert(true);
    }

    public void newG(View view) {
        Alert(false);
    }

    public void Alert(boolean isItem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View v = inflater.inflate(R.layout.set_text, null);
        builder.setView(v);
        AlertDialog ad = builder.create();
        ad.setCanceledOnTouchOutside(false);
        ad.show();
        ad.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        EditText text = v.findViewById(R.id.text);
        Button nBtn = v.findViewById(R.id.nBtn);
        Button pBtn = v.findViewById(R.id.pBtn);
        nBtn.setOnClickListener(view -> ad.dismiss());
        EditText p = v.findViewById(R.id.price);
        if (isItem) p.setVisibility(View.VISIBLE);
        pBtn.setOnClickListener(view -> {
            String price = p.getEditableText().toString();
            if (price.isEmpty()) {
                if (isItem) { Toast.makeText(this, "Fiyat vermeyi unutunuz", Toast.LENGTH_SHORT).show();
                return; }
                else price = "0";
            }
            add(text.getEditableText().toString() + "$" + price, isItem);
            ad.dismiss();
        });
    }

    public void add(String text, boolean isItem) {
        if (isItem) adapter.addItem(text);
        else adapter.addGroup(text);
    }

    public void confirm(View view) {
        Intent i = new Intent(this, confirm.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Masa Sayisi")
                .setIcon(R.drawable.table_icon)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        LayoutInflater inflater = this.getLayoutInflater();
        View v = inflater.inflate(R.layout.table_count, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(v);
        AlertDialog ad = builder.create();
        ad.show();
        ad.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        EditText editCount = v.findViewById(R.id.editCount);
        Button nBtn = v.findViewById(R.id.nBtn);
        Button pBtn = v.findViewById(R.id.pBtn);
        editCount.setText(String.valueOf(getTableCount()));
        nBtn.setOnClickListener(view -> ad.dismiss());
        pBtn.setOnClickListener(view -> {
            String count = editCount.getEditableText().toString();
            if (!count.equals("") && parseInt(count) >= 0) {
                setTableCount(parseInt(count));
                ad.dismiss();
            } else
                Toast.makeText(this, "Bir Hata olustu, Masa sayisini kontrol ediniz.", Toast.LENGTH_SHORT).show();
        });

        return true;
    }

}
