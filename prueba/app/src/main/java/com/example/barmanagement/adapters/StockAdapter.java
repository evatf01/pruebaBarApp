package com.example.barmanagement.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barmanagement.R;
import com.example.barmanagement.models.Category;

import java.util.List;

public class StockAdapter extends RecyclerView.Adapter<StockAdapter.ViewHolder> {
    List<Category> categories;
    Context context;

    public StockAdapter(List<Category> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stock_item,parent,false);

        return new ViewHolder(view);
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = categories.get(position);

        holder.txtNom.setText(category.getNombre());
        holder.txtFamilia.setText(category.getFamilia());
        holder.txtStock.setText(category.getStock());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        // componentes del RecyclerView

        TextView txtNom;
        TextView txtFamilia;
        TextView txtStock;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNom = (TextView) itemView.findViewById(R.id.txtNombre);
            txtFamilia = (TextView) itemView.findViewById(R.id.txtFamilia);
            txtStock = (TextView) itemView.findViewById(R.id.txtStock);
        }
    }
}
