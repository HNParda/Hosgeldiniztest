package com.hosgeldiniz;

import static com.hosgeldiniz.utils.Configs.getTableCount;
import static com.hosgeldiniz.utils.Configs.setTableCount;
import static java.lang.Integer.parseInt;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.hosgeldiniz.utils.DefActivity;
import com.hosgeldiniz.utils.Adapter.TableAdapter;

public class Order extends DefActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);
        ListView ListView = findViewById(R.id.List);
        ListView.setAdapter(new TableAdapter(this, ListView));
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

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View v = inflater.inflate(R.layout.table_count, null);
        builder.setView(v);
        AlertDialog ad = builder.create();
        ad.setCanceledOnTouchOutside(false);
        ad.show();
        ad.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        EditText editCount = v.findViewById(R.id.editCount);
        Button nBtn = v.findViewById(R.id.nBtn);
        Button pBtn = v.findViewById(R.id.pBtn);
        editCount.setText(String.valueOf(getTableCount()));
        nBtn.setOnClickListener(view -> ad.dismiss());
        pBtn.setOnClickListener(view -> {
            String count = editCount.getEditableText().toString();
            if (!count.equals("") && parseInt(count) >= 0 ) {
                setTableCount(parseInt(count));
                recreate();
                ad.dismiss();
            } else
                Toast.makeText(this, "Bir Hata olustu, Masa sayisini kontrol ediniz.", Toast.LENGTH_SHORT).show();
        });
        return true;
    }

}
