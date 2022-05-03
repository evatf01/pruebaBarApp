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

import java.util.ArrayList;

public class ComandaAdapter extends RecyclerView.Adapter<ComandaAdapter.ViewHolder> {
    ArrayList<Comanda> comandas = new ArrayList<>();
    Context context;
    private RecyclerViewClickListener listener; // interfaz que me he creado para poder hacer onClick en el recyclerView

    public ComandaAdapter(ArrayList<Comanda> comandas, Context context, RecyclerViewClickListener listener) {
        this.comandas = comandas;
        this.context = context;
        this.listener = listener;
    }




    public  interface RecyclerViewClickListener{
        void onClick(View view, int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comanda_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comanda comanda = comandas.get(position);
        holder.txtOrden.setText(comanda.getComanda());
    }

    @Override
    public int getItemCount() {
        return comandas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // componentes del RecyclerView

        TextView txtOrden;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtOrden = (TextView) itemView.findViewById(R.id.txtOrden);

            itemView.setOnClickListener(this);

        }
        // implementamos el metodo onClick, donde usaremos el metodo de la interfaz creada anteriormente
        @Override
        public void onClick(View v) {

            listener.onClick(v,getAdapterPosition());
        }
    }
}
