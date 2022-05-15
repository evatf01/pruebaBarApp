package com.example.barmanagement.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barmanagement.R;
import com.example.barmanagement.models.Category;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class RacionesAdapter extends FirestoreRecyclerAdapter<Category, RacionesAdapter.ViewHolder> {

    Context context;
    private OnCategoryListener listener; // interfaz que me he creado para poder hacer onClick en el recyclerView

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public RacionesAdapter(@NonNull FirestoreRecyclerOptions<Category> options, Context context) {
        super(options);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raciones_item,parent,false);

        return new ViewHolder(view, listener);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Category model) {

        holder.txtNombre.setText(model.getNombre());
        holder.txtCantidad.setText(model.getCantidad());
        holder.txtPrecio.setText(model.getPrecio());
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        //componentes del RecyclerView


        TextView txtNombre;
        TextView txtPrecio;
        EditText txtCantidad;

        OnCategoryListener onCategoryListener;

        public ViewHolder(@NonNull View itemView, OnCategoryListener onCategoryListener) {
            super(itemView);

            txtNombre = (TextView) itemView.findViewById(R.id.txtFood);
            txtCantidad = (EditText) itemView.findViewById(R.id.txtCantidadFood);
            txtPrecio = (TextView) itemView.findViewById(R.id.txtPrecio);
            this.onCategoryListener = onCategoryListener;
            itemView.setOnClickListener( view ->  {
                int position = getBindingAdapterPosition();
                if(position != RecyclerView.NO_POSITION && listener != null){
                    listener.onCategoryClick(getSnapshots().getSnapshot(position), position);
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
