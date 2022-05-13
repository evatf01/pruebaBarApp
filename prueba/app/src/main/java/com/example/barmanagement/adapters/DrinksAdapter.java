package com.example.barmanagement.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import java.util.Arrays;
import java.util.List;

public class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.ViewHolder> {
    List<Category> categorias;
    String[] texto;
    Context context;
    private final RecyclerViewClickListener listener; // interfaz que me he creado para poder hacer onClick en el recyclerView
    FirebaseFirestore db;

    public DrinksAdapter(List<Category> categorias, Context context, RecyclerViewClickListener listener) {
        this.categorias = categorias;
        this.context = context;
        this.listener = listener;

        texto = new String[this.categorias.size()];
        Log.d("longitud lista", String.valueOf(categorias.size()));
    }




    public  interface RecyclerViewClickListener{
        void onClick(View view, int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drinks_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = categorias.get(position);
        Glide.with(context). // inserto la foto en el recycler con Glide
                load(categorias.get(position).getImg())
                .error(R.mipmap.ic_launcher)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(50)))
                .into(holder.imgDrink);
        holder.txtBebida.setText(categorias.get(position).getNombre());
        holder.txtCantidad.setText(categorias.get(position).getCantidad());
    }

    public String[] getTexto() {
        return texto;
    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // componentes del RecyclerView

        ImageView imgDrink;
        TextView txtBebida;
        EditText txtCantidad;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgDrink = (ImageView) itemView.findViewById(R.id.imgDrink);
            txtBebida = (TextView) itemView.findViewById(R.id.txtBebida);
            txtCantidad = (EditText) itemView.findViewById(R.id.txtCantidad);

            itemView.setOnClickListener(this);

            txtCantidad.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    texto[getBindingAdapterPosition()] = charSequence.toString();
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

        }
        // implementamos el metodo onClick, donde usaremos el metodo de la interfaz creada anteriormente
        @Override
        public void onClick(View v) {

            listener.onClick(v,getBindingAdapterPosition());
        }
    }
}
