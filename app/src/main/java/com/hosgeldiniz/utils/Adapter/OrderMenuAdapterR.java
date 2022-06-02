package com.hosgeldiniz.utils.Adapter;

import static com.hosgeldiniz.utils.Configs.getMenu;
import static com.hosgeldiniz.utils.Configs.setOrder;
import static java.lang.Integer.parseInt;

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
import com.hosgeldiniz.utils.DefActivity;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class OrderMenuAdapterR
        extends RecyclerView.Adapter<OrderMenuAdapterR.ViewHolder> {

    private final DefActivity context;
    public ArrayList<String> OrderList;

    public OrderMenuAdapterR(DefActivity context) {
        super();
        this.context = context;
        //Configs.setOrder(new ArrayList<String>());
        OrderList = (ArrayList<String>) Configs.getOrder();
    }

    public void addO(String s) {
        context.runOnUiThread((Runnable) () -> {
            OrderList.add(s);
            notifyDataSetChanged();
        });
        setOrder(OrderList);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.order_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int pos) {
        String[] msg = OrderList.get(pos).split("[$]");


        ArrayList<String> testt = new ArrayList<>();
        testt.add(" ");

        for (int i = 0; i < msg[2].length(); i++) {
            if (!String.valueOf(msg[2].charAt(i)).equals(testt.get(testt.size() - 1))) testt.add(String.valueOf(msg[2].charAt(i)));
        }

        StringBuilder test = new StringBuilder().append("abc");
        for (int i = 1; i < testt.size(); i++) {
            int count = 0;
            for (int c = 0; c < testt.get(i).length(); c++) {
                Pattern pattern = Pattern.compile("[^" + testt.get(i).charAt(c) + "]*" + testt.get(i).charAt(c) + "");
                Matcher matcher = pattern.matcher(msg[2]);
                while (matcher.find()) {
                    count++;
                }
            }

            int finalI = i;
            String Order = getMenu().stream().filter(o -> o.split("[$]")[3]
                            .contains(testt.get(finalI)))
                    .collect(Collectors.toList()).get(0)
                    .split("[$]")[1]
                    .split("[.]")[1];

            test.append("\n" + count + "x" + Order);
        }


        viewHolder.Order.setText("");
        viewHolder.Order.setText(test.toString().replace("abc\n", ""));
        viewHolder.Table.setText("Masa " + (parseInt(msg[1]) + 1));
    }

    @Override
    public int getItemCount() {
        Log.e("testtest", String.valueOf(OrderList.size()));
        return OrderList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView Order;
        public TextView Table;
        Button btn1;
        Button btn2;

        ViewHolder(View view) {
            super(view);
            Order = view.findViewById(R.id.order);
            Table = view.findViewById(R.id.table);

            btn1 = view.findViewById(R.id.btn1);
            btn2 = view.findViewById(R.id.btn2);
            btn1.setOnClickListener(test(1));
            btn2.setOnClickListener(test(2));
        }

        public View.OnClickListener test(int id) {
            return v -> Toast.makeText(Order.getContext(), String.valueOf(getPosition()) + id, Toast.LENGTH_SHORT).show();

            /*return new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(txt.getContext(), String.valueOf(getPosition()) + id, Toast.LENGTH_SHORT).show();
                }
            } */
        }

    }
}