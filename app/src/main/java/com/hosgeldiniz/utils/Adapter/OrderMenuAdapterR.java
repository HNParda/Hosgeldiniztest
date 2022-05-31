package com.hosgeldiniz.utils.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.hosgeldiniz.R;
import com.hosgeldiniz.utils.Configs;

import java.util.ArrayList;

public class OrderMenuAdapterR
        extends RecyclerView.Adapter<OrderMenuAdapterR.ViewHolder> {

    private ArrayList<String> numberName;
    private Context context;

    public OrderMenuAdapterR(Context context) {
        super();
        this.context = context;
        numberName = (ArrayList<String>) Configs.getMenu();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.order_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Log.e("testtest", numberName.get(i));
        viewHolder.TextView.setText(numberName.get(i));
        viewHolder.setClickListener((view, position, isLongClick) -> Toast.makeText(context, "#" + position + " - " + numberName.get(position),
                Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        Log.e("testtest", String.valueOf(numberName.size()));
        return numberName.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView TextView;
        private ItemClickListener clickListener;

        ViewHolder(View itemView) {
            super(itemView);
            TextView = itemView.findViewById(R.id.text);
            itemView.setOnClickListener(this);
        }

        void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getPosition(), false);
        }

    }
}