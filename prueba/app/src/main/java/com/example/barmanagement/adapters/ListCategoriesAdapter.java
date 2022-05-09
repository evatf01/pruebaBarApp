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
import com.example.barmanagement.models.CategoryTypes;
import com.example.barmanagement.models.Comanda;

import java.util.ArrayList;

public class ListCategoriesAdapter extends RecyclerView.Adapter<ListCategoriesAdapter.ViewHolder> {
    ArrayList<CategoryTypes> categories = new ArrayList<>();
    Context context;
    private RecyclerViewClickListener listener; // interfaz que me he creado para poder hacer onClick en el recyclerView

    public ListCategoriesAdapter(ArrayList<CategoryTypes> categories, Context context, RecyclerViewClickListener listener) {
        this.categories = categories;
        this.context = context;
        this.listener = listener;
    }




    public  interface RecyclerViewClickListener{
        void onClick(View view, int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryTypes category = categories.get(position);
        Glide.with(context). // inserto la foto en el recycler con Glide
                load(category.getImg())
                .error(R.mipmap.ic_launcher)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(50)))
                .into(holder.imgCat);

        holder.txtNom.setText(category.getName());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // componentes del RecyclerView

        TextView txtNom;
        ImageView imgCat;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNom = (TextView) itemView.findViewById(R.id.txtCategory);
            imgCat = (ImageView) itemView.findViewById(R.id.imgCategory);

            itemView.setOnClickListener(this);

        }
        // implementamos el metodo onClick, donde usaremos el metodo de la interfaz creada anteriormente
        @Override
        public void onClick(View v) {
            listener.onClick(v,getBindingAdapterPosition());
        }
    }
}
