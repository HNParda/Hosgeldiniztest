package com.hosgeldiniz.utils.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hosgeldiniz.R;
import com.hosgeldiniz.utils.DefActivity;

import java.util.List;

public class MenuAdapter extends BaseAdapter {

    private final DefActivity context;
    private final List<String> List;
    private final ListView ListVIew;

    public MenuAdapter(DefActivity context, ListView ListVIew, List<String> List) {
        this.context = context;
        this.List = List;
        this.ListVIew = ListVIew;
    }

    public void addItem(String s) {
        List.add("item$" + (getItemCount() + 1) + ". " + s + "$" + getCount());
        notifyDataSetChanged();
        Log.e("testtest", List.get(List.size() - 1));
    }

    public List<String> getList() {
        return List;
    }

    public void addGroup(String s) {
        List.add("group$" + s + "$" + getCount());
        notifyDataSetChanged();
        Log.e("testtest", List.get(List.size() - 1));
    }

    private void delete(View view) {
        List.remove(ListVIew.getPositionForView(view));
        notifyDataSetChanged();
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        String listTitle = (String) getItem(i);
        String[] args = listTitle.split("[$]");
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (args[0].equals("group")) {
            view = layoutInflater.inflate(R.layout.list_category, null);
            TextView listTitleTextView = view.findViewById(R.id.text);
            listTitleTextView.setTypeface(null, Typeface.BOLD);
            listTitleTextView.setText(args[1]);
        } else {
            view = layoutInflater.inflate(R.layout.list_item, null);
            Button btn = view.findViewById(R.id.menubtn);
            btn.setText(args[1]);
            ((View) btn.getParent()).setClickable(false);
        }

        FloatingActionButton btn = view.findViewById(R.id.delete);
        btn.setOnClickListener(this::delete);
        return view;
    }

    @Override
    public int getCount() {
        return List.size();
    }

    public int getItemCount() {
        int s = List.size();
        int count = 0;
        for (int i = 0; i < s; i++) if (List.get(i).split("[$]")[0].equals("group")) count++;
        Log.e("testtest itemcount", List.size() + " " + (s - count));
        return s - count;
    }

    @Override
    public Object getItem(int i) {
        return List.get(i);
    }

    @Override
    public long getItemId(int i) {
        return ListVIew.getId();

    }

}