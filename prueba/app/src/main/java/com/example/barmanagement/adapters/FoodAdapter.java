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
import com.example.barmanagement.models.Comanda;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    List<Category> categorias = new ArrayList<>();
    Context context;
    private final RecyclerViewClickListener listener; // interfaz que me he creado para poder hacer onClick en el recyclerView
    FirebaseFirestore db;

    public FoodAdapter(List<Category> categorias, Context context, RecyclerViewClickListener listener, FirebaseFirestore db) {
        this.categorias = categorias;
        this.context = context;
        this.listener = listener;
        this.db = db;
    }


    public  interface RecyclerViewClickListener{
        void onClick(View view, int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tapas_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.txtFood.setText(categorias.get(position).getNombre());
        holder.txtCantidad.setText(categorias.get(position).getCantidad());
    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // componentes del RecyclerView

        TextView txtFood;
        TextView txtCantidad;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtFood = (TextView) itemView.findViewById(R.id.txtFood);
            txtCantidad = (TextView) itemView.findViewById(R.id.txtCantidadFood);

            itemView.setOnClickListener(this);

        }
        // implementamos el metodo onClick, donde usaremos el metodo de la interfaz creada anteriormente
        @Override
        public void onClick(View v) {

            listener.onClick(v,getBindingAdapterPosition());
        }
    }
}
