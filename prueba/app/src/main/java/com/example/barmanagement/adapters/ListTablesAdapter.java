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
import com.example.barmanagement.models.Tables;

import java.util.ArrayList;

public class ListTablesAdapter extends RecyclerView.Adapter<ListTablesAdapter.ViewHolder> {
    ArrayList<Tables> tables;
    Context context;
    private final OnTablesListener listener; // interfaz que me he creado para poder hacer onClick en el recyclerView

    public ListTablesAdapter(ArrayList<Tables> tables, Context context, OnTablesListener listener) {
        this.tables = tables;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tables_item,parent,false);

        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tables table = tables.get(position);
        Glide.with(context). // inserto la foto en el recycler con Glide
                load(table.getImg())
                .error(R.mipmap.ic_launcher)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(50)))
                .into(holder.imgTable);

        holder.txtMesa.setText(table.getNum());

    }


    @Override
    public int getItemCount() {
        return tables.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //componentes del RecyclerView

        ImageView imgTable;
        TextView txtMesa;
        OnTablesListener onTablesListener;

        public ViewHolder(@NonNull View itemView, OnTablesListener onTablesListener) {
            super(itemView);
            imgTable = (ImageView) itemView.findViewById(R.id.imgTable);
            txtMesa = (TextView) itemView.findViewById(R.id.txtMesa);
            this.onTablesListener = onTablesListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            onTablesListener.onTableClick(view,getAdapterPosition());
        }

    }
    public  interface OnTablesListener{
        void onTableClick(View view, int position);
    }

}
