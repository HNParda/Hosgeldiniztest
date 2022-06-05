package com.hosgeldiniz.utils.Adapter;

import static java.lang.Integer.parseInt;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.hosgeldiniz.R;
import com.hosgeldiniz.utils.Configs;
import com.hosgeldiniz.utils.DefActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class OrderMenuAdapter extends BaseAdapter {

    private final DefActivity context;
    private final List<String> List;
    private final ListView ListView;
    public ArrayList<String> order = new ArrayList<>();

    public OrderMenuAdapter(DefActivity context, ListView ListView) {
        this.context = context;
        this.List = Configs.getMenu();
        this.ListView = ListView;
    }

    public List<String> getList() {
        return List;
    }

    private void add(View view) {
        EditText counter = ((View) view.getParent()).findViewById(R.id.count);
        if (!EditText.contains(counter)) EditText.add(counter);
        String c = counter.getEditableText().toString();
        int count;
        if (!c.isEmpty()) count = parseInt(c);
        else count = 0;
        count++;
        counter.setText(String.valueOf(count));
        order.add(getArray(view)[3]);
        Collections.sort(order);
    }

    private void remove(View view) {
        EditText counter = ((View) view.getParent()).findViewById(R.id.count);
        String c = counter.getEditableText().toString();
        if (c.isEmpty()) c = "1";
        int count = parseInt(c);
        if (count == 0) return;
        count--;
        counter.setText(String.valueOf(count));
        String item = getArray(view)[3];
        order = (ArrayList<String>) order.stream().filter(i -> !i.equals(item)).collect(Collectors.toList());
        for (int i = 0; i < count; i++) order.add(item);
        Collections.sort(order);
    }


    String[] getArray(View view) {
        return List.get(ListView.getPositionForView(view)).split("[$]");
    }

    public ArrayList<EditText> EditText = new ArrayList<>();

    @SuppressLint("InflateParams")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        String listTitle = (String) getItem(i);
        String[] args = listTitle.split("[$]");
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (args[0].equals("group")) {
            view = layoutInflater.inflate(R.layout.menu_category, null);
            TextView listTitleTextView = view.findViewById(R.id.text);
            listTitleTextView.setText(args[1]);
            listTitleTextView.setClickable(false);
        } else {
            view = layoutInflater.inflate(R.layout.menu_item, null);
            Button btn = view.findViewById(R.id.menubtn);
            btn.setText(args[1]);
            EditText counter = view.findViewById(R.id.count);


            ImageButton imgbtn = view.findViewById(R.id.add);
            imgbtn.setOnClickListener(this::add);

            imgbtn = view.findViewById(R.id.remove);
            imgbtn.setOnClickListener(this::remove);


            String[] item = List.get(i).split("[$]");
            ArrayList<String> test = (ArrayList<String>) order.stream().filter(t -> t.equals(item[3])).collect(Collectors.toList());
            String test2 = String.valueOf(test.size());

            counter.setText(test2);
        }
        return view;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
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

}