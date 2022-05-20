package com.example.barmanagement.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barmanagement.R;
import com.example.barmanagement.models.Comanda;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.ArrayList;
import java.util.List;

public class ComandaAdapter extends FirestoreRecyclerAdapter<Comanda, ComandaAdapter.ViewHolder> {
    List<Comanda> comandas = new ArrayList<>();
    Context context;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ComandaAdapter(@NonNull FirestoreRecyclerOptions<Comanda> options, Context context) {
        super(options);
        this.context = context;
    }


    public  interface RecyclerViewClickListener{
        void onClick(View view, int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comanda_item,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comanda comanda = comandas.get(position);
        holder.txtBebida.setText(comanda.getNombre());
        holder.txtCantidad.setText(comanda.getCantidad());
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Comanda model) {

    }
    public void deleteComanda(int position){
        getSnapshots().getSnapshot(position).getReference().delete();
    }

    @Override
    public int getItemCount() {
        return comandas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        // componentes del RecyclerView
        TextView txtBebida;
        TextView txtCantidad;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtBebida = (TextView) itemView.findViewById(R.id.txtBebida);
            txtCantidad = (TextView) itemView.findViewById(R.id.txtCantidad);

        }
        // implementamos el metodo onClick, donde usaremos el metodo de la interfaz creada anteriormente

    }
}
