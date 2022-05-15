package com.example.barmanagement.adapters;

import android.content.Context;
import android.util.Log;
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
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListTablesAdapter extends FirestoreRecyclerAdapter<Tables, ListTablesAdapter.ViewHolder> {

    Context context;
    private OnTablesListener listener; // interfaz que me he creado para poder hacer onClick en el recyclerView

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ListTablesAdapter(@NonNull FirestoreRecyclerOptions<Tables> options, Context context) {
        super(options);

        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tables_item,parent,false);

        return new ViewHolder(view, listener);
    }



    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Tables model) {
        Log.d("mesas", model.toString());
        Glide.with(context). // inserto la foto en el recycler con Glide
                load(model.getImg())
                .error(R.mipmap.ic_launcher)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(50)))
                .into(holder.imgTable);

        holder.txtMesa.setText(model.getNum());

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        //componentes del RecyclerView

        ImageView imgTable;
        TextView txtMesa;
        OnTablesListener onTablesListener;

        public ViewHolder(@NonNull View itemView, OnTablesListener onTablesListener) {
            super(itemView);
            imgTable = (ImageView) itemView.findViewById(R.id.imgTable);
            txtMesa = (TextView) itemView.findViewById(R.id.txtMesa);
            this.onTablesListener = onTablesListener;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getBindingAdapterPosition();
                    if(position != RecyclerView.NO_POSITION && listener != null){
                        listener.onTableClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });

        }
    }
    public  interface OnTablesListener{
        void onTableClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnClickListener(OnTablesListener listener){
        this.listener = listener;
    }



}
