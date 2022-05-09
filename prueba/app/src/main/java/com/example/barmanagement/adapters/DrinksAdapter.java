package com.example.barmanagement.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EdgeEffect;
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
import com.example.barmanagement.models.Tables;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;

public class DrinksAdapter extends FirestoreRecyclerAdapter<Category, DrinksAdapter.ViewHolder> {

    Context context;
    private OnCategoryListener listener; // interfaz que me he creado para poder hacer onClick en el recyclerView

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public DrinksAdapter(@NonNull FirestoreRecyclerOptions<Category> options, Context context) {
        super(options);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drinks_item,parent,false);

        return new ViewHolder(view, listener);
    }



    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Category model) {
        Glide.with(context). // inserto la foto en el recycler con Glide
                load(model.getImg())
                .error(R.mipmap.ic_launcher)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(50)))
                .into(holder.imgCategory);

        holder.txtNombre.setText(model.getNombre());
        holder.txtCantidad.setText(model.getCantidad());
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        //componentes del RecyclerView

        ImageView imgCategory;
        TextView txtNombre;
        EditText txtCantidad;
        OnCategoryListener onCategoryListener;

        public ViewHolder(@NonNull View itemView, OnCategoryListener onCategoryListener) {
            super(itemView);
            imgCategory = (ImageView) itemView.findViewById(R.id.imgDrink);
            txtNombre = (TextView) itemView.findViewById(R.id.txtBebida);
            txtCantidad = (EditText) itemView.findViewById(R.id.txtCantidad);
            this.onCategoryListener = onCategoryListener;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getBindingAdapterPosition();
                    if(position != RecyclerView.NO_POSITION && listener != null){
                        listener.onCategoryClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });

        }
    }
    public  interface OnCategoryListener{
        void onCategoryClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnClickListener(OnCategoryListener listener){
        this.listener = listener;
    }



}
