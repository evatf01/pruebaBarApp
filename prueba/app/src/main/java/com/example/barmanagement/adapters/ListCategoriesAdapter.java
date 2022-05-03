package com.example.barmanagement.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.barmanagement.R;
import com.example.barmanagement.models.Category;

import java.util.ArrayList;

public class ListCategoriesAdapter extends RecyclerView.Adapter<ListCategoriesAdapter.ViewHolder> {
    ArrayList<Category> listCategories;
    Context context;
    private final OnCategoryListener listener; // interfaz que me he creado para poder hacer onClick en el recyclerView

    public ListCategoriesAdapter(ArrayList<Category> listCategories, Context context, OnCategoryListener listener) {
        this.listCategories = listCategories;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_item,parent,false);

        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = listCategories.get(position);
        Glide.with(context). // inserto la foto en el recycler con Glide
                load(category.getImg())
                .error(R.mipmap.ic_launcher)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(50)))
                .into(holder.imgCategory);

        holder.txtNom.setText(category.getNombre());

    }


    @Override
    public int getItemCount() {
        return listCategories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //componentes del RecyclerView

        ImageView imgCategory;
        TextView txtNom;
        OnCategoryListener onCategoryListener;

        public ViewHolder(@NonNull View itemView, OnCategoryListener onTablesListener) {
            super(itemView);
            imgCategory = (ImageView) itemView.findViewById(R.id.imgCategory);
            txtNom = (TextView) itemView.findViewById(R.id.txtCategory);
            this.onCategoryListener = onTablesListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            onCategoryListener.onClickListener(view, getAdapterPosition());
        }
        // implementamos el metodo onClick, donde usaremos el metodo de la interfaz creada anteriormente

    }
    public interface OnCategoryListener{
        void onClickListener(View view, int position);
    }

}
