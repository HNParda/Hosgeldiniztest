package com.hosgeldiniz.utils.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.hosgeldiniz.OrderMenu;
import com.hosgeldiniz.R;
import com.hosgeldiniz.utils.Configs;
import com.hosgeldiniz.utils.DefActivity;

import java.util.ArrayList;
import java.util.List;

public class TableAdapter extends BaseAdapter {

    private final DefActivity context;
    private final List<String> List;
    private final ListView ListView;

    public TableAdapter(DefActivity context, ListView ListView) {
        this.context = context;
        this.ListView = ListView;
        List = new ArrayList<>();
        for (int i = 1; Configs.getTableCount() + 1 > i; i++) List.add("Masa " + i);
        notifyDataSetChanged();
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.table_item, null);
        Button btn = view.findViewById(R.id.btn);
        btn.setText(String.valueOf(getItem(i)));
        btn.setOnClickListener(this::onClick);
        ((View) btn.getParent()).setClickable(false);
        return view;
    }

    @Override
    public int getCount() {
        return List.size();
    }

    @Override
    public Object getItem(int i) {
        return List.get(i);
    }

    @Override
    public long getItemId(int i) {
        return ListView.getId();
    }

    public void onClick(View view) {
        context.startActivity(new Intent(context, OrderMenu.class).putExtra("table", ListView.getPositionForView(view)));
    }

}