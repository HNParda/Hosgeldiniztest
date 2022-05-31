package com.hosgeldiniz.utils.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
        viewHolder.txt.setText(numberName.get(i));
    }

    @Override
    public int getItemCount() {
        Log.e("testtest", String.valueOf(numberName.size()));
        return numberName.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt;
        Button btn1;
        Button btn2;

        ViewHolder(View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.text);
            btn1 = itemView.findViewById(R.id.btn1);
            btn2 = itemView.findViewById(R.id.btn2);
            btn1.setOnClickListener(test(1));
            btn2.setOnClickListener(test(2));
        }

        public View.OnClickListener test(int id) {
            return v -> Toast.makeText(txt.getContext(), String.valueOf(getPosition()) + id, Toast.LENGTH_SHORT).show();

            /*return new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(txt.getContext(), String.valueOf(getPosition()) + id, Toast.LENGTH_SHORT).show();
                }
            } */
        }

    }
}