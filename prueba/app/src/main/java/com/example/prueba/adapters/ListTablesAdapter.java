package com.example.prueba.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prueba.R;
import com.example.prueba.models.Tables;

import java.util.ArrayList;

public class ListTablesAdapter extends BaseAdapter {
    ArrayList<Tables> tables = new ArrayList<>();
    Context context;

    public ListTablesAdapter(ArrayList<Tables> tables, Context context) {
        this.tables = tables;
        this.context = context;
    }

    @Override
    public int getCount() {
        return tables.size();
    }

    @Override
    public Object getItem(int position) {
        return tables.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Tables table = (Tables) getItem(i);
        view = LayoutInflater.from(context).inflate(R.layout.tables_item, null);
        ImageView img = (ImageView) view.findViewById(R.id.img);
        TextView txtMesa  = (TextView) view.findViewById(R.id.txtMesa);

        img.setImageResource(table.getImg());
        txtMesa.setText(table.getNombre());

        return view;
    }
}
