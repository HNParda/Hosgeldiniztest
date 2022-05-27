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
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class OrderMenuAdapter extends BaseAdapter {

    private final DefActivity context;
    private final List<String> List;
    private final ListView ListView;

    public OrderMenuAdapter(DefActivity context, ListView ListView) {
        this.context = context;
        this.List = Configs.getMenu();
        this.ListView = ListView;
    }

    public List<String> getList() {
        return List;
    }

    private void add(View view) {
        EditText counter = ((View)view.getParent()).findViewById(R.id.count);
        String c = counter.getEditableText().toString();
        int count;
        if (!c.isEmpty()) count = parseInt(c);
        else count = 0;
        count ++;
        counter.setText(String.valueOf(count));

        test.add(getArray(view)[3]);
    }
    ArrayList test = new ArrayList();

    private void remove(View view) {
        EditText counter = ((View)view.getParent()).findViewById(R.id.count);
        int count = parseInt(counter.getEditableText().toString());
        if  (count == 0) return;
        count --;
        counter.setText(String.valueOf(count));

        String item = getArray(view)[3];
        count = Collections.frequency(List, item);
        count --;
        test = (ArrayList) test.stream().filter(i -> i.equals(item)).collect(Collectors.toList());

        for (int i = 0; i < count; i++) test.add(item);

    }

    String[] getArray(View view) {
        return List.get(ListView.getPositionForView(view)).split("[$]");
    }

    public List test() {
        return test;
    }

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

            ImageButton checkBox = view.findViewById(R.id.add);
            checkBox.setOnClickListener(this::add);

                        checkBox = view.findViewById(R.id.remove);
            checkBox.setOnClickListener(this::remove);
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