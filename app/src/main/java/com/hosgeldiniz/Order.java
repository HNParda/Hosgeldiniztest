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


}
