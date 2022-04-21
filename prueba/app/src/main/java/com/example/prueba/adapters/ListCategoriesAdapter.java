package com.example.prueba.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prueba.R;
import com.example.prueba.models.Category;
import com.example.prueba.models.Tables;

import java.util.ArrayList;

public class ListCategoriesAdapter extends BaseAdapter{
    ArrayList<Category> categories = new ArrayList<>();
    Context context;

    public ListCategoriesAdapter(ArrayList<Category> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Category getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Category category = (Category) getItem(i);
        view = LayoutInflater.from(context).inflate(R.layout.categories_item, null);
        ImageView img = (ImageView) view.findViewById(R.id.imgCategory);
        TextView txtMesa  = (TextView) view.findViewById(R.id.txtCategory);

        img.setImageResource(category.getImg());
        txtMesa.setText(category.getNombre());

        return view;
    }


}
